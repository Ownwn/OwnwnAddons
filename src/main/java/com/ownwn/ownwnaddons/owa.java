package com.ownwn.ownwnaddons;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;


public class owa extends CommandBase {
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
        if (args.length >= 1 && args[0].equalsIgnoreCase("stoner")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED +
                    "Hello " +  EnumChatFormatting.YELLOW + sender.getName() + EnumChatFormatting.RED + ", did you know Nelson is a " + EnumChatFormatting.BLUE + "stonahh"));
        }
        else if (args.length >= 2 && args[0].equalsIgnoreCase("say")) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(args[1]);

        }
        else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                    EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + "\u279C OwnwnAddons Help\n"

                    + EnumChatFormatting.BLUE + "/owa stoner \u27A1 " + EnumChatFormatting.AQUA + "Runs a super secret stoner command\n"

                    + EnumChatFormatting.BLUE + "/owa say <message> \u27A1 " + EnumChatFormatting.AQUA + "Say anything!"
            ));
        }
    }
}
