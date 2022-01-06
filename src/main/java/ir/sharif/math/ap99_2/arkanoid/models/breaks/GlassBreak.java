package ir.sharif.math.ap99_2.arkanoid.models.breaks;

import ir.sharif.math.ap99_2.arkanoid.models.Player;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

public class GlassBreak extends Break {

    public GlassBreak(Player player) {
        super(player);
        this.score = 1;
        this.setPosition(new Position(120,0));
    }
    public String getName(){
        return "Glass";
    }
}
