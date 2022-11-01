package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CustomNameColour {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {

        if (!OwnwnAddons.config.NAME_COLOUR_SWITCH) {
            return;
        }

        String player = Minecraft.getMinecraft().thePlayer.getName();
        String msg = event.message.getFormattedText();

        if (msg.contains(player)) {
            int newColour = OwnwnAddons.config.NAME_COLOUR_SELECT;

            msg = msg.replace(player, "\u00A7" + newColour + player);
            event.message = new ChatComponentText(msg);

        }
    }
}
