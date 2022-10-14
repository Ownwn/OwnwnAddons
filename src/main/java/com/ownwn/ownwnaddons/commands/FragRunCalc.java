package com.ownwn.ownwnaddons.commands;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.goodstuff.PriceRound;
import gg.essential.universal.UChat;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import static com.ownwn.ownwnaddons.outside.HttpRequest.bz;
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

    public static void sendResults(String hypPrice, int cleanHyperion) {
        UChat.chat(OwnwnAddons.PREFIX + "&b&lF7 Frag Run Calculator: \n &a$ per hour: &a" + hypPrice + "\n &aDiamante Cost: &a" + PriceRound.roundPrice(cleanHyperion));

    }


    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 2 || !args[1].equalsIgnoreCase("no") && !args[0].equalsIgnoreCase("yes") ) {
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


            String hypPrice = PriceRound.roundPrice(lbin("HYPERION"));

            int necronBlade = lbin("NECRON_HANDLE") + (lbin("WITHER_CATALYST") * 24);
            int cleanHyperion = necronBlade + (lbin("GIANT_FRAGMENT_LASER") * 8);


            if (args[0].equalsIgnoreCase("max")) {
                try {

                    String bzPrice = String.valueOf(bz("PERFECT_SAPPHIRE_GEM"));
                    UChat.chat(OwnwnAddons.PREFIX + "&9" + bzPrice);



                } catch (NullPointerException f) {
                    UChat.chat("&cInvalid item!");
                    f.printStackTrace();
                }

            }

            else if (args[0].equalsIgnoreCase("semimax")) {
                UChat.chat("&cNot yet implemented!");
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
