package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import com.ownwn.ownwnaddons.utils.Utils;

public class OnScreenTimer extends SingleTextHud {
    private static boolean isActive;
    private static long startTime = 0;
    private static long elapsed = 0;

    public OnScreenTimer() {
        super("Timer", false);
    }

    @Override
    protected String getText(boolean example) {
        if (example) {
            return "1.1s";
        }
        if(isActive){
            long now = System.currentTimeMillis();
            elapsed += now - startTime;
            startTime = now;
        }
        return Utils.roundNum(elapsed / 1000.0, 1) + "s";
    }

    public static void toggleTimer() {
        isActive = !isActive;
        if (isActive) {
            startTime = System.currentTimeMillis();
        }
    }

    public static void resetTimer() {
        isActive = false;
        elapsed = 0;
    }
}
