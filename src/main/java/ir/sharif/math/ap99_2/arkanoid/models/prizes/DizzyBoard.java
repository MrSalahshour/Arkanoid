package ir.sharif.math.ap99_2.arkanoid.models.prizes;

import ir.sharif.math.ap99_2.arkanoid.models.GameState;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.PrizeBreak;


public class DizzyBoard extends Prize {

    public DizzyBoard(PrizeBreak prizeBreak,GameState gameState) {
        super(prizeBreak,gameState);
    }

    @Override
    public void applyPrize() {
        if (!getGameState().getBoard().isDizzy()){
            getGameState().getBoard().setDizzy(true);
            setAvailable(false);
            setTaken(true);
        }
    }


    public String getName(){
        return "DizzyBoard";
    }
}
