package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomName {

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChat(ClientChatReceivedEvent event) {

        if (NewConfig.CUSTOM_NAME_EDITOR.equals("")) {
            return;
        }
        if (NewConfig.PLAYER_HYPIXEL_RANK == 0) { // no rank set
            return;
        }

        String player = Minecraft.getMinecraft().thePlayer.getName();
        String msg = event.message.getFormattedText();


        if (!msg.contains(player)) {
            return;
        }

        String newMsg = "";
        String newCustomName = NewConfig.CUSTOM_NAME_EDITOR.replace("&&", "§");
        String newCustomRank = NewConfig.CUSTOM_RANK_EDITOR.replace("&&", "§");

        Matcher noPlusMatcher = Pattern.compile("(§b\\[MVP]|§a\\[VIP]) (" + player + ")").matcher(msg); // for vip or mvp
        Matcher defaultMatcher = Pattern.compile("((§7)+" + player + ")").matcher(msg); // for players without a rank (grey name)
        Matcher rankMatcher = Pattern.compile("((§b|§6)\\[MVP(§r)*§.\\+{1,2}(§r)*(§b|§6)]|§a\\[VIP(§r)*§6\\+(§r)*§a]) (" + player + ")").matcher(msg); // for players with vip+/mvp+/mvp++
        Matcher missingRankMatcher = Pattern.compile("(§." + player + ")").matcher(msg); // for replacing just the name and one colour code

        if (NewConfig.PLAYER_HYPIXEL_RANK == 1) { // default rank
            if (defaultMatcher.find()) {
                newMsg = msg.replace(defaultMatcher.group(1), newCustomName);
            }


        } else if (NewConfig.PLAYER_HYPIXEL_RANK == 2 || NewConfig.PLAYER_HYPIXEL_RANK == 4) { // MVP/VIP
            if (noPlusMatcher.find()) {
                if (NewConfig.NAME_REPLACE_RANK) {
                    newMsg = msg.replace(noPlusMatcher.group(0), newCustomRank+ " " + newCustomName);
                } else {
                    newMsg = msg.replace(noPlusMatcher.group(2), newCustomName);
                }

            }
        } else { // VIP+/MVP+/MVP++
            if (rankMatcher.find()) {
                if (NewConfig.NAME_REPLACE_RANK) {
                    newMsg = msg.replace(rankMatcher.group(0), newCustomRank + " " + newCustomName);
                } else {
                    newMsg = msg.replace(rankMatcher.group(8), newCustomName);
                }
            }
        }


        if (newMsg.equals("")) {
            if (missingRankMatcher.find() && NewConfig.CUSTOM_NAME_AGGRO) {
                newMsg = msg.replace(missingRankMatcher.group(1), newCustomName + "§r");
            }
        }
        if (newMsg.equals("")) {
            return;
        }

        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle()); // replace msg

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void renderTooltip(ItemTooltipEvent event) {
    if (!NewConfig.CUSTOM_NAME_TOOLTIPS) {
        return;
    }
    if (NewConfig.CUSTOM_NAME_EDITOR.equals("")) {
        return;
    }
    if (event.toolTip.isEmpty()) {
        return;
    }
    String name = Minecraft.getMinecraft().thePlayer.getName();
    String newCustomName = NewConfig.CUSTOM_NAME_EDITOR.replace("&&", "§");

    for (int i = 0; i < event.toolTip.size(); i++) {
        if (event.toolTip.get(i).contains(name)) {
            event.toolTip.replaceAll(s -> s.replace(name, newCustomName + "§r"));
            return;
        }
    }
    }
    @SubscribeEvent
    public void RenderName(PlayerEvent.NameFormat event) {
        if (!NewConfig.CUSTOM_NAME_NAMETAG) {
            return;
        }

        if (NewConfig.CUSTOM_NAME_EDITOR.equals("")) {
            return;
        }
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        String name = Minecraft.getMinecraft().thePlayer.getName();
        if (!event.displayname.contains(name)) {
            return;
        }

        event.displayname = event.displayname.replace(name, NewConfig.CUSTOM_NAME_EDITOR.replace("&&", "§"));
    }
}
