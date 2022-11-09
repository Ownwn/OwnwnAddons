package com.ownwn.ownwnaddons.commands;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.PriceRound;
import gg.essential.api.EssentialAPI;
import gg.essential.universal.UChat;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import static com.ownwn.ownwnaddons.utils.HttpRequest.lbin;


public class Owa extends CommandBase {
    @Override
    public String getCommandName() {
        return "owa";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/owa [subcommand]";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {


        if (args.length >= 1 && args[0].equalsIgnoreCase("lbin")) {
            if (args.length >= 2) {

                Thread T = new Thread(() -> {
                    try {
                        int itemPrice = lbin().get(args[1].toUpperCase()).getAsInt();
                        String roundPrice = PriceRound.roundPrice(itemPrice);

                        UChat.chat(OwnwnAddons.PREFIX + "&aThe price of &b" + args[1].toUpperCase() + "&a is: &b" + roundPrice);
                    } catch (Exception e) {
                        UChat.chat(OwnwnAddons.PREFIX + "&cInvalid ItemID!");
                    }

                });
                T.start();

            } else {
                UChat.chat(OwnwnAddons.PREFIX + "&cPlease enter an ItemID!");
            }
        }

        else if (args.length >= 1 && args[0].equalsIgnoreCase("preview")) {
            if (args.length >= 2) {

                StringBuilder mm = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    mm.append(args[i]).append(" ");
                }

                String formattedMsg = mm.toString().replace("&", "\u00A7");
                UChat.chat(formattedMsg);

            } else {
                UChat.chat(OwnwnAddons.PREFIX + "&cPlease enter a message to preview!");
            }
        }

        else if (args.length >= 1) {
            UChat.chat(
                    "&9&l\u279C OwnwnAddons Help\n"

                    + "&9/owa \u27A1 &bOpens the GUI\n"

                    + "&9/owa lbin <item> \u27A1 &bFind the lowest bin for any item (uses moulberry.codes)\n"

                    + "&9/owa preview <message> \u27A1 &bDisplays any message in chat, supports formatting codes"
            );
        }

        else {
            EssentialAPI.getGuiUtil().openScreen(OwnwnAddons.config.gui());
        }

    }
}
