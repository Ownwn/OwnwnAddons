package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BazaarChatCleanup {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {

        if (!OwnwnAddons.config.BAZAAR_CHAT_CLEANUP) {
            return;
        }


        String msg = event.message.getFormattedText();
        String newMsg;


        if (!msg.contains("[Bazaar]")) {
            return;
        }

        switch (msg) {
            case "§r§6[Bazaar] §r§7Putting goods in escrow...§r":
            case "§r§6[Bazaar] §r§7Submitting sell offer...§r":
            case "§r§6[Bazaar] §r§7Executing instant buy...§r":
            case "§r§6[Bazaar] §r§7Executing instant sell...§r":
            case "§r§6[Bazaar] §r§7Claiming order...§r":
                event.setCanceled(true);
                return;
        }


        Matcher claimedCoin = Pattern.compile("§r§6\\[Bazaar] §r§7§r§7Claimed §r§6(.+) coins §r§7from selling §r§a(.+)§r§7x §r§(.+) §r§7at §r§6(.+) §r§7each!§r").matcher(msg);
        Matcher claimedItem = Pattern.compile("§r§6\\[Bazaar] §r§7§r§7Claimed §r§a(.+)§r§7x §r§(.+) §r§7worth §r§6(.+) coins §r§7bought for §r§6(.+) §r§7each!§r").matcher(msg);
        Matcher setupOrder = Pattern.compile("§r§6\\[Bazaar] §r§7§r§e(Buy Order|Sell Offer) Setup! §r§a(.+)§r§7x §r§(.+) §r§7for §r§6(.+) coins§r§7.§r").matcher(msg);
        Matcher instaOrder = Pattern.compile("§r§6\\[Bazaar] §r§7(Bought|Sold) §r§a(.+)§r§7x §r§(.+) §r§7for §r§6(.+) coins§r§7!§r").matcher(msg);

        if (claimedCoin.find()) { // has vip/mvp
            newMsg = "§6[Bz] §7Claimed §6" + claimedCoin.group(1) + " coins §7(§6" + claimedCoin.group(4) + "§7 per)\n §7for §a" + claimedCoin.group(2) + "§7x §" + claimedCoin.group(3);

        } else if (setupOrder.find()) { // has vip/mvp
            newMsg = "§6[Bz] §7" + setupOrder.group(1).split(" ")[0] + " -> §a" + setupOrder.group(2) + "§7x §" + setupOrder.group(3) + "\n §7for §6" + setupOrder.group(4) + " coins";

        } else if (claimedItem.find()) { // has vip/mvp
            newMsg = "§6[Bz] §7Claimed §a" + claimedItem.group(1) + "§7x §" + claimedItem.group(2) + "\n §7for §6" + claimedItem.group(3) + " §7(§6" + claimedItem.group(4) + "§7 per)";
        } else if (instaOrder.find()) {
            newMsg = "§6[Bz] §7" + instaOrder.group(1) + " §a" + instaOrder.group(2) + "§7x §" + instaOrder.group(3) + " §7for §6" + instaOrder.group(4) + " coins";
        }

        else {
            return;
        }

        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle()); // replace msg
    }
}
