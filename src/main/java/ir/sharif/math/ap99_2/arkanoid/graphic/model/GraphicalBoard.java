package ir.sharif.math.ap99_2.arkanoid.graphic.model;

import ir.sharif.math.ap99_2.arkanoid.graphic.ImageLoader;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

import java.awt.*;

public class GraphicalBoard extends GraphicalModel {
    private Position position; //set it frequently by update from logic
    private int width;
    private final int height;
    private boolean dizzy; //set it frequently by update from logic
    private final Image image;

    public GraphicalBoard(int width, int height, Position position) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.dizzy = false;
        this.image = ImageLoader.getImage("board");
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDizzy() {
        return dizzy;
    }

    public void setDizzy(boolean dizzy) {
        this.dizzy = dizzy;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        graphics2D.drawImage(image, getPosition().getX(), getPosition().getY(),getWidth(), getHeight(), null);

    }
}
