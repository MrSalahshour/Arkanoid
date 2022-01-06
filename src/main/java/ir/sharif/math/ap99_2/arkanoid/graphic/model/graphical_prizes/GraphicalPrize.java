package ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_prizes;

import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalModel;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_breaks.GraphicalPrizeBreak;
import ir.sharif.math.ap99_2.arkanoid.models.Position;


public abstract class GraphicalPrize extends GraphicalModel {
    private GraphicalPrizeBreak prizeBreak;
    private final int size;
    private Position position; //set it frequently by update from logic
    private boolean available; //set it frequently by update from logic
    private boolean taken; //set it frequently by update from logic

    public GraphicalPrize(GraphicalPrizeBreak prizeBreak, int size,boolean available,boolean taken) {
        this.available = available;
        this.taken = taken;
        this.prizeBreak = prizeBreak;
        this.size = size;
        if (prizeBreak!=null)
            this.position = prizeBreak.getPosition();
    }

    public GraphicalPrizeBreak getPrizeBreak() {
        return prizeBreak;
    }

    public int getSize() {
        return size;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public void setPrizeBreak(GraphicalPrizeBreak prizeBreak) {
        this.prizeBreak = prizeBreak;
    }
}
