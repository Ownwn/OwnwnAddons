package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomNameColour {
    List<String> colours = new ArrayList<>();
    public CustomNameColour() {
        colours.add("0");
        colours.add("1");
        colours.add("2");
        colours.add("3");
        colours.add("4");
        colours.add("5");
        colours.add("6");
        colours.add("7");
        colours.add("8");
        colours.add("9");
        colours.add("a");
        colours.add("b");
        colours.add("c");
        colours.add("d");
        colours.add("e");
        colours.add("f");
        colours.add("g");
        colours.add("h");



//        users.put(0, "0"); // "Black"
//        users.put(1, "1"); // "Dark Blue"
//        users.put(2, "2"); // "Green"
//        users.put(3, "3"); // "Dark Aqua"
//        users.put(4, "4"); // "Dark Red"
//        users.put(5, "5"); // "Purple"
//        users.put(6, "6"); // "Gold"
//        users.put(7, "7"); // "Grey"
//        users.put(8, "8"); // "Dark Grey"
//        users.put(9, "9"); // "Blue"
//        users.put(10, "a"); // "Lime"
//        users.put(11, "b"); // "Aqua"
//        users.put(12, "c"); // "Red"
//        users.put(13, "d"); // "Pink"
//        users.put(14, "e"); // "Yellow"
//        users.put(15, "f"); // "White"

    }
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {

        if (!OwnwnAddons.config.NAME_COLOUR_SWITCH) {
            return;
        }

        String player = Minecraft.getMinecraft().thePlayer.getName();
        String msg = event.message.getFormattedText();

        if (!msg.contains(player)) {
            return;
        }
        String goodColour = colours.get(OwnwnAddons.config.NAME_COLOUR_SELECT);

        String regex = "\\u00A7.\\[.+] " + player;
        Matcher matcher = Pattern.compile(regex).matcher(msg);

        if (!matcher.find()) {
            return;
        }

        msg = msg.replace(player, "\u00A7" + goodColour + player + "\u00A7r");
        event.message = new ChatComponentText(msg).setChatStyle(event.message.getChatStyle());



    }
}
