package ir.sharif.math.ap99_2.arkanoid.models.prizes;

import ir.sharif.math.ap99_2.arkanoid.models.GameState;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.PrizeBreak;

public class SmallBoard extends Prize {

    public SmallBoard(PrizeBreak prizeBreak,GameState gameState) {
        super(prizeBreak,gameState);
    }

    @Override
    public void applyPrize() {
        getGameState().getBoard().applyOnWidth(-20);
        setAvailable(false);
        setTaken(true);
    }
    public String getName(){
        return "SmallBoard";
    }
}
