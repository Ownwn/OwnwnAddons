package com.ownwn.ownwnaddons.commands;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.NetworkUtils;
import cc.polyfrost.oneconfig.utils.commands.annotations.*;
import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.HttpRequest;
import com.ownwn.ownwnaddons.utils.NewConfig;
import com.ownwn.ownwnaddons.utils.Utils;
import net.minecraft.client.Minecraft;

@Command(value = "owa", description = "Access the " + OwnwnAddons.NAME + " GUI.", customHelpMessage = OwnwnAddons.HELP)
public class Owa {
    public String rank = "";
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
                int itemPrice = HttpRequest.lbin().get(id.toUpperCase()).getAsInt();
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

        UChat.chat(message);
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

    @SubCommand(description = "Gets the player's Hypixel rank")
    @SuppressWarnings("SameParameterValue")
    private void getrank() {
        UChat.chat(OwnwnAddons.PREFIX + "&bFetching rank...");
            if (NewConfig.HYPIXEL_API_KEY.equals("")) {
                UChat.chat(OwnwnAddons.PREFIX + "&cYour Hypixel API key isn't set! For now you need to manually set it in &b/owa");
                return;
            }
            Thread T = new Thread(() -> {
                JsonObject playerData;
                try {


                    playerData = NetworkUtils.getJsonElement("https://api.hypixel.net/player?uuid=" + Minecraft.getMinecraft().thePlayer.getUniqueID() + "&key=" + NewConfig.HYPIXEL_API_KEY).getAsJsonObject().get("player").getAsJsonObject();

                    playerData.get("playername").getAsString();

                } catch (NullPointerException noData) {
                    UChat.chat(OwnwnAddons.PREFIX + "&cError getting player data! Perhaps the Hypixel API is down?");
                    noData.printStackTrace();
                    return;
                }

                try { // check if has a rank
                    playerData.get("newPackageRank").getAsString();
                } catch (NullPointerException defaultRank) {
                    setRank(1);
                    return;
                }

                if (playerData.get("monthlyPackageRank").getAsString().equals("SUPERSTAR")) { // check if has mvp++
                    setRank(6);
                } else {
                     // just get the rank
                    rank = playerData.get("newPackageRank").getAsString();
                    switch (rank) {
                        case "VIP":
                            setRank(2);
                            break;
                        case "VIP_PLUS":
                            setRank(3);
                            break;
                        case "MVP":
                            setRank(4);
                            break;
                        case "MVP_PLUS":
                            setRank(5);
                            break;
                    }
                }

            });
            T.start();
    }

    public void setRank(int rank) {
        NewConfig.PLAYER_HYPIXEL_RANK = rank;
        UChat.chat(OwnwnAddons.PREFIX + "&bSuccessfully set rank!");
    }
}
