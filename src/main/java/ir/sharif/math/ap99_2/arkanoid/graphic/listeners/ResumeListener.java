package ir.sharif.math.ap99_2.arkanoid.graphic.listeners;

import ir.sharif.math.ap99_2.arkanoid.graphic.GraphicalAgent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ResumeListener implements DummyListener {
    private final GraphicalAgent graphicalAgent;

    public ResumeListener(GraphicalAgent graphicalAgent) {
        this.graphicalAgent = graphicalAgent;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        graphicalAgent.resumeGameRequest();

    }


}
