package com.ownwn.ownwnaddons.features.chat;

import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BazaarChatCleanup {

    public static String bazaarMsg = "§6[Bz] §7";
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChat(ClientChatReceivedEvent event) {

        if (!NewConfig.BAZAAR_CHAT_CLEANUP) {
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
            case "§r§6[Bazaar] §r§7Submitting buy order...§r":
            case "§r§6[Bazaar] §r§7Executing instant buy...§r":
            case "§r§6[Bazaar] §r§7Executing instant sell...§r":
            case "§r§6[Bazaar] §r§7Claiming order...§r":
            case "§r§6[Bazaar] §r§7Cancelling order...§r":
                event.setCanceled(true);
                return;
        }


        Matcher claimedCoin = Pattern.compile("§r§6\\[Bazaar] §r§7§r§7Claimed §r§6(.+) coins §r§7from selling §r§a(.+)§r§7x §r§(.+) §r§7at §r§6(.+) §r§7each!§r").matcher(msg);
        Matcher claimedItem = Pattern.compile("§r§6\\[Bazaar] §r§7§r§7Claimed §r§a(.+)§r§7x §r§(.+) §r§7worth §r§6(.+) coins §r§7bought for §r§6(.+) §r§7each!§r").matcher(msg);
        Matcher setupOrder = Pattern.compile("§r§6\\[Bazaar] §r§7§r§e(Buy Order|Sell Offer) Setup! §r§a(.+)§r§7x §r§(.+) §r§7for §r§6(.+) coins§r§7.§r").matcher(msg);
        Matcher instaOrder = Pattern.compile("§r§6\\[Bazaar] §r§7(Bought|Sold) §r§a(.+)§r§7x §r§(.+) §r§7for §r§6(.+) coins§r§7!§r").matcher(msg);
        Matcher cancelOffer = Pattern.compile("§r§6\\[Bazaar] §r§7§r§cCancelled! §r§7Refunded §r§((.+)§r§7x §r§(.+)|6.+ coins) §r§7from cancelling (Sell Offer|Buy Order)!§r").matcher(msg);
        Matcher sellFilled = Pattern.compile("§r§6\\[Bazaar] §r§7§r§eYour §r(§.Sell Offer|§.Buy Order) §r§efor §r§a(.+) §r(.+) §r§ewas filled!§r").matcher(msg);

        if (claimedCoin.find()) {
            newMsg = bazaarMsg + claimedCoin.group(1) + " coins §7(§6" + claimedCoin.group(4) + "§7 per)\n §7for §a" + claimedCoin.group(2) + "§7x §" + claimedCoin.group(3);

        } else if (setupOrder.find()) {
            newMsg = bazaarMsg + setupOrder.group(1).split(" ")[0] + " -> §a" + setupOrder.group(2) + "§7x §" + setupOrder.group(3) + "\n §7for §6" + setupOrder.group(4) + " coins";

        } else if (claimedItem.find()) {
            newMsg = bazaarMsg + "Claimed §a" + claimedItem.group(1) + "§7x §" + claimedItem.group(2) + "\n §7for §6" + claimedItem.group(3) + " §7(§6" + claimedItem.group(4) + "§7 per)";
        } else if (instaOrder.find()) {
            newMsg = bazaarMsg + instaOrder.group(1) + " §a" + instaOrder.group(2) + "§7x §" + instaOrder.group(3) + " §7for §6" + instaOrder.group(4) + " coins";
        } else if (cancelOffer.find()) {
            newMsg = bazaarMsg + cancelOffer.group(4).split(" ")[0] + " §ccancelled! §7Refunded §" + cancelOffer.group(1);
        } else if (sellFilled.find()) {
            newMsg = bazaarMsg + sellFilled.group(1) + "§7 filled! §a" + sellFilled.group(2) + " " + sellFilled.group(3);
        }

        else {
            return;
        }

        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle()); // replace msg
    }
}
