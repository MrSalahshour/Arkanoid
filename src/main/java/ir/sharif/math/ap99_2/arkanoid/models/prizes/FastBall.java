package ir.sharif.math.ap99_2.arkanoid.models.prizes;

import ir.sharif.math.ap99_2.arkanoid.models.Ball;
import ir.sharif.math.ap99_2.arkanoid.models.GameState;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.PrizeBreak;

import java.util.LinkedList;

public class FastBall extends Prize {
    public FastBall(PrizeBreak prizeBreak, GameState gameState) {
        super(prizeBreak,gameState);
    }

    @Override
    public void applyPrize() {
        LinkedList<Ball> playerBalls = getGameState().getBalls();
        for (Ball playerBall : playerBalls) {
            if (!playerBall.isFast()){
                if (playerBall.getXVelocity()>0){
                    playerBall.applyXVelocity(+1);
                }
                if (playerBall.getXVelocity()<0){
                    playerBall.applyXVelocity(-1);
                }
                if (playerBall.getYVelocity()>0){
                    playerBall.applyYVelocity(+1);
                }
                if (playerBall.getYVelocity()<0){
                    playerBall.applyYVelocity(-1);
                }
                playerBall.setFast(true);
            }

        }
        setAvailable(false);
        setTaken(true);
    }
    public String getName(){
        return "FastBall";
    }

}
