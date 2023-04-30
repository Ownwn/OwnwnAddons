package com.ownwn.ownwnaddons.commands;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.Multithreading;
import cc.polyfrost.oneconfig.utils.commands.annotations.*;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.ApiUtils;
import com.ownwn.ownwnaddons.utils.FetchOnServerJoin;
import com.ownwn.ownwnaddons.utils.Utils;

import java.util.concurrent.TimeUnit;

@Command(value = "owa", description = "Access the " + OwnwnAddons.NAME + " GUI.", customHelpMessage = OwnwnAddons.HELP)
public class Owa {
    public static boolean devMode = false;
    @Main
    private static void main() {
        OwnwnAddons.INSTANCE.config.openGui();
    }

    @SubCommand(description = "Gets the lowest BIN price for any item.")
    @SuppressWarnings("SameParameterValue")
    private void lbin(@Description("ItemID") @Greedy String id) {
        if (id.equals("")) {
            UChat.chat(OwnwnAddons.PREFIX + "&cPlease enter an ItemID!");
            return;
        }
        UChat.actionBar(OwnwnAddons.PREFIX + " &bFetching...");
        Thread T = new Thread(() -> {
            try {
                int itemPrice = ApiUtils.lbin().get(id.toUpperCase()).getAsInt();
                String roundPrice = Utils.roundPrice(itemPrice);

                UChat.chat(OwnwnAddons.PREFIX + "&aThe price of &b" + id.toUpperCase() + "&a is: &b" + roundPrice);
            } catch (Exception e) {
                UChat.chat(OwnwnAddons.PREFIX + "&cInvalid ItemID!");
            }
        });
        T.start();
    }


    @SubCommand(description = "Displays a customizable, formatted chat message in your chat. \"&\"s will be replaced with formatting codes.")
    @SuppressWarnings("SameParameterValue")
    private void preview(@Description("message") @Greedy String message) {
        if (message.equals("")) {
            UChat.chat(OwnwnAddons.PREFIX + "&cPlease enter a message to preview!");
            return;
        }
        UChat.chat(message.replace("&", "§"));
    }


    @SubCommand(description = "OwnwnAddons dev mode")
    @SuppressWarnings("SameParameterValue")
    private void devtest() {
        if (devMode) {
            devMode = false;
            UChat.chat(OwnwnAddons.PREFIX + "&cDisabled &adev mode");
            return;

        }
        devMode = true;
        UChat.chat(OwnwnAddons.PREFIX + "&bEnabled &adev mode");
    }

    @SubCommand(description = "Refreshes custom names without rejoining")
    @SuppressWarnings("SameParameterValue")
    private void fetchnames() {
        UChat.chat(OwnwnAddons.PREFIX + "&aRefreshing names...");
        FetchOnServerJoin.fetchNameTime = System.currentTimeMillis();
        Multithreading.schedule(FetchOnServerJoin.fetchotherNames, 2, TimeUnit.SECONDS);
    }
}
