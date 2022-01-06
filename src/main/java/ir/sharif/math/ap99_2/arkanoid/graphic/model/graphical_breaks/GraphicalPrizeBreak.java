package ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_breaks;

import ir.sharif.math.ap99_2.arkanoid.graphic.ImageLoader;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_prizes.GraphicalPrize;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

import java.awt.*;

public class GraphicalPrizeBreak extends GraphicalBreak {
    private final Image image;
    private GraphicalPrize graphicalPrize;

    public GraphicalPrizeBreak(int width, int height, Position position) {
        super(width, height, position);
        this.image = ImageLoader.getImage("prizeBreak");
    }


    public Image getImage() {
        return image;
    }

    public GraphicalPrize getGraphicalPrize() {
        return graphicalPrize;
    }

    public void setGraphicalPrize(GraphicalPrize graphicalPrize) {
        this.graphicalPrize = graphicalPrize;
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        graphics2D.drawImage(image, getPosition().getX(), getPosition().getY(),getWidth(), getHeight(), null);
    }
}
