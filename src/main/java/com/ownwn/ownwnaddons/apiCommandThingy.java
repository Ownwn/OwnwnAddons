package com.ownwn.ownwnaddons;


import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;



public class apiCommandThingy extends CommandBase {
    @Override
    public String getCommandName() {
        return "owatestapi";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/owatestapi";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }

    private static final CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    @Override
    public void processCommand(ICommandSender sender, String[] args) {


    }
}
