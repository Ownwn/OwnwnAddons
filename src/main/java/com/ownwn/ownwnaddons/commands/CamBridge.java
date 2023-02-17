package com.ownwn.ownwnaddons.commands;


import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Description;
import cc.polyfrost.oneconfig.utils.commands.annotations.Greedy;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.HttpRequest;
import com.ownwn.ownwnaddons.utils.NewConfig;

import java.io.IOException;

@Command(value = "cam", description = "Send a message to the Camoflaged bridge chat", customHelpMessage = OwnwnAddons.HELP)
public class CamBridge {
    public static String msgContent = "";
    @Main
    private static void main(@Description("Message") @Greedy String args) {
        if (args.equals("")) {
            UChat.chat(OwnwnAddons.PREFIX + "&cUsage: /cam <message>");
            return;
        }
        if (NewConfig.BRIDGE_WEBHOOK_URL.equals("") || NewConfig.BRIDGE_WEBHOOK_URL.equals("https://discord.com/api/webhooks/<ID>/<Webhook Token>")) {
            UChat.chat(OwnwnAddons.PREFIX + "&cYou need to set a webhook url! Set it in /owa");
            return;
        }
        msgContent = args;
        UChat.actionBar(OwnwnAddons.PREFIX + " &bSending...");
        Thread T = new Thread(() -> {

            try {
                HttpRequest.postRequest(NewConfig.BRIDGE_WEBHOOK_URL, args); //this features sends a customisable message to a customisable webhook, intended to replace bridge bots. It does not send any confidential information
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        T.start();
    }
}
