package ir.sharif.math.ap99_2.arkanoid.logic;

import ir.sharif.math.ap99_2.arkanoid.models.*;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.*;
import ir.sharif.math.ap99_2.arkanoid.models.prizes.*;



public class GameStateBuilder {
    private String data;
    private final String[] parts;
    private int start;
    private boolean fastBall=false;
    private boolean slowBall=false;

    public GameStateBuilder(String data) {
        this.data = data;
        this.parts = data.split("#");
        this.start = 0;
    }

    public void completeGameState(GameState gameState){
        loadDelay();
        loadBreaks(gameState);
        loadAvailablePrizes(gameState);
        loadBalls(gameState);
        loadBoard(gameState);

    }
    public void loadDelay(){
        Zero:
        for (int i = 0; i <parts.length ; i++) {
            switch (parts[i]){
                case "BreakDelay:":
                    Delay.setBreakDelay(getInt(parts[i+1]));
                    break;
                case "FastBallDelay:":
                    fastBall = true;
                    Delay.setFastBallDelay(getInt(parts[i+1]));
                    break;
                case "SlowBallDelay:":
                    slowBall = true;
                    Delay.setSlowBallDelay(getInt(parts[i+1]));
                    break;
                case "DizzyBoardDelay:":
                    Delay.setDizzyBoardDelay(getInt(parts[i+1]));
                    this.start = i+1;
                    break Zero;
            }
        }
    }
    public void loadBreaks(GameState gameState){
        First:
        for (int i = start; i <parts.length ; i++) {
            switch (parts[i]){
                case "Flashing":
                    FlashingBreak flashingBreak = new FlashingBreak(gameState.getPlayer());
                    flashingBreak.setVisible(checkBoolean(parts[i+2]));
                    flashingBreak.setPosition(new Position(getInt(parts[i+4]),getInt(parts[i+6])));
                    gameState.getBreaks().add(flashingBreak);
                    break;
                case "Prize":
                    PrizeBreak prizeBreak = new PrizeBreak(gameState.getPlayer(),null);
                    prizeBreak.setPrize(checkPrize(prizeBreak,parts[i+2],gameState));
                    prizeBreak.setPosition(new Position(getInt(parts[i+4]),getInt(parts[i+6])));
                    gameState.getBreaks().add(prizeBreak);
                    break;
                case "Unvisible":
                    UnvisibleBreak unvisibleBreak = new UnvisibleBreak(gameState.getPlayer());
                    unvisibleBreak.setPosition(new Position(getInt(parts[i+2]),getInt(parts[i+4])));
                    gameState.getBreaks().add(unvisibleBreak);
                    break;
                case "Glass":
                    GlassBreak glassBreak = new GlassBreak(gameState.getPlayer());
                    glassBreak.setPosition(new Position(getInt(parts[i+2]),getInt(parts[i+4])));
                    gameState.getBreaks().add(glassBreak);
                    break;
                case "Wooden":
                    WoodenBreak woodenBreak = new WoodenBreak(gameState.getPlayer());
                    woodenBreak.setHit(checkBoolean(parts[i+2]));
                    woodenBreak.setPosition(new Position(getInt(parts[i+4]),getInt(parts[i+6])));
                    woodenBreak.setHit(false);
                    gameState.getBreaks().add(woodenBreak);
                    break;
                case "Prizes:":
                    this.start = i+1;
                    break First;
            }
        }
    }

