package com.ownwn.ownwnaddons.features.chat;

import cc.polyfrost.oneconfig.events.event.ChatSendEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import cc.polyfrost.oneconfig.libs.universal.UChat;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MinigameClickFriend {
    public static boolean onCooldown = false;
    public static List<String> requestList = new ArrayList< >();

    // The list of people you've friended resets when you restart your game, but that's fine IMO
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (!NewConfig.CLICK_MINIGAME_FRIENDS) {
            return;
        }

        String msg = event.message.getFormattedText();
        if (msg.contains("§cYou can only have 10 active requests simultaneously!§r")) {
            onCooldown = true;
        } else if (msg.contains("§eYour friend request to §r")) {
            onCooldown = false;
        } else if (msg.contains("§aYou are now friends with §r")) {
            onCooldown = false;
        }
        if (!msg.contains("§e has joined") || msg.contains(Minecraft.getMinecraft().thePlayer.getName())) {
            return;
        }
        Matcher playerJoined = Pattern.compile("§e§r§.(.+)§r§r§r§e has joined \\(§r§b\\d+§r§r§r§e/§r§b\\d+§r§r§r§e\\)!§r§e§r").matcher(msg);
        if (!playerJoined.find()) {
            return;
        }
        String joinedName = playerJoined.group(1);
        if (requestList.contains(playerJoined.group(1))) {
            return;
        }
        event.message.setChatStyle(event.message.getChatStyle()
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f add " + joinedName))
                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("§r§aClick to add §b" + joinedName + " §aas a friend!§r"))));

    }

    @Subscribe
    public void onSpeak(ChatSendEvent event) {
        if (!NewConfig.CLICK_MINIGAME_FRIENDS) {
            return;
        }
        if (!event.message.contains("/f add ")) {
            return;
        }
        if (onCooldown) {
            event.isCancelled = true;
            UChat.chat(OwnwnAddons.PREFIX + "&cYou have reached the maximum of 10 requests!");
            return;
        }
        String playerName = event.message.replace("/f add ", "");
        if (requestList.contains(playerName)) {
            event.isCancelled = true;
            UChat.chat(OwnwnAddons.PREFIX + "&cYou have already friended that player!");
        }
        requestList.add(playerName);


    }
}
