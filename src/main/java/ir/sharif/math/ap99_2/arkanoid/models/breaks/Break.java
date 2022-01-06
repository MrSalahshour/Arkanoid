package ir.sharif.math.ap99_2.arkanoid.models.breaks;

import ir.sharif.math.ap99_2.arkanoid.models.GameState;
import ir.sharif.math.ap99_2.arkanoid.models.Player;
import ir.sharif.math.ap99_2.arkanoid.models.Position;
import ir.sharif.math.ap99_2.arkanoid.models.prizes.Prize;
import ir.sharif.math.ap99_2.arkanoid.util.Config;

import java.util.LinkedList;


public abstract class Break {
    private final Player player;
    protected int score;
    private Position position;
    private final int width;
    private final int height;

    public Break(Player player) {
        this.player=player;
        Config config = Config.getConfig("infoBreak");
        this.width = config.getProperty(Integer.class,"width");
        this.height = config.getProperty(Integer.class,"height");
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void breakMoveTask(){
        position.setY(position.getY()+25);
        checkPosition();
    }

    public void destroy(){
        getPlayer().applyScore(score);
        getPlayer().getGameState().getBreaks().remove(this);
    }

    public static void movePosition(LinkedList<Break> breaks,GameState gameState) {
        for (Break aBreak : breaks) {
            aBreak.breakMoveTask();
        }
        addBreaksToGS(gameState);
    }

    public void checkPosition() {
        if (position.getY()>getPlayer().getGameState().getBoard().getPosition().getY()){
            getPlayer().getGameState().setFinished(true);
        }
    }

    public static void addBreaksToGS(GameState gameState){
        gameState.getBreaks().add(generateFlashingB(gameState.getPlayer()));
        gameState.getBreaks().add(generateGlassB(gameState.getPlayer()));
        gameState.getBreaks().add(generatePrizeB(gameState.getPlayer()));
        gameState.getBreaks().add(generateUnvisibleB(gameState.getPlayer()));
        gameState.getBreaks().add(generateWoodenB(gameState.getPlayer()));

    }
    private static FlashingBreak generateFlashingB (Player player){
        FlashingBreak flashingBreak = new FlashingBreak(player);
        flashingBreak.setVisible(true);
        return flashingBreak;
    }
    private static GlassBreak generateGlassB(Player player){
        return new GlassBreak(player);
    }

    private static PrizeBreak generatePrizeB(Player player){
        PrizeBreak prizeBreak = new PrizeBreak(player,null);
        prizeBreak.setPrize(Prize.generatePrize(prizeBreak));
        return prizeBreak;
    }

    private static UnvisibleBreak generateUnvisibleB(Player player){
        return new UnvisibleBreak(player);
    }

    private static WoodenBreak generateWoodenB(Player player){
        return new WoodenBreak(player);
    }

    public String getName(){
      return "Break";
    }


}
