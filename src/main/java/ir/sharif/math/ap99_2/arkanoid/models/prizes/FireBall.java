package ir.sharif.math.ap99_2.arkanoid.models.prizes;

import ir.sharif.math.ap99_2.arkanoid.models.Ball;
import ir.sharif.math.ap99_2.arkanoid.models.GameState;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.PrizeBreak;

import java.util.LinkedList;

public class FireBall extends Prize {
    public FireBall(PrizeBreak prizeBreak, GameState gameState) {
        super(prizeBreak,gameState);
    }

    @Override
    public void applyPrize() {
        LinkedList<Ball> playerBalls = getGameState().getBalls();
        for (Ball playerBall : playerBalls) {
            playerBall.setFireBall(true);
        }
        setAvailable(false);
        setTaken(true);

    }
    public String getName(){
        return "FireBall";
    }
}
