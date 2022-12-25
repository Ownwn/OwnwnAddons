package com.ownwn.ownwnaddons.features.chat;

import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SBAChromaReplacement {
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChat(ClientChatReceivedEvent event) {

        if (!NewConfig.SBA_CHROMA_CHAT) {
            return;
        }
        if (!Loader.isModLoaded("skyblockaddons")) {
            return;
        }

        String msg = event.message.getFormattedText();
        String newMsg = "";

        if (msg.contains("§r§eYou are being transferred to the §r§aPrototype Lobby §r§efor being §r§cAFK§r§e!§r")) {
            newMsg = msg.replace("§aPrototype Lobby", "§zPrototype Lobby");
        } else if (msg.contains("§r§eWelcome to §r§aHypixel SkyBlock§r§e!§r")) {
            newMsg = msg.replace("§aHypixel SkyBlock", "§zHypixel SkyBlock");
        }


        if (newMsg.equals("")) {
            return;
        }
        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle());
    }
}
