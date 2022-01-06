package ir.sharif.math.ap99_2.arkanoid.graphic;

import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalGameState;
import ir.sharif.math.ap99_2.arkanoid.graphic.panel.GamePanel;
import ir.sharif.math.ap99_2.arkanoid.graphic.panel.MainPanel;
import ir.sharif.math.ap99_2.arkanoid.graphic.panel.MenuPanel;
import ir.sharif.math.ap99_2.arkanoid.logic.LogicalAgent;
import ir.sharif.math.ap99_2.arkanoid.models.GameState;

import javax.swing.*;
import java.io.File;

public class GraphicalAgent {
    private final LogicalAgent logicalAgent;
    private GraphicalGameState graphicalGameState;
    private Frame frame;
    private final Object paintLock;


    public GraphicalAgent(LogicalAgent logicalAgent) {
        this.logicalAgent = logicalAgent;
        this.paintLock = new Object();
    }
    /**
     * this method get game state and build or update a graphical models
     * and save this models somewhere
     */
    public void initialize(GameState gameState){
        this.graphicalGameState = new GraphicalGameStateBuilder(gameState).build();
        this.frame = initializePanels();

    }

    public void update(GameState gameState) {
        synchronized (paintLock) {
            new GraphicalGameStateBuilder(gameState).update(this.graphicalGameState);
        }
    }


    private Frame initializePanels() {
        GamePanel gamePanel = new GamePanel(this,graphicalGameState);
        MenuPanel menuPanel = new MenuPanel(this);
        MainPanel mainPanel = new MainPanel(gamePanel,menuPanel);
        Frame frame = Frame.getInstance();
        frame.setContentPane(mainPanel);
        return frame;
    }

    public String getPlayerName() {
        String result;
        do {
            result = JOptionPane.showInputDialog(frame, "Enter Your Name: ");
        } while (result == null || result.length() == 0);
        return result;
    }
    public String getGameFileName() {
        String result;
        do {
            result = JOptionPane.showInputDialog(frame, "Enter Your Game Name: ");
        } while (result == null || result.length() == 0);
        return result;
    }
    public File getUnfinishedGame(){
        String path = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(".\\resources\\ir\\sharif\\math\\ap99_2\\arkanoid" +
                "\\playersDirectory\\"+logicalAgent.getGameState().getPlayer().getName()));
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION){
           path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        if (path==null)
            return null;
        return new File(path);
    }

    public LogicalAgent getLogicalAgent() {
        return logicalAgent;
    }

    public GraphicalGameState getGraphicalGameState() {
        return graphicalGameState;
    }


    public Frame getFrame() {
        return frame;
    }

    public Object getPaintLock() {
        return paintLock;
    }

    public void moveBoardLeftRequest(){
        logicalAgent.moveBoardLeft();

    }

    public void moveBoardRightRequest(){
        logicalAgent.moveBoardRight();
    }

    public void playGameRequest(){
        logicalAgent.playGame();
    }

    public void stopGameRequest(){
        logicalAgent.stopGame();
    }

    public void resumeGameRequest(){
        logicalAgent.resumeGame();
    }

    public void loadGameRequest(){
        logicalAgent.loadUnfinishedGame();
    }

    public void saveGameRequest(){
        logicalAgent.saveUnfinishedGame();
    }

    public void scoresRequest(){
        String details = logicalAgent.showScores();
        JScrollPane scrollPane = new JScrollPane();
        JOptionPane.showMessageDialog(frame, details, "Scores", JOptionPane.INFORMATION_MESSAGE);
    }
    public void playerLose(int playerScore) {
        String message;
        message = "You Lost With " + playerScore + " Score";
        JOptionPane.showMessageDialog(frame, message,"GAME OVER",JOptionPane.INFORMATION_MESSAGE);
    }
}
