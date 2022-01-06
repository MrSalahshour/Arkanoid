package ir.sharif.math.ap99_2.arkanoid.graphic.panel;

import ir.sharif.math.ap99_2.arkanoid.graphic.GraphicalAgent;
import ir.sharif.math.ap99_2.arkanoid.graphic.ImageLoader;
import ir.sharif.math.ap99_2.arkanoid.graphic.listeners.BoardKeyListener;
import ir.sharif.math.ap99_2.arkanoid.graphic.model.GraphicalGameState;
import ir.sharif.math.ap99_2.arkanoid.logic.Instances;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    protected final GraphicalAgent agent;
    protected final GraphicalGameState graphicalGameState;
    private final Image image;


    public GamePanel(GraphicalAgent agent, GraphicalGameState graphicalGameState) {
        this.agent = agent;
        this.graphicalGameState = graphicalGameState;
        this.image = ImageLoader.getImage("background");
        config();
        setFocusable(true);
        requestFocusInWindow();
    }
    private void config() {
        setLayout(null);
        setBounds(Instances.gamePanelX(), Instances.gamePanelY(), Instances.gamePanelWidth(), Instances.gamePanelHeight());
        addKeyListener(new BoardKeyListener(agent));
    }
    protected void paintComponent(Graphics gs) {
        super.paintComponent(gs);
        Graphics2D g = (Graphics2D) gs;
        g.drawImage(image,0,0,null);
        synchronized (agent.getPaintLock()) {
            graphicalGameState.paint(g);
        }
    }


}
