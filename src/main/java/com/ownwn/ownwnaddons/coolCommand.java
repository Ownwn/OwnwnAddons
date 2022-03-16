package com.ownwn.ownwnaddons;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class coolCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "nelsonisastoner";
    }
    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "examplecommand <requiredArg1> [optionalArg2";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED +
                "Hello " +  EnumChatFormatting.YELLOW + sender.getName() + EnumChatFormatting.RED + ", did you know Nelson is a " + EnumChatFormatting.BLUE + "stonahh"));
    }
}
