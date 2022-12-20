package com.ownwn.ownwnaddons.features.chat;

import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankChatCleanup {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {

        if (!NewConfig.BANK_CHAT_CLEANUP) {
            return;
        }


        String msg = event.message.getFormattedText();
        String newMsg = "";


        switch (msg) {
            case "§r§8Withdrawing coins...§r":
            case "§r§8Depositing coins...§r":
            case "§r§eYour §r§apersonal bank §r§ecooldown was reset!§r":

                event.setCanceled(true);
                return;
        }

        Matcher bankInteracted = Pattern.compile("§r§aYou have (withdrawn|deposited) §r§6(.+) (coin.*)§r§a! You now have §r§6(.+) coin.* §r§ain your account!§r").matcher(msg);

        if (bankInteracted.find()) {
            newMsg = "§a" + bankInteracted.group(1).substring(0, 1).toUpperCase() + bankInteracted.group(1).substring(1) + " §6" + bankInteracted.group(2) + " §a" + bankInteracted.group(3) + ". You have §6" + bankInteracted.group(4) + "§a total.";
        }

        if (newMsg.equals("")) {
            return;
        }

        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle());


    }
}
