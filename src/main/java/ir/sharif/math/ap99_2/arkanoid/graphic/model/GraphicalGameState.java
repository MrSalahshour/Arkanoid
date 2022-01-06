package ir.sharif.math.ap99_2.arkanoid.graphic.model;


import ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_breaks.GraphicalBreak;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_prizes.GraphicalPrize;

import java.awt.*;
import java.util.LinkedList;

public class GraphicalGameState extends GraphicalModel {
    private final GraphicalPlayer player;
    private final LinkedList<GraphicalBall> balls;
    private final LinkedList<GraphicalBreak> breaks;
    private final LinkedList<GraphicalPrize> prizes;
    private final GraphicalBoard board;

    public GraphicalGameState(GraphicalPlayer player, LinkedList<GraphicalBall> balls,
                              LinkedList<GraphicalBreak> breaks, LinkedList<GraphicalPrize> prizes, GraphicalBoard board) {
        this.player = player;
        this.balls = balls;
        this.breaks = breaks;
        this.prizes = prizes;
        this.board = board;
    }

    public GraphicalPlayer getPlayer() {
        return player;
    }

    public LinkedList<GraphicalBall> getBalls() {
        return balls;
    }

    public LinkedList<GraphicalBreak> getBreaks() {
        return breaks;
    }

    public GraphicalBoard getBoard() {
        return board;
    }

    public LinkedList<GraphicalPrize> getPrizes() {
        return prizes;
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        balls.forEach(gt -> gt.paint(graphics2D));
        breaks.forEach(gt -> gt.paint(graphics2D));
        prizes.forEach(gt -> gt.paint(graphics2D));
        board.paint(graphics2D);
    }
}
