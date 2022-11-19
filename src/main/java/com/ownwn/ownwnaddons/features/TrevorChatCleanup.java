package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrevorChatCleanup {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (!OwnwnAddons.config.TREVOR_CHAT_CLEANUP) {
            return;
        }


        String msg = event.message.getFormattedText();
        String newMsg;

        if (!msg.toLowerCase().contains("pelt") && !msg.toLowerCase().contains("trapper")) {
            return;
        }



        Matcher peltReward = Pattern.compile("§r§aKilling the animal rewarded you §r§5(\\d+) pelts§r§a.§r").matcher(msg);
        Matcher mobLocation = Pattern.compile("§e\\[NPC] Trevor The Trapper§f: §rYou can find your (.+) §fanimal near the (.+).§r§r").matcher(msg);

        if (peltReward.find()) { // has vip/mvp
            newMsg = "\u00A7a+\u00A75" + peltReward.group(1) + "\u00A7a pelts.";
        }
        else if (mobLocation.find()) { // has vip/mvp
            newMsg = "§e[NPC] Trevor§f: " + mobLocation.group(1) + "§f -> " + mobLocation.group(2) + "§f.§r";
        }
        else {
            newMsg = msg;
        }

        switch (msg) {
            case "§e[NPC] Trevor The Trapper§f: §rSorry, I don't have any animals for you to hunt.§r§r":
                newMsg = "§e[NPC] Trevor§f: §cStill on cooldown!\u00A7r";
                break;
            case "§e[NPC] Trevor The Trapper§f: §rCome back soon!§r§r":
            case "§e[NPC] Trevor The Trapper§f: §rAny longer than that and the animal will run away!§r§r":
                event.setCanceled(true);
                break;
            case "§e[NPC] Trevor The Trapper§f: §rYou will have 10 minutes to find the mob from when you accept the task.§r§r":
                newMsg = "§e[NPC] Trevor§f: §rLoading...§r§r";
                break;
            case "§e[NPC] Trevor The Trapper§f: §rI couldnt locate any animals. Come back in a little bit!§r§r":
                newMsg = "§e[NPC] Trevor§f: §cAn error occurred, try again.§r";
                break;
        }

        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle()); // replace msg

    }
}
