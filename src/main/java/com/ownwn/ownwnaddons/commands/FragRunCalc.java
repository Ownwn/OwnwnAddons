package com.ownwn.ownwnaddons.commands;

import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.goodstuff.PriceRound;
import gg.essential.universal.UChat;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import static com.ownwn.ownwnaddons.outside.HttpRequest.lbin;

public class FragRunCalc extends CommandBase {
    @Override
    public String getCommandName() {
        return "calcfragrun";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/calcfragrun";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public static void sendResults(String profitPerHour, int diamante) {
        UChat.chat(OwnwnAddons.PREFIX + "&b&lF7 Frag Run Calculator: \n &a$ per hour: &a" + profitPerHour + "\n &aDiamante Cost: &a" + PriceRound.roundPrice(diamante));

    }


    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 2 || !args[1].equalsIgnoreCase("no") && !args[1].equalsIgnoreCase("yes") ) {
            UChat.chat(OwnwnAddons.PREFIX + "&cInvalid usage! Use /calcfragrun <time in seconds> <looting yes/no>");
            return;
        }


        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            UChat.chat(OwnwnAddons.PREFIX + "&cInvalid run time! Please enter the time in seconds");
            return;
        }


        Thread T = new Thread(() -> {

            JsonObject lbins = lbin();
            int diamante = lbins.get("GIANT_FRAGMENT_DIAMOND").getAsInt();

            int averagePrice = lbins.get("GIANT_FRAGMENT_LASER").getAsInt() + lbins.get("GIANT_FRAGMENT_BOULDER").getAsInt() + lbins.get("GIANT_FRAGMENT_BIGFOOT").getAsInt() + lbins.get("GIANT_FRAGMENT_DIAMOND").getAsInt();
            averagePrice /= 4;

            // IDK if looting affects precursor loot, so I'm going to assume that it does
            double finalChance = averagePrice * 0.75;
            if (args[1].equalsIgnoreCase("no")) {
                    finalChance = averagePrice * 0.5;
            }
            System.out.println(finalChance);
            double moneyPerHour = (finalChance / Integer.parseInt(args[0])) * 3600;

            String rounded = PriceRound.roundPrice(moneyPerHour);

            sendResults(rounded, diamante);


        });
        T.start();
    }
}
