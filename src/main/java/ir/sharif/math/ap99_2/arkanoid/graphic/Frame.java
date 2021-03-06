package ir.sharif.math.ap99_2.arkanoid.graphic;

import ir.sharif.math.ap99_2.arkanoid.graphic.listeners.BoardKeyListener;
import ir.sharif.math.ap99_2.arkanoid.graphic.panel.MainPanel;
import ir.sharif.math.ap99_2.arkanoid.util.Config;
import ir.sharif.math.ap99_2.arkanoid.util.Loop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame  {
    private static Frame instance;
    private int fps;
    private MainPanel mainPanel;

    public static Frame getInstance() {
        if (instance == null) instance = new Frame();
        return instance;
    }

    private Frame() {
        this.config();
        new Loop(fps, this::update).start();
    }

    private void config() {
        Config frameConfig = Config.getConfig("frame");
        setSize(new Dimension(frameConfig.getProperty(Integer.class, "width")
                , frameConfig.getProperty(Integer.class, "height")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(frameConfig.getProperty(Boolean.class, "resizable"));
        setUndecorated(frameConfig.getProperty(Boolean.class, "undecorated"));
        setTitle(frameConfig.getProperty(String.class, "title"));
        fps = frameConfig.getProperty(Integer.class, "fps");
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void setContentPane(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        super.setContentPane(mainPanel);
        super.revalidate();
        super.repaint(1L);
        super.pack();
    }

    private void update() {
        super.revalidate();
        super.repaint();
    }

}
