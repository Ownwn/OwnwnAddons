package com.ownwn.ownwnaddons.events;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class changeMimicMsg {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (event.message.getUnformattedText().contains("$SKYTILS-DUNGEON-SCORE-MIMIC$")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A7eSkytils > \u00A7bMimic Killed!"));
            event.setCanceled(true);
        }

    }
}