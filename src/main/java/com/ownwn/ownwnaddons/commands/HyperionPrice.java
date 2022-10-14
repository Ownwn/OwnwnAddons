package com.ownwn.ownwnaddons.commands;

import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.goodstuff.PriceRound;
import gg.essential.universal.UChat;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import static com.ownwn.ownwnaddons.outside.HttpRequest.bz;
import static com.ownwn.ownwnaddons.outside.HttpRequest.lbin;

public class HyperionPrice extends CommandBase {
    @Override
    public String getCommandName() {
        return "hyperionprice";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/hyperionprice";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public static void sendResults(String hypPrice, int cleanHyperion) {
        UChat.chat(OwnwnAddons.PREFIX + "&b&lHyperion Price Info: \n &aLowest BIN: &a" + hypPrice + "\n &aCraft Cost: &a" + PriceRound.roundPrice(cleanHyperion));

    }


    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        if (args.length == 0 || !args[0].equalsIgnoreCase("max") && !args[0].equalsIgnoreCase("clean") ) {
            UChat.chat(OwnwnAddons.PREFIX + "&cInvalid argument! Valid arguments are: \n &c- clean \n &c- semimax \n &c- max ");
            return;
        }

        Thread T = new Thread(() -> {

            JsonObject lbins = lbin();
            String hypPrice = PriceRound.roundPrice(lbins.get("HYPERION").getAsInt());

            int necronBlade = lbins.get("NECRON_HANDLE").getAsInt() + (lbins.get("WITHER_CATALYST").getAsInt() * 24);
            int cleanHyperion = necronBlade + (lbins.get("GIANT_FRAGMENT_LASER").getAsInt() * 8);


            if (args[0].equalsIgnoreCase("max")) {
                JsonObject jasperjson, sapphirejson;
                try {
                    JsonObject bzs = bz();
                    jasperjson = bzs.getAsJsonObject("PERFECT_JASPER_GEMSTONE");
                    sapphirejson = bzs.getAsJsonObject("PERFECT_SAPPHIRE_GEMSTONE");

                } catch (NullPointerException f) {
                    UChat.chat("&cThe bazaar API is currently unresponsive. Please try again later.");
                    return;
                }


                try {
                    double jasper, sapphire = 0;
                    jasper = jasperjson.getAsJsonObject("quick_status").getAsJsonObject("buyPrice").getAsDouble();
                    sapphire = sapphirejson.getAsJsonObject("quick_status").getAsJsonObject("buyPrice").getAsDouble();
                } catch (Exception e) {
                    UChat.chat("&cOne or more gemstones have no buy price. This may offset the hyperion price.");
                }


            }



            else {
                    try {
                        sendResults(hypPrice, cleanHyperion);

                    } catch (Exception e) {
                        UChat.chat(OwnwnAddons.PREFIX + "&cSomething went wrong! ");
                        e.printStackTrace();
                    }
            }



        });
        T.start();
    }
}