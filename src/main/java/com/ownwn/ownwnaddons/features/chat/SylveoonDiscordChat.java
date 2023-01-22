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
        if (!NewConfig.GUILD_MSG_PRETTY.contains("NAME") || !NewConfig.GUILD_MSG_PRETTY.contains("MSG")) {
            return;
        }


        String msg = event.message.getFormattedText();
        String newMsg = "";
        if (!msg.contains("Sylveoon")) {
            return;
        }

        String msgReplace = NewConfig.GUILD_MSG_PRETTY.replace("&&", "§");
        Matcher discordPrefix = Pattern.compile("§r§2Guild > §7Sylveoon §3\\[ADMIN]§f: §r(.+?): (.+)§r").matcher(msg);

        if (discordPrefix.find()) {
            //newMsg = "§1Discord > §3" + standardPrefix.group(1) + "§f: " + standardPrefix.group(2);
            newMsg = msgReplace.replace("NAME", discordPrefix.group(1)).replace("MSG", discordPrefix.group(2));
        } else {
            return;
        }

        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle());


    }
}
