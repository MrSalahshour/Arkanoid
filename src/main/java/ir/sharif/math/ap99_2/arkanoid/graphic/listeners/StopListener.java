package ir.sharif.math.ap99_2.arkanoid.graphic.listeners;

import ir.sharif.math.ap99_2.arkanoid.graphic.GraphicalAgent;

import java.awt.event.MouseEvent;

public class StopListener implements DummyListener {
    private final GraphicalAgent graphicalAgent;

    public StopListener(GraphicalAgent graphicalAgent) {
        this.graphicalAgent = graphicalAgent;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        graphicalAgent.stopGameRequest();

    }

}
