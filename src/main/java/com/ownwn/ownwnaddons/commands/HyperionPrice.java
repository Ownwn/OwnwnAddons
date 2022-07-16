package com.ownwn.ownwnaddons.commands;

import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.outside.HttpRequest;
import com.ownwn.ownwnaddons.goodstuff.PriceRound;
import com.ownwn.ownwnaddons.goodstuff.SendMsg;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

import java.io.IOException;

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
        SendMsg.Msg(EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + "Hyperion Price Info: \n"
                + EnumChatFormatting.GREEN + "  Lowest BIN: " + EnumChatFormatting.BLUE + hypPrice + "\n"
                + EnumChatFormatting.GREEN + "  Craft Cost: " + EnumChatFormatting.BLUE + PriceRound.roundPrice(cleanHyperion));
    }


    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        Thread T = new Thread(() -> {


            String hypPrice = PriceRound.roundPrice(lbin("HYPERION"));

            int necronBlade = lbin("NECRON_HANDLE") + (lbin("WITHER_CATALYST") * 24);
            int cleanHyperion = necronBlade + (lbin("GIANT_FRAGMENT_LASER") * 8);


            if (args.length >= 1 && args[0].equalsIgnoreCase("max")) {
                try {

                    String bzPrice = String.valueOf(bz("PERFECT_SAPPHIRE_GEM"));
                    SendMsg.Msg(EnumChatFormatting.BLUE + bzPrice);



                } catch (NullPointerException f) {
                    SendMsg.Msg(EnumChatFormatting.RED + "Invalid item!");
                    f.printStackTrace();
                }

            }

            else if (args.length >= 1 && args[0].equalsIgnoreCase("semimax")) {

            }

            else {
                    try {
                        sendResults(hypPrice, cleanHyperion);

                    } catch (Exception e) {
                        SendMsg.Msg(EnumChatFormatting.RED + "Something went wrong! ");
                        e.printStackTrace();
                    }
            }

        });
        T.start();
    }
}