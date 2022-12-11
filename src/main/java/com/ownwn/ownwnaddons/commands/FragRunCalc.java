package com.ownwn.ownwnaddons.commands;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Description;
import cc.polyfrost.oneconfig.utils.commands.annotations.Greedy;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.Utils;

import static com.ownwn.ownwnaddons.utils.HttpRequest.lbin;

@Command(value = "calcfragrun", aliases = "cfr", description = "Calculate profit per hour while F7 frag-running.", customHelpMessage = OwnwnAddons.HELP)
public class FragRunCalc {
    @Main
    private static void main(@Description("<Time in seconds> <Looting? yes/no>") @Greedy String args) {
        if (!args.contains(" ")) {
            UChat.chat(OwnwnAddons.PREFIX + "&cUsage: /calcfragrun <time in seconds> <looting yes/no>");
            return;
        }

        String[] allArgs = args.split(" ");
        if (allArgs.length != 2 || !allArgs[1].equalsIgnoreCase("no") && !allArgs[1].equalsIgnoreCase("yes") ) {
            UChat.chat(OwnwnAddons.PREFIX + "&cUsage: /calcfragrun <time in seconds> <looting yes/no>");
            return;
        }

        try {
            Integer.parseInt(allArgs[0]);
        } catch (NumberFormatException e){
            UChat.chat(OwnwnAddons.PREFIX + "&cInvalid run time! Please enter the time in seconds");
            return;
        }

        UChat.actionBar(OwnwnAddons.PREFIX + " &bFetching...");
        Thread T = new Thread(() -> {

            JsonObject lbins = lbin();
            int diamante = lbins.get("GIANT_FRAGMENT_DIAMOND").getAsInt();

            int averagePrice = lbins.get("GIANT_FRAGMENT_LASER").getAsInt() + lbins.get("GIANT_FRAGMENT_BOULDER").getAsInt() + lbins.get("GIANT_FRAGMENT_BIGFOOT").getAsInt() + lbins.get("GIANT_FRAGMENT_DIAMOND").getAsInt();
            averagePrice /= 4;

            // IDK if looting affects precursor loot, so I'm going to assume that it does
            double finalChance = averagePrice * 0.75;
            if (allArgs[1].equalsIgnoreCase("no")) {
                finalChance = averagePrice * 0.5;
            }
            System.out.println(finalChance);
            double moneyPerHour = (finalChance / Integer.parseInt(allArgs[0])) * 3600;

            String rounded = Utils.roundPrice(moneyPerHour);

            sendResults(rounded, diamante);


        });
        T.start();


    }

    public static void sendResults(String profitPerHour, int diamante) {
        UChat.chat(OwnwnAddons.PREFIX + "&b&lF7 Frag Run Calculator: \n &a$ per hour: &a" + profitPerHour + "\n &aDiamante Cost: &a" + Utils.roundPrice(diamante));
    }

}
