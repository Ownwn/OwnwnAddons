package com.ownwn.ownwnaddons.commands;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.goodstuff.PriceRound;
import com.ownwn.ownwnaddons.outside.HttpRequest;
import gg.essential.api.EssentialAPI;
import gg.essential.universal.UChat;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;


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
        if (args.length >= 2 && args[0].equalsIgnoreCase("say")) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(args[1]);

        }

        else if (args.length >= 1 && args[0].equalsIgnoreCase("lbin")) {
            if (args.length == 2) {

                Thread T = new Thread(() -> {
                    try {
                        int itemPrice = HttpRequest.getResponse("https://moulberry.codes/lowestbin.json").get(args[1].toUpperCase()).getAsInt();
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


        else if (args.length >= 1) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                    "&9&l\u279C OwnwnAddons Help\n"

                    + "&9/owa \u27A1 &bOpens the GUI\n"

                    + "&9/owa say <message> \u27A1 &bSay anything!\n"

                    + "&9/owa lbin <item> \u27A1 &bFind the lowest bin for any item (uses Moulberry)"
            ));
        }

        else {
            EssentialAPI.getGuiUtil().openScreen(OwnwnAddons.config.gui());
        }

    }
}
