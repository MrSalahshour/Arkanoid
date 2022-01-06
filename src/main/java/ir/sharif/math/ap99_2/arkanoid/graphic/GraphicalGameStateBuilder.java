package ir.sharif.math.ap99_2.arkanoid.graphic;

import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalBall;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalBoard;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalGameState;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalPlayer;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_breaks.*;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_prizes.*;
import ir.sharif.math.ap99_2.arkanoid.models.*;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.Break;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.FlashingBreak;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.PrizeBreak;
import ir.sharif.math.ap99_2.arkanoid.models.prizes.Prize;

import java.util.LinkedList;

public class GraphicalGameStateBuilder {
    private final GameState logicalGameState;


    public GraphicalGameStateBuilder(GameState gameState) {
        this.logicalGameState = gameState;
    }

    public GraphicalGameState build() {
        GraphicalPlayer gPlayer = createGPlayer(logicalGameState.getPlayer());
        LinkedList<GraphicalBall> gBalls = createGBalls(logicalGameState.getBalls());
        LinkedList<GraphicalPrize> gPrizes = createGPrizes(logicalGameState.getPrizes());
        LinkedList<GraphicalBreak> gBreaks = createGBreaks(logicalGameState.getBreaks());
        GraphicalBoard gBoard = createGBoard(logicalGameState.getBoard());
        return new GraphicalGameState(gPlayer,gBalls,gBreaks,gPrizes,gBoard);
    }

    public void update(GraphicalGameState graphicalGameState) {
        updateGBoard(logicalGameState.getBoard(),graphicalGameState.getBoard());
        updateGBalls(logicalGameState.getBalls(),graphicalGameState.getBalls());
        updateGPlayer(logicalGameState.getPlayer(),graphicalGameState.getPlayer());
        updateGBreaks(logicalGameState.getBreaks(),graphicalGameState.getBreaks());
        updateGPrizes(logicalGameState.getPrizes(), graphicalGameState.getPrizes());
    }

    private GraphicalBoard createGBoard(Board board) {
        return new GraphicalBoard(board.getWidth(), board.getHeight(), board.getPosition());
    }

    private void updateGBoard(Board board, GraphicalBoard gBoard) {
        gBoard.getPosition().setX(board.getPosition().getX());
        gBoard.getPosition().setY(board.getPosition().getY());
        gBoard.setWidth(board.getWidth());
        gBoard.setDizzy(board.isDizzy());
    }

    private LinkedList<GraphicalBall> createGBalls(LinkedList<Ball> balls) {
        LinkedList<GraphicalBall> gBalls = new LinkedList<>();
        for (Ball ball : balls) {
            gBalls.add(new GraphicalBall(ball.isHealth(), ball.getXVelocity(),
                    ball.getYVelocity(), ball.getSize(), ball.isFireBall(),ball.getPosition()));
        }
        return gBalls;
    }

    private void updateGBalls(LinkedList<Ball> balls,LinkedList<GraphicalBall> gBalls){
        setList(gBalls,createGBalls(balls));
    }

    private GraphicalPlayer createGPlayer(Player player) {
        return new GraphicalPlayer(player.getName(), player.getId(),player.getTotalScore());
    }

    private void updateGPlayer(Player player, GraphicalPlayer gPlayer){
        gPlayer.setTotalScore(player.getTotalScore());
    }

    private LinkedList<GraphicalBreak> createGBreaks(LinkedList<Break> breaks) {
        LinkedList<GraphicalBreak> gBreaks = new LinkedList<>();
        for (Break aBreak : breaks) {
            switch (aBreak.getName()) {
                case "Flashing" -> {
                    FlashingBreak flashingBreak = (FlashingBreak) aBreak;
                    gBreaks.add(new GraphicalFlashingBreak
                            (flashingBreak.getWidth(), flashingBreak.getHeight(),
                                    flashingBreak.getPosition(),flashingBreak.isVisible()));
                }
                case "Glass" -> gBreaks.add(new GraphicalGlassBreak
                        (aBreak.getWidth(), aBreak.getHeight(),aBreak.getPosition()));
                case "Prize" -> {
                    PrizeBreak prizeBreak = (PrizeBreak) aBreak;
                    GraphicalPrizeBreak gPrizeBreak = new GraphicalPrizeBreak(aBreak.getWidth(),
                            aBreak.getHeight(), aBreak.getPosition());
                    GraphicalPrize gPrize = checkGPrize(gPrizeBreak, prizeBreak.getPrize().getName(),
                            prizeBreak.getPrize().getSize());
                    gPrizeBreak.setGraphicalPrize(gPrize);
                    gBreaks.add(gPrizeBreak);
                }
                case "Unvisible" -> gBreaks.add(new GraphicalUnvisibleBreak(aBreak.getWidth(),
                        aBreak.getHeight(),aBreak.getPosition()));
                case "Wooden" -> gBreaks.add(new GraphicalWoodenBreak
                        (aBreak.getWidth(), aBreak.getHeight(),aBreak.getPosition()));
            }
        }
        return gBreaks;
    }

