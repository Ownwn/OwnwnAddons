package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import com.ownwn.ownwnaddons.utils.ChatListener;

public class FragRunTimer extends SingleTextHud {

    public FragRunTimer() {
        super("Fragrun", true);
    }

    @Override
    protected String getText(boolean example) {
        if (!ChatListener.toggleTimer) {
            return "§cNot active!";
        }
        return String.valueOf((System.currentTimeMillis() - ChatListener.fragStartTime) / 1000);
    }
}
