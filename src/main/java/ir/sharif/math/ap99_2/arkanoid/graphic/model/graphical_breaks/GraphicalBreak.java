package ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_breaks;

import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalModel;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalPlayer;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

import java.awt.*;


public abstract class GraphicalBreak extends GraphicalModel {
    private final int width;
    private final int height;
    private boolean destroyed; //set it frequently by update from logic
    private Position position; //set it frequently by update from logic
    public GraphicalBreak( int width, int height, Position position) {
        this.width = width;
        this.height = height;
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    @Override
    public void paint(Graphics2D graphics2D) {

    }
}
