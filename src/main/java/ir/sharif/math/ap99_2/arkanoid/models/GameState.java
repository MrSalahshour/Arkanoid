package ir.sharif.math.ap99_2.arkanoid.models;

import ir.sharif.math.ap99_2.arkanoid.models.breaks.Break;
import ir.sharif.math.ap99_2.arkanoid.models.prizes.Prize;

import java.util.LinkedList;

public class GameState {
    private final Player player;
    private final LinkedList<Ball> balls;
    private LinkedList<Break> breaks;
    private LinkedList<Prize> prizes;
    private Board board;
    private boolean finished;
    private boolean ballLost;

    public GameState(Player player) {
        this.player = player;
        this.finished = false;
        this.ballLost = false;
        this.balls = new LinkedList<>();
        this.breaks = new LinkedList<>();
        this.prizes = new LinkedList<>();


    }

    public Player getPlayer() {
        return player;
    }

    public LinkedList<Ball> getBalls() {
        return balls;
    }

    public LinkedList<Break> getBreaks() {
        return breaks;
    }

    public Board getBoard() {
        return board;
    }

    public void setBreaks(LinkedList<Break> breaks) {
        this.breaks = breaks;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public LinkedList<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(LinkedList<Prize> prizes) {
        this.prizes = prizes;
    }

    public boolean isBallLost() {
        return ballLost;
    }

    public void setBallLost(boolean ballLost) {
        this.ballLost = ballLost;
    }

}
