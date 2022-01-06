package ir.sharif.math.ap99_2.arkanoid.models.breaks;

import ir.sharif.math.ap99_2.arkanoid.models.Player;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

import javax.swing.*;

public class FlashingBreak extends Break {
    private boolean visible;
    private final Timer visibleTimer;

    public FlashingBreak(Player player) {
        super(player);
        this.visible=true;
        this.score = 3;
        this.setPosition(new Position(0,0));
        this.visibleTimer = new Timer(2000, e -> refreshVisible());
        this.visibleTimer.start();
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Timer getVisibleTimer() {
        return visibleTimer;
    }

    public void refreshVisible(){
        this.visible = !this.visible;
    }
    public String getName(){
        return "Flashing";
    }


}
