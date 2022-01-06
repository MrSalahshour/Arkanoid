package ir.sharif.math.ap99_2.arkanoid.graphic.panel;

import ir.sharif.math.ap99_2.arkanoid.graphic.GraphicalAgent;
import ir.sharif.math.ap99_2.arkanoid.graphic.listeners.*;
import ir.sharif.math.ap99_2.arkanoid.logic.Instances;

import javax.swing.*;

public class MenuPanel extends JPanel {
    protected final GraphicalAgent agent;
    private JButton play;
    private JButton resume;
    private JButton stop;
    private JButton load;
    private JButton save;
    private JButton score;
    private final LoadListener loadListener;
    private final PlayListener playListener;
    private final ResumeListener resumeListener;
    private final SaveListener saveListener;
    private final ScoreListener scoreListener;
    private final StopListener stopListener;


    public MenuPanel(GraphicalAgent agent) {
        this.agent = agent;
        this.loadListener = new LoadListener(agent);
        this.playListener = new PlayListener(agent);
        this.resumeListener = new ResumeListener(agent);
        this.stopListener = new StopListener(agent);
        this.saveListener = new SaveListener(agent);
        this.scoreListener = new ScoreListener(agent);
        this.initialize();
        setFocusable(false);
    }

    private void initialize() {
        setBounds(Instances.menuPanelX(), Instances.menuPanelY(), Instances.menuPanelWidth(), 600);
        setLayout(null);
        this.setVisible(true);
        play = new JButton("PLAY");
        play.setFont(play.getFont().deriveFont(25.0f));
        play.setFocusable(false);
        play.setBorder(null);
        play.addMouseListener(playListener);
        stop = new JButton("STOP");
        stop.setFont(stop.getFont().deriveFont(25.0f));
        stop.setFocusable(false);
        stop.setBorder(null);
        stop.addMouseListener(stopListener);
        resume = new JButton("RESUME");
        resume.setFont(resume.getFont().deriveFont(25.0f));
        resume.setFocusable(false);
        resume.setBorder(null);
        resume.addMouseListener(resumeListener);
        load = new JButton("LOAD");
        load.setFont(load.getFont().deriveFont(25.0f));
        load.setFocusable(false);
        load.setBorder(null);
        load.addMouseListener(loadListener);
        save = new JButton("SAVE");
        save.setFont(save.getFont().deriveFont(25.0f));
        save.setFocusable(false);
        save.setBorder(null);
        save.addMouseListener(saveListener);
        score = new JButton("SCORES");
        score.setFont(score.getFont().deriveFont(25.0f));
        score.setFocusable(false);
        score.setBorder(null);
        score.addMouseListener(scoreListener);
        this.positioning();
        this.addElements();
    }
    private void positioning() {
        play.setBounds(0, Instances.playButtonY(), Instances.menuPanelWidth(), 100);
        stop.setBounds(0, Instances.stopButtonY(), Instances.menuPanelWidth(), 100);
        resume.setBounds(0, Instances.resumeButtonY(), Instances.menuPanelWidth(), 100);
        load.setBounds(0, Instances.loadButtonY(), Instances.menuPanelWidth(), 100);
        save.setBounds(0, Instances.saveButtonY(), Instances.menuPanelWidth(), 100);
        score.setBounds(0, Instances.scoreButtonY(), Instances.menuPanelWidth(), 100);
    }
    private void addElements() {
        add(play);
        add(stop);
        add(resume);
        add(load);
        add(save);
        add(score);
    }


}
