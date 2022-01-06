package ir.sharif.math.ap99_2.arkanoid.logic;

import ir.sharif.math.ap99_2.arkanoid.models.*;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.Break;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.FlashingBreak;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.PrizeBreak;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.WoodenBreak;
import ir.sharif.math.ap99_2.arkanoid.util.Config;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ModelLoader {
    private final File defaultGame, playersDirectory, highestScores;

    public ModelLoader( ) {
        defaultGame = Config.getConfig("mainConfig").getProperty(File.class, "defaultGame");
        playersDirectory = Config.getConfig("mainConfig").getProperty(File.class, "playersDirectory");
        highestScores = Config.getConfig("mainConfig").getProperty(File.class, "highestScores");
        if (!playersDirectory.exists()) playersDirectory.mkdirs();
    }

    public Player loadPlayer(String playerName){
        try {
            File playerDirectory = searchPlayerDirectory(playerName);
            if (playerDirectory==null){
                Player player = new Player(playerName, UUID.randomUUID().toString());
                saveFinishedGame(player);
                return player;
            }
            File gInfo = new File(playerDirectory.getPath()+"\\gInfo.txt");
            Scanner scanner = new Scanner(gInfo);
            scanner.next();scanner.next();scanner.next();scanner.next();scanner.next();
            String id = scanner.next();
            Player player = new Player(playerName,id);
            saveFinishedGame(player);
            return player;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("could not find player file");
            System.exit(-2);
        }
        return null;
    }

    public void saveFinishedGame(Player player){
        try {
            createGInfo(player);
            boolean exist = false;
            File tmpFile = new File(highestScores.getParent()+"\\tmp.txt");
            Scanner fileScanner = new Scanner(highestScores);
            FileWriter fileWriter = new FileWriter(tmpFile);
            HashMap<String, Integer> scores = new HashMap<>();
            while (fileScanner.hasNext()){
                String name = fileScanner.next();
                if (name.equals(player.getName())){
                    exist = true;
                    fileScanner.next();
                    int score = Integer.parseInt(fileScanner.next());
                    if (player.getTotalScore()>score)
                        score = player.getTotalScore();
                    scores.put(name,score);
                    if (fileScanner.hasNext())
                        name = fileScanner.next();
                    else
                        break;
                }
                fileScanner.next();
                scores.put(name,Integer.parseInt(fileScanner.next()));
            }
            if (!exist)
                scores.put(player.getName(),player.getTotalScore());
            Map<String, Integer> sortedScores = sortByValue(scores);
            for (Map.Entry<String, Integer> en : sortedScores.entrySet()) {
                fileWriter.write(en.getKey()+" = "+en.getValue()+"\n");
            }
            fileScanner.close();
            fileWriter.flush();
            fileWriter.close();
            highestScores.delete();
            tmpFile.renameTo(highestScores);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            System.err.println("could not find player file");
            System.exit(-2);
        }
        catch (Exception e ){
            e.printStackTrace();
        }


    }

    public void saveUnfinishedGame(Player player,String gameName){
        try {
            File playerDirectory = searchPlayerDirectory(player.getName());
            if (!playerDirectory.exists())
                playerDirectory.mkdirs();
            File gameDirectory = new File(playerDirectory.getPath()+"\\"+gameName);
            if (gameDirectory.exists()){
                int answer = JOptionPane.showConfirmDialog(null,"Do you want to save changes?"
                        ,"Save Loaded Game",JOptionPane.YES_NO_OPTION);
                if (answer==1)
                    return;
            }
            else {
                gameDirectory.mkdirs();
            }
            File gameFile = new File(gameDirectory.getPath()+"\\gameInfo.txt");
            gameFile.createNewFile();
            saveGameState(gameFile,player.getGameState());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void saveGameState(File gameFile,GameState gameState){
        GameStateSaver gameStateSaver = new GameStateSaver(gameState,gameFile);
        gameStateSaver.saveGameState();
    }

    public GameState loadUnfinishedGame(File file,Player player){
        String data = fileToString(file);
        GameState gameState = new GameState(player);
        GameStateBuilder gameStateBuilder = new GameStateBuilder(data);
        gameStateBuilder.completeGameState(gameState);
        return gameState;
    }

    public void completeDefaultGS(GameState gameState){
        String data = fileToString(defaultGame);
        GameStateBuilder gameStateBuilder = new GameStateBuilder(data);
        gameStateBuilder.completeGameState(gameState);
    }

    public File searchPlayerDirectory (String playerName) {
        File[] files = playersDirectory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                if (file.getName().equals(playerName))
                    return file;
            }
        }
        return null;
    }

    public void createGInfo(Player player){
        try {
            File playerDirectory = searchPlayerDirectory(player.getName());
            if (playerDirectory==null){
                playerDirectory = new File(playersDirectory.getPath()+"\\"+player.getName());
                playerDirectory.mkdirs();
            }
            File gInfo = new File(playerDirectory.getPath()+"\\gInfo.txt");
            if (!gInfo.exists()){
                gInfo.createNewFile();
                FileWriter writer = new FileWriter(gInfo);
                writer.write("Name = "+player.getName()+"\n");
                writer.write("id = "+player.getId()+"\n");
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //convert file to string( should be splited by # )
    public String fileToString(File file){
        String data="";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                data+=scanner.next()+"#";
            }
            return data;
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        return null;
    }

    public String showScores(){
        try {
            String scores="";
            Scanner scanner = new Scanner(highestScores);
            while (scanner.hasNext()){
                scores+=scanner.nextLine()+"\n";
            }
            return scores;
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    public HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(hm.entrySet());
        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
