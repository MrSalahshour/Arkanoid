package ir.sharif.math.ap99_2.arkanoid.models.prizes;

import ir.sharif.math.ap99_2.arkanoid.models.Ball;
import ir.sharif.math.ap99_2.arkanoid.models.GameState;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.PrizeBreak;

public class MultiBall extends Prize {
    public MultiBall(PrizeBreak prizeBreak, GameState gameState) {
        super(prizeBreak,gameState);
    }

    @Override
    public void applyPrize() {
        addBalls();
    }
    public void addBalls(){
        for (int i = 0; i <2 ; i++) {
            Ball ball =new Ball(getGameState().getPlayer());
            ball.setXVelocity(-4);
            ball.setYVelocity(-4);
            if (i==1){
                ball.getPosition().setX(ball.getPosition().getX()+30);
                ball.getPosition().setY(ball.getPosition().getY()+30);
            }
            getGameState().getBalls().add(ball);
        }
        setAvailable(false);
        setTaken(true);
    }
    public String getName(){
        return "MultiBall";
    }
}
