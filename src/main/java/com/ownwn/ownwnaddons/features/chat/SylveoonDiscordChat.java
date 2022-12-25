package com.ownwn.ownwnaddons.features.chat;

import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SylveoonDiscordChat {
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChat(ClientChatReceivedEvent event) {
        if (!NewConfig.GUILD_MSG_PRETTY) {
            return;
        }


        String msg = event.message.getFormattedText();
        String newMsg = "";
        if (!msg.contains("Sylveoon")) {
            return;
        }
        Matcher shortPrefix = Pattern.compile("§r§r§r§2G > §7Sylveoon §3\\[ADMIN]§f: §r(.+): (.+)§r").matcher(msg);
        Matcher standardPrefix = Pattern.compile("§r§2Guild > §7Sylveoon §3\\[ADMIN]§f: §r(.+): (.+)§r").matcher(msg);

        if (standardPrefix.find()) {
            newMsg = "§1Discord > §3" + standardPrefix.group(1) + "§f: " + standardPrefix.group(2);
        } else if (shortPrefix.find()) {
            newMsg = "§1Discord > §3" + shortPrefix.group(1) + "§f: " + shortPrefix.group(2);
        }

        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle());


    }
}
