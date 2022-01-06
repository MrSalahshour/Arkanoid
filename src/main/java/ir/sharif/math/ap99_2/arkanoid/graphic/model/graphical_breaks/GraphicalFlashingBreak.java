package ir.sharif.math.ap99_2.arkanoid.graphic.model.graphical_breaks;

import ir.sharif.math.ap99_2.arkanoid.graphic.ImageLoader;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalPlayer;
import ir.sharif.math.ap99_2.arkanoid.models.Position;

import java.awt.*;

public class GraphicalFlashingBreak extends GraphicalBreak {
    private final Image image;
    private boolean visible;

    public GraphicalFlashingBreak(int width, int height, Position position,boolean visible) {
        super(width, height, position);
        this.image = ImageLoader.getImage("flashingBreak");
        this.visible = visible;
    }


    public Image getImage() {
        return image;
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        if (visible)
            graphics2D.drawImage(image, getPosition().getX(), getPosition().getY(),getWidth(), getHeight(), null);
    }
}
