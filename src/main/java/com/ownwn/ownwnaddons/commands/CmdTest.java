package com.ownwn.ownwnaddons.commands;

import com.ownwn.ownwnaddons.goodstuff.SendMsg;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CmdTest extends CommandBase {
    @Override
    public String getCommandName() {
        return "cmdtest";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/cmdtest";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        SendMsg.Msg("\u00A7r\u00A7dTest of Stamina \u00A7r\u00A7e\u00A7lOBJECTIVES\u00A7r");


    }
}