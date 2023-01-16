package com.ownwn.ownwnaddons.commands;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Description;
import cc.polyfrost.oneconfig.utils.commands.annotations.Greedy;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import com.ownwn.ownwnaddons.OwnwnAddons;

@Command(value = "islandafk", aliases = "isafk", description = "Records the amount of time you were afk on your island", customHelpMessage = OwnwnAddons.HELP)
public class IslandAfkTimer {
    static long startTime = 0;
    static long endTime = 0;

    @Main
    private static void main(@Description("<start/end>") @Greedy String args) {
        if (args.equals("start")) {
            startAfk();

        } else if (args.equals("end")) {
            endAfk();

        } else {
            UChat.chat(OwnwnAddons.PREFIX + "&cUsage: /islandafk <start/end>");
        }
    }

    public static void endAfk() {
        if (IslandAfkTimer.startTime == 0) {
            UChat.chat(OwnwnAddons.PREFIX + "&cYou must start recording first!");
            return;
        }
        IslandAfkTimer.endTime = System.currentTimeMillis();
        UChat.chat(OwnwnAddons.PREFIX + "&bAFK recording ended!");
        UChat.chat(OwnwnAddons.PREFIX + "&bYou were afk for &a" + (endTime - startTime) / 1000 + "&bs");
        IslandAfkTimer.startTime = 0;
    }

    public static void startAfk() {
        IslandAfkTimer.startTime = System.currentTimeMillis();
        IslandAfkTimer.endTime = 0;
        UChat.chat(OwnwnAddons.PREFIX + "&bAFK recording started!");
    }
}
