package ir.sharif.math.ap99_2.arkanoid.models.prizes;

import ir.sharif.math.ap99_2.arkanoid.models.GameState;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.PrizeBreak;


public class BigBoard extends Prize {


    public BigBoard(PrizeBreak prizeBreak, GameState gameState) {
        super(prizeBreak,gameState);

    }

    @Override
    public void applyPrize() {
        getGameState().getBoard().applyOnWidth(20);
        setTaken(true);
        setAvailable(false);
    }
    public String getName(){
        return "BigBoard";
    }
}