    private void updateGBreaks(LinkedList<Break> breaks,LinkedList<GraphicalBreak> gBreaks){
        setList(gBreaks,createGBreaks(breaks));
    }

    private LinkedList<GraphicalPrize> createGPrizes(LinkedList<Prize> prizes) {
        LinkedList<GraphicalPrize> gPrizes = new LinkedList<>();
        for (Prize prize : prizes) {
            switch (prize.getName()) {
                case "BigBoard" -> {
                    GraphicalBigBoard gBoard = new GraphicalBigBoard
                            (null, prize.getSize(),prize.isAvailable(),prize.isTaken());
                    gBoard.setPosition(prize.getPosition());
                    gPrizes.add(gBoard);
                }
                case "DizzyBoard" -> {
                    GraphicalDizzyBoard gDizzyBoard = new GraphicalDizzyBoard
                            (null, prize.getSize(),prize.isAvailable(),prize.isTaken());
                    gDizzyBoard.setPosition(prize.getPosition());
                    gPrizes.add(gDizzyBoard);
                }
                case "FastBall" -> {
                    GraphicalFastBall gFastBall = new GraphicalFastBall
                            (null, prize.getSize(),prize.isAvailable(),prize.isTaken());
                    gFastBall.setPosition(prize.getPosition());
                    gPrizes.add(gFastBall);
                }
                case "FireBall" -> {
                    GraphicalFireBall gFireBall = new GraphicalFireBall
                            (null, prize.getSize(),prize.isAvailable(),prize.isTaken());
                    gFireBall.setPosition(prize.getPosition());
                    gPrizes.add(gFireBall);
                }
                case "MultiBall" -> {
                    GraphicalMultiBall gMultiBall = new GraphicalMultiBall
                            (null, prize.getSize(),prize.isAvailable(),prize.isTaken());
                    gMultiBall.setPosition(prize.getPosition());
                    gPrizes.add(gMultiBall);
                }
                case "RandomPrize" -> {
                    GraphicalRandomPrize gRandomPrize = new GraphicalRandomPrize
                            (null, prize.getSize(),prize.isAvailable(),prize.isTaken());
                    gRandomPrize.setPosition(prize.getPosition());
                    gPrizes.add(gRandomPrize);
                }
                case "SlowBall" -> {
                    GraphicalSlowBall gSlowBall = new GraphicalSlowBall
                            (null, prize.getSize(),prize.isAvailable(),prize.isTaken());
                    gSlowBall.setPosition(prize.getPosition());
                    gPrizes.add(gSlowBall);
                }
                case "SmallBoard" -> {
                    GraphicalSmallBoard gSmallBoard = new GraphicalSmallBoard
                            (null, prize.getSize(),prize.isAvailable(),prize.isTaken());
                    gSmallBoard.setPosition(prize.getPosition());
                    gPrizes.add(gSmallBoard);
                }
            }
        }
        return gPrizes;
    }

    private void updateGPrizes(LinkedList<Prize> prizes,LinkedList<GraphicalPrize> gPrizes){
        setList(gPrizes,createGPrizes(prizes));
    }

    private GraphicalPrize checkGPrize(GraphicalPrizeBreak gPrizeBreak, String prizeName, int size) {
        return switch (prizeName) {
            case "BigBoard" -> new GraphicalBigBoard(gPrizeBreak, size,true,false);
            case "DizzyBoard" -> new GraphicalDizzyBoard(gPrizeBreak, size,true,false);
            case "FastBall" -> new GraphicalFastBall(gPrizeBreak, size,true,false);
            case "FireBall" -> new GraphicalFireBall(gPrizeBreak, size,true,false);
            case "MultiBall" -> new GraphicalMultiBall(gPrizeBreak, size,true,false);
            case "RandomPrize" -> new GraphicalRandomPrize(gPrizeBreak, size,true,false);
            case "SlowBall" -> new GraphicalSlowBall(gPrizeBreak, size,true,false);
            case "SmallBoard" -> new GraphicalSmallBoard(gPrizeBreak, size,true,false);
            default -> null;
        };
    }

    private <T> void setList(LinkedList<? super T> target, LinkedList<? extends T> values) {
        target.clear();
        target.addAll(values);
    }
}
