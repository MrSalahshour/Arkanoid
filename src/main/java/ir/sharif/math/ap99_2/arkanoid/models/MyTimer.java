package ir.sharif.math.ap99_2.arkanoid.models;

import java.util.Timer;

public class MyTimer {
    private final Timer timer;
    static MyTimer instance;

    private MyTimer(){
        this.timer = new Timer();
    }

    public static MyTimer getMyTimer(){
        if (instance==null)
            instance = new MyTimer();
        return instance;
    }

    public Timer getTimer() {
        return timer;
    }
}
