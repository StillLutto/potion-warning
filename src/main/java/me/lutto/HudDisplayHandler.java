package me.lutto;

import java.util.Timer;
import java.util.TimerTask;

public class HudDisplayHandler {
    private static boolean showHud = false;

    public static void triggerHudDisplay() {
        showHud = true;
        TimerTask task = new TimerTask() {
            public void run() {
                showHud = false;
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 5000L;
        timer.schedule(task, delay);
    }

    public static boolean shouldDisplayHud() {
        return showHud;
    }
}
