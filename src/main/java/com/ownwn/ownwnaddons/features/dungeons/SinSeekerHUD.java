package com.ownwn.ownwnaddons.features.dungeons;

import cc.polyfrost.oneconfig.hud.SingleTextHud;

public class SinSeekerHUD extends SingleTextHud {
    public static boolean shouldDisplay;


    public SinSeekerHUD() {
        super("Time", true);
    }


    @Override
    public String getText(boolean example) {
        if (example) return "500";

        long timeLeft = SinSeekerCooldown.timeLeft - System.currentTimeMillis();

        shouldDisplay = timeLeft >= 1;// if it's 0 or negative then the sinseeker is not active
        return String.valueOf(timeLeft);
    }


    @Override
    public boolean shouldShow() {
        return super.shouldShow() && shouldDisplay;
    }

}
