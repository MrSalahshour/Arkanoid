package ir.sharif.math.ap99_2.arkanoid.models;

import ir.sharif.math.ap99_2.arkanoid.logic.Instances;
import ir.sharif.math.ap99_2.arkanoid.models.prizes.Prize;

public class Board  {
    private final Player player;
    private Position position;
    private int width;
    private final int height;
    private boolean dizzy;
    private boolean locked;
    private int dizzyDelay;

    public Board(Player player) {
        this.player = player;
        this.dizzy = false;
        this.locked = true;
        this.width = Instances.boardWidth();
        this.height = Instances.boardHeight();
        this.dizzyDelay = 10000;
    }

    public Player getPlayer() {
        return player;
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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void applyOnWidth(int pixel){
        setWidth(getWidth()+pixel);
    }

    public int getDizzyDelay() {
        return dizzyDelay;
    }

    public void setDizzyDelay(int dizzyDelay) {
        this.dizzyDelay = dizzyDelay;
    }

    public void moveToRight() {
        if (position.getX()+ width <=Instances.gamePanelWidth()-15)
            position.setX(position.getX()+15);

    }
    public void moveToLeft() {
        if (position.getX()>=15)
            position.setX(position.getX()-15);
    }
    public void checkPrize(){
        for (int i = 0; i <getPlayer().getGameState().getPrizes().size() ; i++) {
            Prize prize = getPlayer().getGameState().getPrizes().get(i);
            if (prize.getPosition().getY()+prize.getSize()>=position.getY()){
                if (prize.getPosition().getX()>=position.getX()
                        && prize.getPosition().getX()<=position.getX()+width){
                    prize.applyPrize();
                    prize.stopMovePrize();
                    getPlayer().getGameState().getPrizes().remove(prize);
                }
            }
        }
    }


}
