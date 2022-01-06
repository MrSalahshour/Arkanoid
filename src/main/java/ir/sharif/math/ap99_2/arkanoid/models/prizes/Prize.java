package ir.sharif.math.ap99_2.arkanoid.models.prizes;

import ir.sharif.math.ap99_2.arkanoid.logic.Instances;
import ir.sharif.math.ap99_2.arkanoid.models.GameState;
import ir.sharif.math.ap99_2.arkanoid.models.MyTimer;
import ir.sharif.math.ap99_2.arkanoid.models.Position;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.PrizeBreak;
import ir.sharif.math.ap99_2.arkanoid.util.Config;

import java.util.Random;
import java.util.TimerTask;


public abstract class Prize {

    private PrizeBreak prizeBreak;
    private Position position;
    private boolean available;
    private boolean taken;
    private TimerTask movePrize;
    private final int size;
    private GameState gameState;




    public Prize(PrizeBreak prizeBreak,GameState gameState) {
        this.gameState = gameState;
        this.prizeBreak = prizeBreak;
        this.available = true;
        this.taken = false;
        this.size = Config.getConfig("infoPrize").getProperty(Integer.class,"size");
        this.movePrize = new TimerTask() {
            @Override
            public void run() {
                prizeMoveTask();
            }
        };
    }

    public PrizeBreak getPrizeBreak() {
        return prizeBreak;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public TimerTask getMovePrize() {
        return movePrize;
    }

    public int getSize() {
        return size;
    }

    public void setPrizeBreak(PrizeBreak prizeBreak) {
        this.prizeBreak = prizeBreak;
    }

    public void prizeMoveTask(){
        position.setY(position.getY()+2);
        gameState.getBoard().checkPrize();
        checkPosition();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    //*make sure to update graphic after this function*//
    public abstract void applyPrize();

    public void movePosition() {
        MyTimer.getMyTimer().getTimer().scheduleAtFixedRate(movePrize,0,15);
    }

    public void stopMovePrize(){
        getMovePrize().cancel();
        movePrize = new TimerTask() {
            @Override
            public void run() {
                prizeMoveTask();
            }
        };
    }

    public void checkPosition() {
        if (position.getY()> Instances.gamePanelHeight()-Instances.boardHeight()){
            if (getPosition().getX()<gameState.getBoard().getPosition().getX() ||
                    getPosition().getX()>gameState.getBoard().getPosition().getX()+gameState.getBoard().getWidth()){
            stopMovePrize();
            gameState.getPrizes().remove(this);
            }
        }
    }
    public String getName(){
        return "prize";
    }
    public static Prize generatePrize(PrizeBreak prizeBreak){
        Random random = new Random();
        int res = random.nextInt(8);
        return switch (res) {
            case 0 -> new BigBoard(prizeBreak, prizeBreak.getPlayer().getGameState());
            case 1 -> new DizzyBoard(prizeBreak, prizeBreak.getPlayer().getGameState());
            case 2 -> new FastBall(prizeBreak, prizeBreak.getPlayer().getGameState());
            case 3 -> new FireBall(prizeBreak, prizeBreak.getPlayer().getGameState());
            case 4 -> new MultiBall(prizeBreak, prizeBreak.getPlayer().getGameState());
            case 5 -> new RandomPrize(prizeBreak, prizeBreak.getPlayer().getGameState());
            case 6 -> new SlowBall(prizeBreak, prizeBreak.getPlayer().getGameState());
            case 7 -> new SmallBoard(prizeBreak, prizeBreak.getPlayer().getGameState());
            default -> null;
        };
    }

}
