package ir.sharif.math.ap99_2.arkanoid.models.breaks;

import ir.sharif.math.ap99_2.arkanoid.models.Player;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

public class UnvisibleBreak extends GlassBreak {
    public UnvisibleBreak(Player player) {
        super(player);
        this.score=4;
        this.setPosition(new Position(360,0));

    }
    public String getName(){
        return "Unvisible";
    }
}
