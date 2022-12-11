package com.ownwn.ownwnaddons.utils;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener {
    public static long fragStartTime;
    public static long fragEndTime;
    public static boolean toggleTimer = false;

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {


        String player = Minecraft.getMinecraft().thePlayer.getName();
        String msg = event.message.getFormattedText();

        Matcher leaveMatcher = Pattern.compile("§r§c ☠.+" + player + ".+ disconnected from the Dungeon and became a ghost§r§7.§r").matcher(msg);



        if (msg.contains("§aYour active Potion Effects have been paused and stored.")) {
            fragStartTime = System.currentTimeMillis();
            toggleTimer = true;
        }
        else if (leaveMatcher.find()) {
            fragEndTime = System.currentTimeMillis();
            toggleTimer = false;
            UChat.chat(OwnwnAddons.PREFIX + "&bThat frag run took &a" + (fragEndTime - fragStartTime) / 1000 + "s&b.");
        }
    }
}
