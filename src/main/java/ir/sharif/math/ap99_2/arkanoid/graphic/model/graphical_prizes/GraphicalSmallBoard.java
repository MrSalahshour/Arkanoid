package ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_prizes;

import ir.sharif.math.ap99_2.arkanoid.graphic.ImageLoader;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_breaks.GraphicalPrizeBreak;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

import java.awt.*;

public class GraphicalSmallBoard extends GraphicalPrize {

    private final Image image;

    public GraphicalSmallBoard(GraphicalPrizeBreak prizeBreak, int size,boolean available,boolean taken) {
        super(prizeBreak, size,available,taken);
        this.image = ImageLoader.getImage("smallBoardPrize");

    }


    public Image getImage() {
        return image;
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        if (isAvailable()){
            graphics2D.drawImage(image, getPosition().getX(), getPosition().getY(),getSize(), getSize(), null);
        }
    }
}
