package ir.sharif.math.ap99_2.arkanoid.logic;

import ir.sharif.math.ap99_2.arkanoid.util.Config;

public class Instances {
    private static final Config GAME_PANEL = Config.getConfig("gamePanel");
    private static final Config INFO_BALL = Config.getConfig("infoBall");
    private static final Config INFO_BOARD = Config.getConfig("infoBoard");
    private static final Config MENU_PANEL = Config.getConfig("menuPanel");
    private static final Config INFO_BUTTONS = Config.getConfig("infoButtons");

    public static int gamePanelWidth() {
        return GAME_PANEL.getProperty(Integer.class, "width");
    }

    public static int gamePanelHeight() {
        return GAME_PANEL.getProperty(Integer.class, "height");
    }

    public static int ballSize() {
        return INFO_BALL.getProperty(Integer.class, "size");
    }

    public static int gamePanelX() {
        return GAME_PANEL.getProperty(Integer.class, "x");
    }

    public static int gamePanelY() {
        return GAME_PANEL.getProperty(Integer.class, "y");
    }

    public static int boardHeight() {
        return INFO_BOARD.getProperty(Integer.class, "height");
    }

    public static int boardWidth() {
        return INFO_BOARD.getProperty(Integer.class, "width");
    }

    public static int boardX() {
        return INFO_BOARD.getProperty(Integer.class, "x");
    }

    public static int boardY() {
        return INFO_BOARD.getProperty(Integer.class, "y");
    }

    public static int ballX() {
        return INFO_BALL.getProperty(Integer.class, "x");
    }

    public static int ballY() {
        return INFO_BALL.getProperty(Integer.class, "y");
    }

    public static int menuPanelX() {
        return MENU_PANEL.getProperty(Integer.class, "x");
    }

    public static int menuPanelY() {
        return MENU_PANEL.getProperty(Integer.class, "y");
    }

    public static int menuPanelHeight() {
        return MENU_PANEL.getProperty(Integer.class, "height");
    }

    public static int menuPanelWidth() {
        return MENU_PANEL.getProperty(Integer.class, "width");
    }

    public static int playButtonY() {
        return INFO_BUTTONS.getProperty(Integer.class, "playY");
    }

    public static int stopButtonY() {
        return INFO_BUTTONS.getProperty(Integer.class, "stopY");
    }

    public static int resumeButtonY() {
        return INFO_BUTTONS.getProperty(Integer.class, "resumeY");
    }

    public static int loadButtonY() {
        return INFO_BUTTONS.getProperty(Integer.class, "loadY");
    }

    public static int saveButtonY() {
        return INFO_BUTTONS.getProperty(Integer.class, "saveY");
    }

    public static int scoreButtonY() {
        return INFO_BUTTONS.getProperty(Integer.class, "scoreY");
    }


}
