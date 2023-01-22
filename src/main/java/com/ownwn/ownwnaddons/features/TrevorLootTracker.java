package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.hud.TextHud;
import com.ownwn.ownwnaddons.features.chat.TrevorChatCleanup;

import java.util.List;

public class TrevorLootTracker extends TextHud {

    public TrevorLootTracker() {
        super(false);
    }

    @Override
    protected void getLines(List<String> lines, boolean example) {
        lines.add("Trevor Loot Tracker");
        lines.add("Pelts: " + TrevorChatCleanup.peltTracker);
    }
}
