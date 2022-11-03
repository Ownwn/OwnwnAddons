package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomNameColour {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (!OwnwnAddons.config.NAME_COLOUR_SWITCH) {
            return;
        }

        String player = Minecraft.getMinecraft().thePlayer.getName();
        String msg = event.message.getFormattedText();

        if (!msg.contains(player)) {
            return;
        }

        String goodColour = EnumChatFormatting.values()[OwnwnAddons.config.NAME_COLOUR_SELECT].toString();

        Matcher rankMatcher = Pattern.compile("\\u00A7.\\[.+] " + player).matcher(msg); // for players with vip/mvp
        Matcher defaultMatcher = Pattern.compile("\\u00A77" + player).matcher(msg); // for players without a rank (grey name)

        if (rankMatcher.find()) {
            msg = msg.replace(player, goodColour + player + "§r");
        }
        else if (defaultMatcher.find()) {
            msg = msg.replace(player, goodColour + player + "§7");
        }
        else {
            return;
        }

        event.message = new ChatComponentText(msg).setChatStyle(event.message.getChatStyle());

    }
}
