package ir.sharif.math.ap99_2.arkanoid.logic;

import ir.sharif.math.ap99_2.arkanoid.graphic.GraphicalAgent;
import ir.sharif.math.ap99_2.arkanoid.models.Ball;
import ir.sharif.math.ap99_2.arkanoid.models.GameState;
import ir.sharif.math.ap99_2.arkanoid.models.MyTimer;
import ir.sharif.math.ap99_2.arkanoid.models.Player;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.Break;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.FlashingBreak;
import ir.sharif.math.ap99_2.arkanoid.models.prizes.Prize;

import javax.swing.*;
import java.io.File;
import java.util.TimerTask;

public class LogicalAgent {
    private GameState gameState;
    private final GraphicalAgent graphicalAgent;
    private final ModelLoader modelLoader;
    private final Timer ballLoses;
    private final Timer ballMove;
    private final Timer endGame;
    private final Timer moveBreaks;
    private Timer delayMoveBreaks;
    private final Timer delayFastBall;
    private final Timer delaySlowBall;
    private final Timer delayDizzyBoard;
    private TimerTask delayFBTask;
    private TimerTask delaySBTask;
    private TimerTask delayDBTask;
    private boolean ballSpeed;



    public LogicalAgent() {
        this.graphicalAgent = new GraphicalAgent(this);
        this.modelLoader = new ModelLoader();
        this.gameState = loadGameState();
        this.ballLoses = new Timer(1, e -> ballLosesTask());
        this.ballMove = new Timer(15, e -> ballMoveTask());
        this.endGame = new Timer(1, e -> endGameTask());
        this.moveBreaks = new Timer(10000, e -> moveBreaksTask());
        this.delayMoveBreaks = new Timer(Delay.getBreakDelay(), e-> delayMoveBreaksTask());
        this.delayFastBall = new Timer(1, e-> delayFastBallTask());
        this.delaySlowBall = new Timer(1, e-> delaySlowBallTask());
        this.delayDizzyBoard = new Timer(1, e-> delayDizzyBoardTask());
        this.ballSpeed = false;

    }

    public GameState getGameState() {
        return gameState;
    }

    public void initialize() {
        graphicalAgent.initialize(gameState);
        Timer updateGraphic = new Timer(1, e -> graphicalAgent.update(gameState));
        updateGraphic.start();
    }

    public GameState loadGameState() {
        Player player = modelLoader.loadPlayer(graphicalAgent.getPlayerName());
        GameState gameState = new GameState(player);
        modelLoader.completeDefaultGS(gameState);
        player.setGameState(gameState);
        return gameState;
    }

    public void moveBoardLeft() {
        getGameState().getBoard().moveToLeft();
    }

    public void moveBoardRight() {
        getGameState().getBoard().moveToRight();
    }

    public void gameOver() {
        stopGame();
        Player player = getGameState().getPlayer();
        graphicalAgent.playerLose(player.getTotalScore());
        modelLoader.saveFinishedGame(player);
        player.setTotalScore(0);
        GameState gameState = new GameState(this.gameState.getPlayer());
        modelLoader.completeDefaultGS(gameState);
        player.setGameState(gameState);
        this.gameState = gameState;
        initialize();
    }

    public void loadRestOfGame() {
        if (gameState.isBallLost()) {
            gameState.setBallLost(false);
            stopGame();
            gameState.getBalls().get(0).getPosition().setX(Instances.ballX());
            gameState.getBalls().get(0).getPosition().setY(Instances.ballY());
            gameState.getBalls().get(0).setYVelocity(gameState.getBalls().get(0).getYVelocity()*-1);
            gameState.getBoard().getPosition().setX(Instances.boardX());
            gameState.getBoard().getPosition().setY(Instances.boardY());
            for (int i = 0; i < gameState.getPrizes().size(); i++) {
                Prize prize = gameState.getPrizes().get(i);
                gameState.getPrizes().remove(prize);
            }
            for (Ball b : gameState.getBalls()) {
                if (b.isFast()){
                    b.decreaseBallSpeed();
                    b.setFast(false);
                }
                if (b.isSlow()){
                    b.increaseBallSpeed();
                    b.setSlow(false);
                }
            }
            gameState.getBoard().setDizzy(false);
        }
    }

    public void resumeGame() {
        Delay.setStartBreakD(System.currentTimeMillis());
        gameState.getBoard().setLocked(false);
        ballMove.start();
        delayMoveBreaks.start();
        delayFastBall.start();
        delayDizzyBoard.start();
        delaySlowBall.start();
        for (int i = 0; i < gameState.getPrizes().size(); i++) {
            gameState.getPrizes().get(i).movePosition();
        }
        flashingVisibleStatus(true);
        checkState();
    }

    public void stopGame() {
        stopGameDelayTasks();
        ballMove.stop();
        gameState.getBoard().setLocked(true);
        delayMoveBreaks.stop();
        delayMoveBreaks = new Timer(Delay.getBreakDelay(), e-> delayMoveBreaksTask());
        moveBreaks.stop();
        flashingVisibleStatus(false);
        for (int i = 0; i < gameState.getPrizes().size(); i++) {
            if (gameState.getPrizes().get(i).isAvailable())
                gameState.getPrizes().get(i).stopMovePrize();
        }
    }



