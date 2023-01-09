package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.hud.SingleTextHud;

public class FragRunTimer extends SingleTextHud {
    public FragRunTimer() {
        super("Fragrun", true);
    }

    @Override
    protected String getText(boolean example) {
//        if (!ChatListener.toggleTimer) {
//            return "§cNot active!";
//        }
//        return String.valueOf((System.currentTimeMillis() - ChatListener.runStartTime) / 1000);
        return null;
    }
}
