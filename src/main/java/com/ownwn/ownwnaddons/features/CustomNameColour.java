package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomNameColour {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (OwnwnAddons.config.CUSTOM_NAME_EDITOR.equals("")) {
            return;
        }

        String player = Minecraft.getMinecraft().thePlayer.getName();
        String msg = event.message.getFormattedText();

        if (!msg.contains(player)) {
            return;
        }

        String newCustomName = OwnwnAddons.config.CUSTOM_NAME_EDITOR.replace("&&", "\u00A7");

        Matcher rankMatcher = Pattern.compile("\\u00A7.\\[.+] " + player).matcher(msg); // for players with vip/mvp
        Matcher defaultMatcher = Pattern.compile("\\u00A77" + player).matcher(msg); // for players without a rank (grey name)

        if (rankMatcher.find()) { // has vip/mvp
            msg = msg.replace(player, newCustomName + "§r");
        }
        else if (defaultMatcher.find()) { // default rank
            msg = msg.replace(player, newCustomName + "§7");
        }
        else {
            return;
        }

        event.message = new ChatComponentText(msg).setChatStyle(event.message.getChatStyle()); // replace msg

    }
}
