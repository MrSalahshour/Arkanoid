package ir.sharif.math.ap99_2.arkanoid.models.breaks;

import ir.sharif.math.ap99_2.arkanoid.models.Player;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

public class WoodenBreak extends Break{
    private boolean hit;

    public WoodenBreak(Player player) {
        super(player);
        this.score = 2;
        this.hit = false;
        this.setPosition(new Position(480,0));
    }
    public void destroy(){
        if(hit){
            getPlayer().applyScore(score);
            getPlayer().getGameState().getBreaks().remove(this);
        }
        else {
            hit = true;
        }

    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public String getName(){
        return "Wooden";
    }
}
