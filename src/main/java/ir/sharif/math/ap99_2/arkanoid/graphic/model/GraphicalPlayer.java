package ir.sharif.math.ap99_2.arkanoid.graphic.model;

import java.awt.*;

public class GraphicalPlayer extends GraphicalModel {
    private String name;
    private int totalScore; //set it frequently by update from logic
    private final String id;

    public GraphicalPlayer(String name, String id,int totalScore) {
        this.name = name;
        this.id = id;
        this.totalScore = totalScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getId() {
        return id;
    }
    @Override
    public void paint(Graphics2D graphics2D) {

    }
}
