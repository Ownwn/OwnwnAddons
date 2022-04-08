package com.ownwn.ownwnaddons.commands;


import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import com.ownwn.ownwnaddons.goodstuff.httpRequest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;


public class lowestBin extends CommandBase {

    @Override
    public String getCommandName() {
        return "owaprice";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/owaprice [subcommand]";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("lbin")) {
                if (args.length == 2) {

                    Thread T = new Thread(() -> {
                        try {
                            int itemPrice = httpRequest.getResponse("https://moulberry.codes/lowestbin.json").get(args[1]).getAsInt();
                            String roundPrice = com.ownwn.ownwnaddons.goodstuff.priceRound.roundPrice(itemPrice);
                            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(OwnwnAddons.PREFIX + EnumChatFormatting.GREEN + "The price of " + EnumChatFormatting.AQUA + args[1] + EnumChatFormatting.GREEN + " is: " + EnumChatFormatting.AQUA + roundPrice));
                        } catch (Exception e) {
                            e.printStackTrace();
                            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(OwnwnAddons.PREFIX + EnumChatFormatting.RED + "Invalid ItemID!"));
                        }

                    });
                    T.start();


                } else {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(OwnwnAddons.PREFIX + EnumChatFormatting.RED + "Please enter an ItemID!"));
                }
            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(OwnwnAddons.PREFIX + EnumChatFormatting.RED + "Invalid subcommand!"));
            }
        } else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(OwnwnAddons.PREFIX + EnumChatFormatting.RED + "Please enter a subcommand!"));
        }
    }
}
