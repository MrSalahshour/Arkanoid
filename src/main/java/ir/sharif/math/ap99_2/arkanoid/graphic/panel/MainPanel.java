package ir.sharif.math.ap99_2.arkanoid.graphic.panel;

import ir.sharif.math.ap99_2.arkanoid.util.Config;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final GamePanel gamePanel;
    private final MenuPanel menuPanel;

    public MainPanel(GamePanel gamePanel, MenuPanel menuPanel) {
        this.gamePanel = gamePanel;
        this.menuPanel = menuPanel;
        initialize();
    }
    private void initialize() {
        config();
        this.add(gamePanel);
        this.add(menuPanel);
    }

    private void config() {
        Config config = Config.getConfig("mainPanel");
        setLayout(null);
        setBounds(config.getProperty(Integer.class, "x"), config.getProperty(Integer.class, "y")
                , config.getProperty(Integer.class, "width"), config.getProperty(Integer.class, "height"));
        setPreferredSize(new Dimension(config.getProperty(Integer.class, "width")
                , config.getProperty(Integer.class, "height")));

    }
}
