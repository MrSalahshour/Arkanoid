package ir.sharif.math.ap99_2.arkanoid.logic;

import ir.sharif.math.ap99_2.arkanoid.models.Ball;
import ir.sharif.math.ap99_2.arkanoid.models.Board;
import ir.sharif.math.ap99_2.arkanoid.models.GameState;
import ir.sharif.math.ap99_2.arkanoid.models.Position;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.Break;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.FlashingBreak;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.PrizeBreak;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.WoodenBreak;
import ir.sharif.math.ap99_2.arkanoid.models.prizes.Prize;

import java.io.File;
import java.io.FileWriter;

public class GameStateSaver {
    private final GameState gameState;
    private final File gameFile;

    public GameStateSaver(GameState gameState, File gameFile) {
        this.gameState = gameState;
        this.gameFile = gameFile;
    }
    private void saveDelay(FileWriter fileWriter){
        try {
            fileWriter.write("BreakDelay: "+Delay.getBreakDelay()+"\n");
            if (gameState.getBalls().get(0).isFast())
                fileWriter.write("FastBallDelay: "+Delay.getFastBallDelay()+"\n");
            if (gameState.getBalls().get(0).isSlow())
                fileWriter.write("SlowBallDelay: "+Delay.getSlowBallDelay()+"\n");
            if (gameState.getBoard().isDizzy())
                fileWriter.write("DizzyBoardDelay: "+Delay.getDizzyBoardDelay()+"\n");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void saveGameState(){
        try {
            FileWriter fileWriter = new FileWriter(gameFile);
            saveDelay(fileWriter);
            saveBreaks(fileWriter);
            savePrizes(fileWriter);
            saveBalls(fileWriter);
            saveBoard(fileWriter);
            saveScore(fileWriter);
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void saveBreaks(FileWriter fileWriter){
        try {
            fileWriter.write("Breaks:"+"\n");
            for (int i = 0; i <gameState.getBreaks().size() ; i++) {
                switch (gameState.getBreaks().get(i).getName()) {
                    case "Flashing" -> {
                        FlashingBreak flashingBreak = (FlashingBreak) gameState.getBreaks().get(i);
                        fileWriter.write(flashingBreak.getName() + "  visible: " + flashingBreak.isVisible() + "  x: "
                                + flashingBreak.getPosition().getX() + "  y: " + flashingBreak.getPosition().getY() + "\n");
                    }
                    case "Glass" -> {
                        Position breakPos = gameState.getBreaks().get(i).getPosition();
                        fileWriter.write(gameState.getBreaks().get(i).getName() + "  x: "
                                + breakPos.getX() + "  y: " + breakPos.getY() + "\n");
                    }
                    case "Prize" -> {
                        PrizeBreak prizeBreak = (PrizeBreak) gameState.getBreaks().get(i);
                        fileWriter.write(prizeBreak.getName() + "  name: " + prizeBreak.getPrize().getName() + "  x: "
                                + prizeBreak.getPosition().getX() + "  y: " + prizeBreak.getPosition().getY() + "\n");
                    }
                    case "Unvisible" -> {
                        Break aBreak = gameState.getBreaks().get(i);
                        fileWriter.write(aBreak.getName() + "  x: " + aBreak.getPosition().getX()
                                + "  y: " + aBreak.getPosition().getY() + "\n");
                    }
                    case "Wooden" -> {
                        WoodenBreak woodenBreak = (WoodenBreak) gameState.getBreaks().get(i);
                        Position WBreakPos = woodenBreak.getPosition();
                        fileWriter.write(woodenBreak.getName() + "  hit: "
                                + woodenBreak.isHit() + "  x: " + WBreakPos.getX() + "  y: " + WBreakPos.getY() + "\n");
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void savePrizes(FileWriter fileWriter){
        try {
            fileWriter.write("Prizes: "+"\n");
            for (int i = 0; i < gameState.getPrizes().size(); i++) {
                Prize prize = gameState.getPrizes().get(i);
                fileWriter.write(prize.getName()+"    x: "
                        +prize.getPosition().getX()+"  y: "+prize.getPosition().getY()+"\n");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saveBalls(FileWriter fileWriter){
        try {
            fileWriter.write("Balls: "+"\n");
            for (int i = 0; i <gameState.getBalls().size() ; i++) {
                Ball ball = gameState.getBalls().get(i);
                fileWriter.write("x: "+(int)ball.getPosition().getX()+"  y: "+(int)ball.getPosition().getY()+"  healthStage: "
                        +ball.getHealthStage() +"  xV: "+ball.getXVelocity()+"  yV: "+ball.getYVelocity()+
                        "  fire: "+ball.isFireBall()+"\n");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saveBoard(FileWriter fileWriter){
        try {
            fileWriter.write("Board: "+"\n");
            Board board = gameState.getBoard();
            fileWriter.write("x: "+board.getPosition().getX()+ "  y: "
                    +board.getPosition().getY()+"  width: "+board.getWidth()+"  dizzy: "+board.isDizzy()+"\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void saveScore(FileWriter fileWriter){
        try {
            fileWriter.write("Score: "+gameState.getPlayer().getTotalScore());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
