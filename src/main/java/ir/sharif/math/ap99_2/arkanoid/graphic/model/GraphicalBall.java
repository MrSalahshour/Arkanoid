package ir.sharif.math.ap99_2.arkanoid.graphic.model;


import ir.sharif.math.ap99_2.arkanoid.graphic.ImageLoader;
import ir.sharif.math.ap99_2.arkanoid.models.BallPosition;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

import java.awt.*;

public class GraphicalBall extends GraphicalModel {
    private boolean health;
    private double xVelocity;
    private double yVelocity;
    private boolean fireBall; //set it frequently by update from logic
    private final int size;
    private BallPosition position; //set it frequently by update from logic
    private final Image image;


    public GraphicalBall(boolean health, double xVelocity, double yVelocity, int size, boolean fireBall,BallPosition position) {
        this.health = health;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.size = size;
        this.fireBall = fireBall;
        this.image = ImageLoader.getImage("ball");
        this.position = position;
    }

    public boolean isHealth() {
        return health;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public boolean isFireBall() {
        return fireBall;
    }

    public void setFireBall(boolean fireBall) {
        this.fireBall = fireBall;
    }

    public int getSize() {
        return size;
    }

    public BallPosition getPosition() {
        return position;
    }

    public void setPosition(BallPosition position) {
        this.position = position;
    }

    private int getX(){
        return (int) Math.floor(getPosition().getX());
    }
    private int getY(){
        return (int) Math.floor(getPosition().getY());
    }

    public Image getImage() {
        return image;
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        graphics2D.drawImage(image,getX(),getY(),getSize(), getSize(), null);

    }
}
