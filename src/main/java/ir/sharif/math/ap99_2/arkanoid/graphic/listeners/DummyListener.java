package ir.sharif.math.ap99_2.arkanoid.graphic.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface DummyListener extends MouseListener {
    @Override
    default void mouseClicked(MouseEvent e) {
    }

    @Override
    default void mousePressed(MouseEvent e) {
    }

    @Override
    default void mouseReleased(MouseEvent e) {
    }

    @Override
    default void mouseEntered(MouseEvent e) {
    }

    @Override
    default void mouseExited(MouseEvent e) {
    }
}
