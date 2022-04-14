package com.ownwn.ownwnaddons.commands;

import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.goodstuff.httpRequest;
import com.ownwn.ownwnaddons.goodstuff.priceRound;
import com.ownwn.ownwnaddons.goodstuff.sendMsg;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

public class hyperionPrice extends CommandBase {
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
        sendMsg.Msg(EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + "Hyperion Price Info: \n"
                + EnumChatFormatting.GREEN + "  Lowest BIN: " + EnumChatFormatting.BLUE + hypPrice + "\n"
                + EnumChatFormatting.GREEN + "  Craft Cost: " + EnumChatFormatting.BLUE + priceRound.roundPrice(cleanHyperion));
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        if (args.length >= 1 && args[0].equalsIgnoreCase("max")) {
        }

        else if (args.length >= 1 && args[0].equalsIgnoreCase("semimax")) {
        }

        else {
            Thread T = new Thread(() -> {
                try {
                    JsonObject lbin = httpRequest.getResponse("https://ytbnhudbghdkbghdrb.com/pog.json");

                    String hypPrice = priceRound.roundPrice(lbin.get("HYPERION").getAsInt());
                    int necronBlade = lbin.get("NECRON_HANDLE").getAsInt() + (lbin.get("WITHER_CATALYST").getAsInt() * 24);
                    int cleanHyperion = necronBlade + (lbin.get("GIANT_FRAGMENT_LASER").getAsInt() * 8);

                    sendResults(hypPrice, cleanHyperion);
                } catch (Exception ignored) {

                }

            });
            T.start();

        }

    }
}