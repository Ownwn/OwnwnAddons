package com.ownwn.ownwnaddons.commands;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.Utils;

import static com.ownwn.ownwnaddons.utils.HttpRequest.bz;
import static com.ownwn.ownwnaddons.utils.HttpRequest.lbin;

@Command(value = "hyperionprice", aliases = "hypprice", description = "Calculate the price to craft a Hyperion.", customHelpMessage = OwnwnAddons.HELP)
public class HyperionPrice {
    @Main
    private static void main() {
        UChat.actionBar(OwnwnAddons.PREFIX + " &bFetching...");
        Thread T = new Thread(() -> {
            String hypPrice;
            int scrollsCost;
            int cleanHyperion;
            int totalHype;
            try {
                JsonObject lbins = lbin();
                hypPrice = Utils.roundPrice(lbins.get("HYPERION").getAsInt());

                int necronBlade = lbins.get("NECRON_HANDLE").getAsInt() + (lbins.get("WITHER_CATALYST").getAsInt() * 24);
                cleanHyperion = necronBlade + (lbins.get("GIANT_FRAGMENT_LASER").getAsInt() * 8);

            } catch (Exception e) {
                UChat.chat(OwnwnAddons.PREFIX + "&cError reaching the LBIN Api! ");
                e.printStackTrace();
                return;
            }

            try {
                JsonObject bzs = bz();
                int witherShield = bzs.get("WITHER_SHIELD_SCROLL").getAsJsonObject().get("quick_status").getAsJsonObject().get("buyPrice").getAsInt();
                int implosion = bzs.get("IMPLOSION_SCROLL").getAsJsonObject().get("quick_status").getAsJsonObject().get("buyPrice").getAsInt();
                int shadowWarp = bzs.get("SHADOW_WARP_SCROLL").getAsJsonObject().get("quick_status").getAsJsonObject().get("buyPrice").getAsInt();
                scrollsCost = witherShield + implosion + shadowWarp;

                totalHype = cleanHyperion + scrollsCost;

            } catch (Exception e) {
                UChat.chat(OwnwnAddons.PREFIX + "&cError reaching the Bazaar API! ");
                e.printStackTrace();
                return;
            }



            sendResults(hypPrice, totalHype, scrollsCost);

        });
        T.start();
    }

    public static void sendResults(String hypPrice, int cleanHyperion, int scrollsCost) {
        UChat.chat(OwnwnAddons.PREFIX + "&b&lHyperion Price Info: \n &aLowest BIN: &a" + hypPrice + "\n &aCraft Cost: &a" + Utils.roundPrice(cleanHyperion) + "\n &5Scrolls Cost: " + Utils.roundPrice(scrollsCost));

    }
}