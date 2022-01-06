package ir.sharif.math.ap99_2.arkanoid.models;



public class Player {
    private final String name;
    private int totalScore;
    private GameState gameState;
    private final String id;

    public Player(String name,String id) {
        this.name = name;
        this.totalScore = 0;
        this.id= id;

    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void applyScore(int score){
        setTotalScore(totalScore+score);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

}
