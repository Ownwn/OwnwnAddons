package com.ownwn.ownwnaddons.features.chat;

import cc.polyfrost.oneconfig.hud.TextHud;

import java.util.List;

public class TrevorLootTracker extends TextHud {

    public TrevorLootTracker() {
        super(true);
    }

    @Override
    protected void getLines(List<String> lines, boolean example) {
        lines.add("Trevor Loot Tracker");
        lines.add("Pelts: " + TrevorChatCleanup.peltTracker);
    }
}
