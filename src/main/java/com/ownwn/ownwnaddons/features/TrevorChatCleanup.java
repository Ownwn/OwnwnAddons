package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrevorChatCleanup {

    public static long finishTime;
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (!OwnwnAddons.config.TREVOR_CHAT_CLEANUP) {
            return;
        }


        String msg = event.message.getFormattedText();
        String newMsg = "";

        if (!msg.toLowerCase().contains("pelt") && !msg.toLowerCase().contains("trapper")) {
            return;
        }



        Matcher peltReward = Pattern.compile("§r§aKilling the animal rewarded you §r§5(\\d+) pelts§r§a.§r").matcher(msg);
        Matcher mobLocation = Pattern.compile("§e\\[NPC] Trevor The Trapper§f: §rYou can find your (.+) §fanimal near the (.+).§r").matcher(msg);

        if (peltReward.find()) { // has vip/mvp
            newMsg = "\u00A7a+\u00A75" + peltReward.group(1) + "\u00A7a pelts.";
        }
        else if (mobLocation.find()) { // has vip/mvp
            newMsg = "§e[NPC] Trevor§f: " + mobLocation.group(1) + "§f -> " + mobLocation.group(2) + "§f.§r";
            finishTime = System.currentTimeMillis() + 30000;
        }

        switch (msg) {
            case "§e[NPC] Trevor The Trapper§f: §rSorry, I don't have any animals for you to hunt.§r":
                newMsg = "§e[NPC] Trevor§f: §cStill on cooldown!\u00A7r";
                break;
            case "§e[NPC] Trevor The Trapper§f: §rCome back soon!§r":
            case "§e[NPC] Trevor The Trapper§f: §rAny longer than that and the animal will run away!§r":
            case "§r§aReturn to the Trapper soon to get a new animal to hunt!§r":
                event.setCanceled(true);
                break;
            case "§e[NPC] Trevor The Trapper§f: §rYou will have 10 minutes to find the mob from when you accept the task.§r":
                newMsg = "§e[NPC] Trevor§f: §rLoading...§r";
                break;
            case "§e[NPC] Trevor The Trapper§f: §rI couldnt locate any animals. Come back in a little bit!§r":
                newMsg = "§e[NPC] Trevor§f: §cAn error occurred, try again.§r";
                break;

        }
        if (newMsg.equals("")) { // avoid screwing with other messages e.g. removing click prompts
            return;
        }

        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle()); // replace msg


    }
}
