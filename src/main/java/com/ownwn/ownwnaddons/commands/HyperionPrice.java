package com.ownwn.ownwnaddons.commands;

import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.PriceRound;
import gg.essential.universal.UChat;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import static com.ownwn.ownwnaddons.utils.HttpRequest.bz;
import static com.ownwn.ownwnaddons.utils.HttpRequest.lbin;

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

    public static void sendResults(String hypPrice, int cleanHyperion, int scrollsCost) {
        UChat.chat(OwnwnAddons.PREFIX + "&b&lHyperion Price Info: \n &aLowest BIN: &a" + hypPrice + "\n &aCraft Cost: &a" + PriceRound.roundPrice(cleanHyperion) + "\n &5Scrolls Cost: " + PriceRound.roundPrice(scrollsCost));

    }


    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        Thread T = new Thread(() -> {
            String hypPrice;
            int scrollsCost;
            int cleanHyperion;
            int totalHype;
            try {
                JsonObject lbins = lbin();
                hypPrice = PriceRound.roundPrice(lbins.get("HYPERION").getAsInt());

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
}