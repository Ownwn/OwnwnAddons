package com.ownwn.ownwnaddons.commands;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.ApiUtils;
import com.ownwn.ownwnaddons.utils.Utils;

import static com.ownwn.ownwnaddons.utils.ApiUtils.bz;
import static com.ownwn.ownwnaddons.utils.ApiUtils.lbin;

@Command(value = "hyperionprice", aliases = "hypprice", description = "Calculate the price to craft a Hyperion.", customHelpMessage = OwnwnAddons.HELP)
public class HyperionPrice {
    @Main
    private static void main() {
        UChat.actionBar(OwnwnAddons.PREFIX + " &bFetching...");
        Thread T = new Thread(() -> {
            String hypPrice;

            long handlePrice;
            long catalystPrice;
            long necronBlade;

            long lasrEye;
            long cleanHyperion;

            long scrollsCost;
            long totalHype;


            try { // fetch lowest bin stuff
                JsonObject lbins = lbin();

                hypPrice = Utils.roundPrice(lbins.get("HYPERION").getAsInt());
                handlePrice = lbins.get("NECRON_HANDLE").getAsInt();

            } catch (Exception e) {
                UChat.chat(OwnwnAddons.PREFIX + "&cError reaching the LBIN Api! ");
                e.printStackTrace();
                return;
            }


            try { // fetch bazaar stuff
                JsonObject bzs = bz();

                scrollsCost = ApiUtils.parseBz("WITHER_SHIELD_SCROLL", bzs) + ApiUtils.parseBz("IMPLOSION_SCROLL", bzs) + ApiUtils.parseBz("SHADOW_WARP_SCROLL", bzs);

                catalystPrice = ApiUtils.parseBz("WITHER_CATALYST", bzs);
                lasrEye = ApiUtils.parseBz("GIANT_FRAGMENT_LASER", bzs);



            } catch (Exception e) {
                UChat.chat(OwnwnAddons.PREFIX + "&cError reaching the Bazaar API! ");
                e.printStackTrace();
                return;
            }

            necronBlade = handlePrice + (catalystPrice  * 24);



            cleanHyperion = necronBlade + (lasrEye * 8);

            totalHype = cleanHyperion + scrollsCost;



            sendResults(hypPrice, totalHype, scrollsCost);

        });
        T.start();
    }

    public static void sendResults(String hypPrice, long cleanHyperion, long scrollsCost) {
        UChat.chat(OwnwnAddons.PREFIX + "&b&lHyperion Price Info: \n &aLowest BIN: &a" + hypPrice + "\n &aCraft Cost: &a" + Utils.roundPrice(cleanHyperion) + "\n &5Scrolls Cost: " + Utils.roundPrice(scrollsCost));

    }
}