package ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_breaks;

import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalPlayer;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

import java.awt.*;

public class GraphicalUnvisibleBreak extends GraphicalBreak {


    public GraphicalUnvisibleBreak(int width, int height, Position position) {
        super(width, height, position);
    }

    @Override
    public void paint(Graphics2D graphics2D) {

    }
}
