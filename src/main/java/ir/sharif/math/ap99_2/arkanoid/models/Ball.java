package ir.sharif.math.ap99_2.arkanoid.models;

import ir.sharif.math.ap99_2.arkanoid.logic.Instances;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.Break;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.FlashingBreak;


public class Ball implements Movable {
    private final Player player;
    private int healthStage; //From 3 to 0.Zero means it dies.
    private boolean health;
    private BallPosition position;
    private double xVelocity;
    private double yVelocity;
    private boolean fireBall;
    private boolean fast;
    private boolean slow;
    private final int size;


    public Ball(Player player) {
        this.fireBall = false;
        this.player = player;
        this.healthStage = 3;
        this.health = true;
        this.xVelocity =4;
        this.yVelocity =4;
        this.position = new BallPosition(Instances.ballX(),Instances.ballY());
        this.size = Instances.ballSize();
        this.fast = false;
        this.slow = false;
    }

    public Player getPlayer() {
        return player;
    }

    public int getHealthStage() {
        return healthStage;
    }

    public void setHealthStage(int healthStage) {
        this.healthStage = healthStage;
    }

    public boolean isHealth() {
        return health;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }

    public void decreaseHealth() {
        healthStage--;
    }

    public boolean isFast() {
        return fast;
    }

    public void setFast(boolean fast) {
        this.fast = fast;
    }

    public boolean isSlow() {
        return slow;
    }

    public void setSlow(boolean slow) {
        this.slow = slow;
    }

    public boolean healthCheck() {
        if (healthStage == 0) {
            setHealth(false);
            return false;
        }
        return true;
    }

    public BallPosition getPosition() {
        return position;
    }

    public void setPosition(BallPosition position) {
        this.position = position;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(double yVelocity) {
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

    public void applyXVelocity(double xVelocity) {
        this.xVelocity+=xVelocity;
    }

    public void applyYVelocity(double yVelocity) {
        this.yVelocity+=yVelocity;
    }

    public void increaseBallSpeed(){
        if (this.xVelocity>0)
            xVelocity++;
        if (this.xVelocity<0)
            xVelocity--;
        if (this.yVelocity>0)
            yVelocity++;
        if (this.yVelocity<0)
            yVelocity--;

    }
    public void decreaseBallSpeed(){
        if (this.xVelocity>0)
            xVelocity--;
        if (this.xVelocity<0)
            xVelocity++;
        if (this.yVelocity>0)
            yVelocity--;
        if (this.yVelocity<0)
            yVelocity++;
    }

    @Override
    public void movePosition() {
        getPosition().setX(getPosition().getX()+getXVelocity());
        getPosition().setY(getPosition().getY()+getYVelocity());
        checkPosition();
    }



    //refresh graphic after this (update and check gameState automatically by timer).
    @Override
    public void checkPosition() {
        int boardY = getPlayer().getGameState().getBoard().getPosition().getY();
        int boardX = getPlayer().getGameState().getBoard().getPosition().getX();
        int boardWidth = getPlayer().getGameState().getBoard().getWidth();
        breakCheck();
        if (position.getX() >= Instances.gamePanelWidth() - size+1 || position.getX() <= 0){
            xVelocity = xVelocity * -1;

        }

        if (position.getY() >= Instances.gamePanelHeight() - size+1 || position.getY() <= 0){
            yVelocity = yVelocity * -1;
            setFireBall(false);
        }

        if (position.getY() >= boardY - size+1) {
            if (position.getX() >= boardX - size && position.getX() <= boardX + boardWidth)
                velocityHandler(boardX,boardWidth);
            else
                ballFail();

        }
    }
    private void velocityHandler(int boardX,double boardWidth){

        int distance = (int) (boardX + boardWidth / 2 - (getPosition().getX() + size/2));
        double distanceAbs = Math.abs(distance);
        double d = (1-(0.1*(distanceAbs/(boardWidth/2))));
        double velocity = Math.sqrt((xVelocity*xVelocity)+(yVelocity*yVelocity));

        if (distance>0) {
            yVelocity = -1*(d)*(yVelocity);
            xVelocity = -1*Math.sqrt((velocity*velocity)-(yVelocity*yVelocity));
        }

        if (distance<0){
            yVelocity = -1*(d)*(yVelocity);
            xVelocity = Math.sqrt((velocity*velocity)-(yVelocity*yVelocity));
        }
        if (distance ==0){
            yVelocity = yVelocity * -1;
        }

    }

    public void ballFail() {
        if (getPlayer().getGameState().getBalls().size() == 1) {
            decreaseHealth();
            if (!healthCheck()){
                getPlayer().getGameState().setFinished(true);
            }
            else {
                getPlayer().getGameState().setBallLost(true);
            }
        }
        else
            getPlayer().getGameState().getBalls().remove(this);
    }
    public boolean flashingCheck(Break aBreak){
        return aBreak.getName().equals("Flashing");
    }



    public void breakCheck(){
        for (int i = 0; i <getPlayer().getGameState().getBreaks().size() ; i++) {
            Break aBreak = getPlayer().getGameState().getBreaks().get(i);
            if (position.getY()<=aBreak.getPosition().getY()+aBreak.getHeight()){
                if (flashingCheck(aBreak)){
                    FlashingBreak flashingBreak = (FlashingBreak) aBreak;
                    if (!flashingBreak.isVisible())
                        continue;
                }
                if (position.getX()>=aBreak.getPosition().getX()
                        && position.getX()<=aBreak.getPosition().getX()+aBreak.getWidth()){
                    aBreak.destroy();
                    if (!isFireBall())
                        yVelocity = yVelocity * -1;
                }
                else if (position.getX()<=aBreak.getPosition().getX()
                        || position.getX()>=aBreak.getPosition().getX()+aBreak.getWidth()){
                    if (position.getX()>=aBreak.getPosition().getX()-size &&
                            position.getX()<=aBreak.getPosition().getX()+aBreak.getWidth()+size){
                        aBreak.destroy();
                        if (!isFireBall())
                            xVelocity = xVelocity * -1;
                    }
                }
            }
        }
    }

}