    public void loadAvailablePrizes(GameState gameState){
        Second:
        for (int i = start; i < parts.length ; i++) {
            switch (parts[i]){
                case "BigBoard":
                    BigBoard bigBoard = new BigBoard(null,gameState);
                    bigBoard.setPosition(new Position(getInt(parts[i+2]),getInt(parts[i+4])));
                    gameState.getPrizes().add(bigBoard);
                    break;
                case "DizzyBoard":
                    DizzyBoard dizzyBoard = new DizzyBoard(null,gameState);
                    dizzyBoard.setPosition(new Position(getInt(parts[i+2]),getInt(parts[i+4])));
                    gameState.getPrizes().add(dizzyBoard);
                    break;
                case "FastBall":
                    FastBall fastBall = new FastBall(null,gameState);
                    fastBall.setPosition(new Position(getInt(parts[i+2]),getInt(parts[i+4])));
                    gameState.getPrizes().add(fastBall);
                    break;
                case "FireBall":
                    FireBall fireBall = new FireBall(null,gameState);
                    fireBall.setPosition(new Position(getInt(parts[i+2]),getInt(parts[i+4])));
                    gameState.getPrizes().add(fireBall);
                    break;
                case "MultiBall":
                    MultiBall multiBall = new MultiBall(null,gameState);
                    multiBall.setPosition(new Position(getInt(parts[i+2]),getInt(parts[i+4])));
                    gameState.getPrizes().add(multiBall);
                    break;
                case "RandomPrize":
                    RandomPrize randomPrize = new RandomPrize(null,gameState);
                    randomPrize.setPosition(new Position(getInt(parts[i+2]),getInt(parts[i+4])));
                    gameState.getPrizes().add(randomPrize);
                    break;
                case "SlowBall":
                    SlowBall slowBall = new SlowBall(null,gameState);
                    slowBall.setPosition(new Position(getInt(parts[i+2]),getInt(parts[i+4])));
                    gameState.getPrizes().add(slowBall);
                    break;
                case "SmallBoard":
                    SmallBoard smallBoard = new SmallBoard(null,gameState);
                    smallBoard.setPosition(new Position(getInt(parts[i+2]),getInt(parts[i+4])));
                    gameState.getPrizes().add(smallBoard);
                    break;
                case "Balls:":
                    this.start = i+1;
                    break Second;
            }
        }

    }

    public void loadBalls(GameState gameState){
        Third:
        for (int i = start; i <parts.length ; i++) {
            switch (parts[i]){
                case "x:":
                    Ball ball = new Ball(gameState.getPlayer());
                    ball.setPosition(new BallPosition(getInt(parts[i+1]),getInt(parts[i+3])));
                    ball.setHealthStage(getInt(parts[i+5]));
                    ball.setXVelocity(getDouble(parts[i+7]));
                    ball.setYVelocity(getDouble(parts[i+9]));
                    ball.setFireBall(checkBoolean(parts[i+11]));
                    ball.setFast(fastBall);
                    ball.setSlow(slowBall);
                    gameState.getBalls().add(ball);
                    break;
                case "Board:":
                    start = i+1;
                    break Third;
            }
        }
    }

    public void loadBoard(GameState gameState){
        for (int i = start; i <parts.length ; i++) {
            if (parts[i].equals("x:")){
                Board board = new Board(gameState.getPlayer());
                board.setPosition(new Position(getInt(parts[i+1]),getInt(parts[i+3])));
                board.setWidth(getInt(parts[i+5]));
                board.setDizzy(checkBoolean(parts[i+7]));
                gameState.setBoard(board);
            }
            if (parts[i].equals("Score:")){
                gameState.getPlayer().setTotalScore(getInt(parts[i+1]));
                //load player score
            }
        }
    }

    private boolean checkBoolean(String bool){
        if (bool.equals("true"))
            return true;
        if (bool.equals("false"))
            return false;
        return false;
    }

    private Prize checkPrize(PrizeBreak prizeBreak,String prizeName,GameState gameState){
        return switch (prizeName) {
            case "BigBoard" -> new BigBoard(prizeBreak,gameState);
            case "DizzyBoard" -> new DizzyBoard(prizeBreak,gameState);
            case "FastBall" -> new FastBall(prizeBreak,gameState);
            case "FireBall" -> new FireBall(prizeBreak,gameState);
            case "MultiBall" -> new MultiBall(prizeBreak,gameState);
            case "RandomPrize" -> new RandomPrize(prizeBreak,gameState);
            case "SlowBall" -> new SlowBall(prizeBreak,gameState);
            case "SmallBoard" -> new SmallBoard(prizeBreak,gameState);
            default -> null;
        };
    }
    private int getInt(String number){
        return Integer.parseInt(number);
    }
    private double getDouble(String number){
        return Double.parseDouble(number);
    }

}
