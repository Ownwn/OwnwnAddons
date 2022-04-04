package com.ownwn.ownwnaddons.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;


public class iUseSbe extends CommandBase {
    @Override
    public String getCommandName() {
        return "iusesbe";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/iusesbe";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        System.out.println("Trying to crash...");
//        try {
//            throw new Error();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            throw new Exception();

        } catch (Exception ignored) {

        }
    }
}
