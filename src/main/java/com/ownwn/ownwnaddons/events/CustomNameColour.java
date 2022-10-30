package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.OwnwnAddons;
import gg.essential.universal.UChat;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Pattern;

public class CustomNameColour {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {

        if (!OwnwnAddons.config.CUSTOM_NAME_SWITCH) {
            return;
        }

        String player = Minecraft.getMinecraft().thePlayer.getName();
        if (event.message.getFormattedText().contains(player)) {
            String newName = matchName(player);

            IChatComponent poggs = (ChatComponentText) event.message;

            IChatComponent newGood;
            newGood = new ChatComponentText("This is neat");
            newGood.setChatStyle(poggs.getChatStyle().createShallowCopy());

            for (IChatComponent sibling : poggs.getSiblings()) {
                newGood.appendSibling(newGood);
            }

            event.message = newGood;
        }




    }

    public static String matchName(String playerName) {
        String colourNameNoRank = "^\u00A7." + playerName + "$";

        if (Pattern.matches(colourNameNoRank, playerName)) {
            return playerName.toUpperCase();
        }
        else {
            return playerName;
        }


    }
}
