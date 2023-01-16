package com.ownwn.ownwnaddons.features.chat;

import cc.polyfrost.oneconfig.utils.hypixel.HypixelUtils;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrevorChatCleanup {

    public static long finishTime;
    public static boolean playFinishSound = false;
    public static int peltTracker = 0;

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChat(ClientChatReceivedEvent event) {
        if (!NewConfig.TREVOR_CHAT_CLEANUP) {
            return;
        }

        try {
            String map = HypixelUtils.INSTANCE.getPreviousLocraw().getMapName();
            if (!map.equals("The Farming Islands")) {
                return;
            }
        } catch (Exception ignored) {
            // return;
        }

        String msg = event.message.getFormattedText();
        String newMsg = "";

        if (!msg.toLowerCase().contains("pelt") && !msg.toLowerCase().contains("trapper")) {
            return;
        }



        Matcher peltReward = Pattern.compile("§r§aKilling the animal rewarded you §r§5(\\d+) pelts§r§a.§r|§r§aYour mob died randomly, you are rewarded §r§5(\\d+) pelts§r§a.§r").matcher(msg);
        Matcher mobLocation = Pattern.compile("§e\\[NPC] Trevor The Trapper§f: §rYou can find your (.+) §fanimal near the (.+).§r").matcher(msg);

        if (peltReward.find()) {
            String peltNum;
            if (peltReward.group(1) == null) {
                peltNum = peltReward.group(2);
            } else {
                peltNum = peltReward.group(1);
            }
            newMsg = "§a+§5" + peltNum + "§a pelts. §b§l[WARP TO TRAPPER]";
            peltTracker += Integer.parseInt(peltNum);

            event.message.setChatStyle(new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp trapper")));

        }
        else if (mobLocation.find()) {
            if (msg.contains("§9Overgrown Mushroom Cave")) {
                newMsg = "§e[Trevor]§f: " + mobLocation.group(1) + "§f -> §2Overgrown Mushroom Cave§f.§r";
            } else {
                newMsg = "§e[Trevor]§f: " + mobLocation.group(1) + "§f -> " + mobLocation.group(2) + "§f.§r";
            }
            if (msg.contains("§9Desert Settlement") || msg.contains("§9Oasis")) {
                newMsg += " §b§l[WARP]";
                event.message.setChatStyle(new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp desert")));
            }
            finishTime = System.currentTimeMillis() + 30000;
            playFinishSound = true;
        }
        if (msg.contains("§b§lAccept the trapper's task to hunt the animal?")) {
            newMsg = "§e[Trevor]§f: §a§l[START]";
        }
        switch (msg) {
            case "§e[NPC] Trevor The Trapper§f: §rSorry, I don't have any animals for you to hunt.§r":
                newMsg = "§e[Trevor]§f: §cStill on cooldown!§r";
                break;
            case "§e[NPC] Trevor The Trapper§f: §rCome back soon!§r":
            case "§e[NPC] Trevor The Trapper§f: §rAny longer than that and the animal will run away!§r":
            case "§r§aReturn to the Trapper soon to get a new animal to hunt!§r":
                event.setCanceled(true);
                break;
            case "§e[NPC] Trevor The Trapper§f: §rYou will have 10 minutes to find the mob from when you accept the task.§r":
                newMsg = "§e[Trevor]§f: §rLoading...§r";
                break;
            case "§e[NPC] Trevor The Trapper§f: §rI couldn't locate any animals. Come back in a little bit!§r":
                newMsg = "§e[Trevor]§f: §cAn error occurred, try again.§r";
                break;

        }
        if (newMsg.equals("")) { // avoid screwing with other messages e.g. removing click prompts
            return;
        }

        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle()); // replace msg

    }
}