    public void playGame() {
        Delay.setStartBreakD(System.currentTimeMillis());
        gameState.getBoard().setLocked(false);
        ballMove.start();
        delayMoveBreaks.start();
        delayFastBall.start();
        delayDizzyBoard.start();
        delaySlowBall.start();
        flashingVisibleStatus(true);
        checkState();
        for (int i = 0; i < gameState.getPrizes().size(); i++) {
            gameState.getPrizes().get(i).movePosition();
        }
    }

    public void loadUnfinishedGame() {
        File file = graphicalAgent.getUnfinishedGame();
        if (file != null){
            GameState gameState = modelLoader.loadUnfinishedGame(file,this.gameState.getPlayer());
            for (int i = 0; i <gameState.getPrizes().size() ; i++) {
                gameState.getPrizes().get(i).setGameState(gameState);
            }
            this.gameState.getPlayer().setGameState(gameState);
            this.gameState = gameState;
            delayMoveBreaks = new Timer(Delay.getBreakDelay(), e-> delayMoveBreaksTask());
        }
    }

    public void saveUnfinishedGame() {
        stopGameDelayTasks();
        String gameName = graphicalAgent.getGameFileName();
        modelLoader.saveUnfinishedGame(gameState.getPlayer(), gameName);
    }

    public String showScores() {
        String scores = "Current Score: " + gameState.getPlayer().getTotalScore() + "\n" + "\n";
        scores += modelLoader.showScores();
        return scores;
    }

    public void ballLosesTask() {
        if (gameState.isBallLost()) {
            if (gameState.isFinished())
                gameOver();
            else
                loadRestOfGame();
        }
    }

    public void endGameTask() {
        if (gameState.isFinished()) gameOver();
    }

    public void moveBreaksTask() {
        Break.movePosition(gameState.getBreaks(), gameState);
        Delay.setStartBreakD(System.currentTimeMillis());
        if (ballSpeed){
            for (Ball b:gameState.getBalls()) {
                b.increaseBallSpeed();
            }
            ballSpeed = false;
        }
        else
            ballSpeed = true;
    }

    public void ballMoveTask(){
        for (int i = 0; i <gameState.getBalls().size() ; i++) {
            gameState.getBalls().get(i).movePosition();
        }
    }

    public void checkState() {
        endGame.start();
        ballLoses.start();
    }

    public void delayMoveBreaksTask(){
        moveBreaksTask();
        moveBreaks.start();
        delayMoveBreaks.stop();
    }

    public void delayFastBallTask() {
        if (gameState.getBalls().get(0).isFast()){
            Delay.setStartFastBallD(System.currentTimeMillis());
            delayFastBall.stop();
            delayFBTask = new TimerTask() {
                @Override
                public void run() {
                    for (Ball ball : gameState.getBalls()) {
                        ball.decreaseBallSpeed();
                        ball.setFast(false);
                    }
                    Delay.setFastBallDelay(10000);
                    delayFastBall.start();
                }
            };
            MyTimer.getMyTimer().getTimer().schedule(delayFBTask,Delay.getFastBallDelay());
        }
    }

    public void delaySlowBallTask(){
        if (gameState.getBalls().get(0).isSlow()){
            Delay.setStartSlowBallD(System.currentTimeMillis());
            delaySlowBall.stop();
            delaySBTask = new TimerTask() {
                @Override
                public void run() {
                    for (Ball ball : gameState.getBalls()) {
                        ball.increaseBallSpeed();
                        ball.setSlow(false);
                    }
                    Delay.setSlowBallDelay(10000);
                    delaySlowBall.start();
                }
            };
            MyTimer.getMyTimer().getTimer().schedule(delaySBTask,Delay.getSlowBallDelay());
        }
    }

    public void delayDizzyBoardTask(){
        if (gameState.getBoard().isDizzy()){
            Delay.setStartDizzyBoardD(System.currentTimeMillis());
            delayDizzyBoard.stop();
            delayDBTask = new TimerTask() {
                @Override
                public void run() {
                    gameState.getBoard().setDizzy(false);
                    Delay.setDizzyBoardDelay(10000);
                    delayDizzyBoard.start();
                }
            };
            MyTimer.getMyTimer().getTimer().schedule(delayDBTask,Delay.getDizzyBoardDelay());
        }
    }

    private void stopGameDelayTasks() {
        long currentTime = System.currentTimeMillis();
        Delay.setEndBreakD(currentTime);
        Delay.setBreakDelay(Delay.makeBreakD());
        if (gameState.getBoard().isDizzy()){
            Delay.setEndDizzyBoardD(currentTime);
            Delay.setDizzyBoardDelay(Delay.makeDizzyBoardD());
            delayDBTask.cancel();
        }
        if (gameState.getBalls().get(0).isFast()){
            Delay.setEndFastBallD(currentTime);
            Delay.setFastBallDelay(Delay.makeFastBallD());
            delayFBTask.cancel();
        }
        if (gameState.getBalls().get(0).isSlow()){
            Delay.setEndSlowBallD(currentTime);
            Delay.setSlowBallDelay(Delay.makeSlowBallD());
            delaySBTask.cancel();
        }
    }

    private void flashingVisibleStatus(boolean start){
        for (int i = 0; i <gameState.getBreaks().size() ; i++) {
            if (gameState.getBreaks().get(i).getName().equals("Flashing")){
                FlashingBreak flashingBreak = (FlashingBreak) gameState.getBreaks().get(i);
                if (start)
                    flashingBreak.getVisibleTimer().start();
                if (!start)
                    flashingBreak.getVisibleTimer().stop();
            }
        }
    }
}
