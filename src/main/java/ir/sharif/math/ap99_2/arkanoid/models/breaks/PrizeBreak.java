package ir.sharif.math.ap99_2.arkanoid.models.breaks;

import ir.sharif.math.ap99_2.arkanoid.models.Player;
import ir.sharif.math.ap99_2.arkanoid.models.Position;
import ir.sharif.math.ap99_2.arkanoid.models.prizes.Prize;

public class PrizeBreak extends Break {

    private Prize prize;

    public PrizeBreak(Player player, Prize prize) {
        super(player);
        this.score=0;
        this.prize = prize;
        this.setPosition(new Position(240,0));
    }

    public void destroy(){
        getPlayer().applyScore(score);
        getPrize().setAvailable(true);
        getPrize().setPosition(new Position(getPosition().getX(),getPosition().getY()));
        getPlayer().getGameState().getPrizes().add(getPrize());
        getPrize().movePosition();
        getPrize().setPrizeBreak(null);
        getPlayer().getGameState().getBreaks().remove(this);
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public String getName(){
        return "Prize";
    }
}
