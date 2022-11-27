package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomChat {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (OwnwnAddons.config.CUSTOM_NAME_EDITOR.equals("") && OwnwnAddons.config.CUSTOM_CHAT_COLOUR.equals("")) {
            return;
        }

        String player = Minecraft.getMinecraft().thePlayer.getName();
        String msg = event.message.getFormattedText();
        String newMsg;

        if (!msg.contains(player)) {
            return;
        }

        String newCustomName = OwnwnAddons.config.CUSTOM_NAME_EDITOR.replace("&&", "§");
        String newCustomChat = OwnwnAddons.config.CUSTOM_CHAT_COLOUR.replace("&&", "§");


        Matcher rankMatcher = Pattern.compile("(§.\\[[^\\[\\]]+] ((§.)*" + player + "))").matcher(msg); // for players with vip/mvp
        Matcher defaultMatcher = Pattern.compile("((§7)+" + player + ")").matcher(msg); // for players without a rank (grey name)
        Matcher customChatMatcher = Pattern.compile(player + "((§.)+:( §.)*)").matcher(msg);


        if (!OwnwnAddons.config.CUSTOM_NAME_EDITOR.equals("")) {

             if (defaultMatcher.find()) { // default rank
                newMsg = msg.replace(defaultMatcher.group(1), newCustomName);
            }
            else if (rankMatcher.find()) { // has vip/mvp or another rank


                if (OwnwnAddons.config.NAME_REPLACE_RANK) {
                    newMsg = msg.replace(rankMatcher.group(1), newCustomName); // include rank in replacement
                } else {
                    newMsg = msg.replace(rankMatcher.group(2), newCustomName); // only replace name, not rank
                }

            }


             else {
                newMsg = msg;
            }
        } else {
            newMsg = msg;
        }

        if (!OwnwnAddons.config.CUSTOM_CHAT_COLOUR.equals("") && customChatMatcher.find()) {
            newMsg = newMsg.replace(customChatMatcher.group(1), "§f:" + newCustomChat);
        }

        if (newMsg.equals(msg)) { // avoid screwing with other messages e.g. removing click prompts
            return;
        }

        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle()); // replace msg

    }
}
