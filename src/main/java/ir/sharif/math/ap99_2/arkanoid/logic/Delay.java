package ir.sharif.math.ap99_2.arkanoid.logic;

public class Delay {
    private static long startBreakD;
    private static long endBreakD;
    private static long startFastBallD;
    private static long endFastBallD;
    private static long startSlowBallD;
    private static long endSlowBallD;
    private static long startDizzyBoardD;
    private static long endDizzyBoardD;
    private static int BreakDelay=10000;
    private static int FastBallDelay=10000;
    private static int SlowBallDelay=10000;
    private static int DizzyBoardDelay=10000;

    public static long getStartBreakD() {
        return startBreakD;
    }

    public static void setStartBreakD(long startBreakD) {
        Delay.startBreakD = startBreakD;
    }

    public static long getEndBreakD() {
        return endBreakD;
    }

    public static void setEndBreakD(long endBreakD) {
        Delay.endBreakD = endBreakD;
    }

    public static long getStartFastBallD() {
        return startFastBallD;
    }

    public static void setStartFastBallD(long startFastBallD) {
        Delay.startFastBallD = startFastBallD;
    }

    public static long getEndFastBallD() {
        return endFastBallD;
    }

    public static void setEndFastBallD(long endFastBallD) {
        Delay.endFastBallD = endFastBallD;
    }

    public static long getStartSlowBallD() {
        return startSlowBallD;
    }

    public static void setStartSlowBallD(long startSlowBallD) {
        Delay.startSlowBallD = startSlowBallD;
    }

    public static long getEndSlowBallD() {
        return endSlowBallD;
    }

    public static void setEndSlowBallD(long endSlowBallD) {
        Delay.endSlowBallD = endSlowBallD;
    }

    public static long getStartDizzyBoardD() {
        return startDizzyBoardD;
    }

    public static void setStartDizzyBoardD(long startDizzyBoardD) {
        Delay.startDizzyBoardD = startDizzyBoardD;
    }

    public static long getEndDizzyBoardD() {
        return endDizzyBoardD;
    }

    public static void setEndDizzyBoardD(long endDizzyBoardD) {
        Delay.endDizzyBoardD = endDizzyBoardD;
    }

    public static void setBreakDelay(int breakDelay) {
        BreakDelay = breakDelay;
    }

    public static void setFastBallDelay(int fastBallDelay) {
        FastBallDelay = fastBallDelay;
    }

    public static void setSlowBallDelay(int slowBallDelay) {
        SlowBallDelay = slowBallDelay;
    }

    public static void setDizzyBoardDelay(int dizzyBoardDelay) {
        DizzyBoardDelay = dizzyBoardDelay;
    }

    public static int getBreakDelay() {
        return BreakDelay;
    }

    public static int getFastBallDelay() {
        return FastBallDelay;
    }

    public static int getSlowBallDelay() {
        return SlowBallDelay;
    }

    public static int getDizzyBoardDelay() {
        return DizzyBoardDelay;
    }

    public static int makeBreakD(){
        int delay = (int) ((getEndBreakD()-getStartBreakD())%10001);
        if (getBreakDelay()<10000-delay)
            delay+=getBreakDelay();
        return 10000-delay;
    }
    public static int makeFastBallD(){
        int delay = (int) ((getEndFastBallD()-getStartFastBallD())%10001);
        if (getFastBallDelay()<10000-delay)
            delay+=getFastBallDelay();
        return 10000-delay;
    }
    public static int makeSlowBallD(){
        int delay = (int) ((getEndSlowBallD()-getStartSlowBallD())%10001);
        if (getSlowBallDelay()<10000-delay)
            delay+=getSlowBallDelay();
        return 10000-delay;
    }
    public static int makeDizzyBoardD(){
        int delay = (int) ((getEndDizzyBoardD()-getStartDizzyBoardD())%10001);
        if (getDizzyBoardDelay()<10000-delay)
            delay+=getDizzyBoardDelay();
        return 10000-delay;
    }


}
