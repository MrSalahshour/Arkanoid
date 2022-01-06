package ir.sharif.math.ap99_2.arkanoid.graphic.listeners;

import ir.sharif.math.ap99_2.arkanoid.graphic.GraphicalAgent;

import java.awt.event.MouseEvent;

public class PlayListener implements DummyListener {
    private final GraphicalAgent graphicalAgent;

    public PlayListener(GraphicalAgent graphicalAgent) {
        this.graphicalAgent = graphicalAgent;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        graphicalAgent.playGameRequest();
    }


}
