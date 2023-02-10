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

        switch (msg) {
            case "§r§eYou are being transferred to the §r§aPrototype Lobby §r§efor being §r§cAFK§r§e!§r":
                newMsg =msg.replace("§aPrototype Lobby", "§zPrototype Lobby");
                break;
            case "§r§eWelcome to §r§aHypixel SkyBlock§r§e!§r":
                newMsg = msg.replace("§aHypixel SkyBlock", "§zHypixel SkyBlock");
                break;
            case "§r§5§lEXTRA! §r§dYou got §r§a§lDOUBLE §r§dsalvaged essence!§r":
                newMsg = msg.replace("§5§lEXTRA", "§z§lEXTRA");
                break;
            case "§r§6Second Wind Activated§r§a! §r§aYour Spirit Mask saved your life!§r":
                newMsg = msg.replace("§6Second Wind Activated", "§zSecond Wind Activated");
                break;
            case "§r§eYou consumed a §r§6Booster Cookie§r§e! §r§dDelicious!§r":
                newMsg = msg.replace("§dDelicious!", "§zDelicious!");
                break;


        }

        if (newMsg.equals("")) {
            if (msg.contains("§6§lALLOWANCE! §r§eYou earned")) {
                newMsg = msg.replace("§6§lALLOWANCE!", "§z§lALLOWANCE!");
            } else if (msg.contains("§d§lYum! §r§eYou gain ")) {
                newMsg = msg.replace("§d§lYum", "§z§lYum");
            } else if (msg.contains("§r§6§lBINGO GOAL COMPLETE!")) {
                newMsg = msg.replace("§6§lBINGO GOAL COMPLETE!", "§z§lBINGO GOAL COMPLETE!");
            } else if (msg.contains("§6§lWelcome to SkyBlock Bingo!")) {
                newMsg = msg.replace("§6§lWelcome to SkyBlock Bingo!", "§z§lWelcome to SkyBlock Bingo!");
            } else if (msg.contains("§b§lSKILL LEVEL UP")) {
                newMsg = msg.replace("§b§lSKILL LEVEL UP", "§z§lSKILL LEVEL UP");
            } else if (msg.contains("§a§lPUZZLE SOLVED!")) {
                newMsg = msg.replace("§a§lPUZZLE SOLVED!", "§z§lPUZZLE SOLVED!");
            } else if (msg.contains(" §r§cThe Catacombs")) {
                newMsg = msg.replace("§cThe Catacombs", "§zThe Catacombs");
            } else if (msg.contains("§dParty Finder §r§f> ")) {
                newMsg = msg.replace("§dParty Finder", "§zParty Finder");
            } else if (msg.contains("§aYour profile was changed to: §e")) {
                newMsg = msg.replace("changed to: §e", "changed to: §z");
            } else if (msg.contains("§6§lSkyBlock Bingo")) {
                newMsg = msg.replace("§6§lSkyBlock Bingo", "§z§lSkyBlock Bingo");
            }
        }



        if (newMsg.equals("")) {
            return;
        }
        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle());
    }
}
