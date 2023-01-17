package com.ownwn.ownwnaddons.features.chat;

import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomChat {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {

        if (NewConfig.CUSTOM_NAME_EDITOR.equals("")) {
            return;
        }
        if (NewConfig.PLAYER_HYPIXEL_RANK == 0) { // no rank set
            return;
        }

        String player = Minecraft.getMinecraft().thePlayer.getName();
        String msg = event.message.getFormattedText();
        String newMsg = "";

        if (!msg.contains(player)) {
            return;
        }

        String newCustomName = NewConfig.CUSTOM_NAME_EDITOR.replace("&&", "§");

        if (NewConfig.CUSTOM_NAME_MODE) {
            newMsg = msg.replace(player, newCustomName);
            event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle());
            return;
        }

        Matcher noPlusMatcher = Pattern.compile("(§b\\[MVP]|§a\\[VIP]) (" + player + ")").matcher(msg); // for vip or mvp
        Matcher defaultMatcher = Pattern.compile("((§7)+" + player + ")").matcher(msg); // for players without a rank (grey name)
        Matcher rankMatcher = Pattern.compile("((§b|§6)\\[MVP(§r)*§.\\+{1,2}(§r)*(§b|§6)]|§a\\[VIP(§r)*§6\\+(§r)*§a]) (" + player + ")").matcher(msg); // for players with vip+/mvp+/mvp++

        if (NewConfig.PLAYER_HYPIXEL_RANK == 1) { // default rank
            if (defaultMatcher.find()) {
                newMsg = msg.replace(defaultMatcher.group(1), newCustomName);
            }


        } else if (NewConfig.PLAYER_HYPIXEL_RANK == 2 || NewConfig.PLAYER_HYPIXEL_RANK == 4) { // MVP/VIP
            if (noPlusMatcher.find()) {
                if (NewConfig.NAME_REPLACE_RANK) {
                    newMsg = msg.replace(noPlusMatcher.group(0), newCustomName);
                } else {
                    newMsg = msg.replace(noPlusMatcher.group(2), newCustomName);
                }

            }
        } else { // VIP+/MVP+/MVP++
            if (rankMatcher.find()) {
                if (NewConfig.NAME_REPLACE_RANK) {
                    newMsg = msg.replace(rankMatcher.group(0), newCustomName);
                } else {
                    newMsg = msg.replace(rankMatcher.group(8), newCustomName);
                }
            }
        }


        if (newMsg.equals("")) {
            return;
        }

        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle()); // replace msg

    }
}
