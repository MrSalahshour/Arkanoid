package ir.sharif.math.ap99_2.arkanoid.graphic.listeners;

import ir.sharif.math.ap99_2.arkanoid.graphic.GraphicalAgent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BoardKeyListener implements KeyListener {
    private final GraphicalAgent graphicalAgent;

    public BoardKeyListener(GraphicalAgent graphicalAgent) {
        this.graphicalAgent = graphicalAgent;
    }

    public GraphicalAgent getGraphicalAgent() {
        return graphicalAgent;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        boardMoves(e);

    }

    private void boardMoves(KeyEvent e) {
        if (!graphicalAgent.getLogicalAgent().getGameState().getBoard().isLocked()){
            if (!graphicalAgent.getLogicalAgent().getGameState().getBoard().isDizzy()){
                switch (e.getKeyCode()) {

                    case 37 -> graphicalAgent.moveBoardLeftRequest();
                    case 39 -> graphicalAgent.moveBoardRightRequest();
                }
            }
            else {
                switch (e.getKeyCode()) {
                    case 37 -> graphicalAgent.moveBoardRightRequest();
                    case 39 -> graphicalAgent.moveBoardLeftRequest();
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boardMoves(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
