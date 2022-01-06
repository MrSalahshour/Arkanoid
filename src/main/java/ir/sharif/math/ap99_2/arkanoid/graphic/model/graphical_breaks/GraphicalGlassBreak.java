package ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_breaks;

import ir.sharif.math.ap99_2.arkanoid.graphic.ImageLoader;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalPlayer;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

import java.awt.*;

public class GraphicalGlassBreak extends GraphicalBreak {

    private final Image image;

    public GraphicalGlassBreak(int width, int height, Position position) {
        super(width, height, position);
        this.image = ImageLoader.getImage("glassBreak");
    }


    public Image getImage() {
        return image;
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        graphics2D.drawImage(image, getPosition().getX(), getPosition().getY(),getWidth(), getHeight(), null);
    }
}
