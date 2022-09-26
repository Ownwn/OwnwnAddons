package com.ownwn.ownwnaddons.commands;

import com.ownwn.ownwnaddons.goodstuff.SaveApiKey;
import com.ownwn.ownwnaddons.goodstuff.ToggleFeatures;
import com.ownwn.ownwnaddons.outside.ConfigStuff;
import com.ownwn.ownwnaddons.outside.HttpRequest;
import com.ownwn.ownwnaddons.goodstuff.PriceRound;
import com.ownwn.ownwnaddons.goodstuff.SendMsg;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;


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
        if (args.length >= 1 && args[0].equalsIgnoreCase("stoner")) {
            System.out.println("Running stoner command...");
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED +
                    "Hello " +  EnumChatFormatting.YELLOW + sender.getName() + EnumChatFormatting.RED + ", did you know Nelson is a " + EnumChatFormatting.BLUE + "stonahh"));
        }
        else if (args.length >= 2 && args[0].equalsIgnoreCase("say")) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(args[1]);

        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("sam")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "Hello sam"));

        }

        else if (args.length >= 1 && args[0].equalsIgnoreCase("lbin")) {
            if (args.length == 2) {

                Thread T = new Thread(() -> {
                    try {
                        int itemPrice = HttpRequest.getResponse("https://moulberry.codes/lowestbin.json").get(args[1].toUpperCase()).getAsInt();
                        String roundPrice = PriceRound.roundPrice(itemPrice);

                        SendMsg.Msg(EnumChatFormatting.GREEN + "The price of " + EnumChatFormatting.AQUA + args[1].toUpperCase() + EnumChatFormatting.GREEN + " is: " + EnumChatFormatting.AQUA + roundPrice);
                    } catch (Exception e) {
                        SendMsg.Msg(EnumChatFormatting.RED + "Invalid ItemID!");
                    }

                });
                T.start();


            } else {
                SendMsg.Msg(EnumChatFormatting.RED + "Please enter an ItemID!");
            }
        }


        else if (args.length >= 1 && args[0].equalsIgnoreCase("saveapi")) {
            if (args.length == 2) {

                SaveApiKey.writeKey(args[1]);

            } else {
                SendMsg.Msg(EnumChatFormatting.RED + "Please enter an API key!");
            }
        }

        else if (args.length >= 1 && args[0].equalsIgnoreCase("toggle")) {


            if (args.length == 2) {

                ToggleFeatures.write(args[1]);

            } else {
                SendMsg.Msg(EnumChatFormatting.RED + "Please enter a feature to toggle:\n " + EnumChatFormatting.RED + "- Scoreboard \n " + EnumChatFormatting.RED + "- Repelling Candle");
            }
        }


        else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                    EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + "\u279C OwnwnAddons Help\n"

                    + EnumChatFormatting.BLUE + "/owa stoner \u27A1 " + EnumChatFormatting.AQUA + "Runs a super secret stoner command\n"

                    + EnumChatFormatting.BLUE + "/owa say <message> \u27A1 " + EnumChatFormatting.AQUA + "Say anything!\n"

                    + EnumChatFormatting.BLUE + "/owa sam \u27A1 " + EnumChatFormatting.AQUA + "Testing message\n"

                    + EnumChatFormatting.BLUE + "/owa lbin <item> \u27A1 " + EnumChatFormatting.AQUA + "Find the lowest bin for any item (uses Moulberry)\n"

                    + EnumChatFormatting.BLUE + "/owa saveapi <key> \u27A1 " + EnumChatFormatting.AQUA + "Saves your API key to the config\n"

                    + EnumChatFormatting.BLUE + "/owa toggle <feature> \u27A1 " + EnumChatFormatting.AQUA + "Toggles features in the mod\n"
            ));
        }
    }
}