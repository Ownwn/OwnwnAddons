package com.ownwn.ownwnaddons.commands;

import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.outside.HttpRequest;
import com.ownwn.ownwnaddons.goodstuff.PriceRound;
import com.ownwn.ownwnaddons.goodstuff.SendMsg;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

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
        SendMsg.Msg(EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + "Hyperion Price Info: \n"
                + EnumChatFormatting.GREEN + "  Lowest BIN: " + EnumChatFormatting.BLUE + hypPrice + "\n"
                + EnumChatFormatting.GREEN + "  Craft Cost: " + EnumChatFormatting.BLUE + PriceRound.roundPrice(cleanHyperion));
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
                    JsonObject lbin = HttpRequest.getResponse("https://moulberry.codes/lowestbin.json");

                    String hypPrice = PriceRound.roundPrice(lbin.get("HYPERION").getAsInt());
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