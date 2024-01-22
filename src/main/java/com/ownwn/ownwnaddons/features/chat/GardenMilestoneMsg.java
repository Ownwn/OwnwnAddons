package com.ownwn.ownwnaddons.features.chat;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GardenMilestoneMsg {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (!NewConfig.GARDEN_MILESTONE_MSG) {
            return;
        }

        String msg = event.message.getFormattedText();

        if (!msg.contains("§r§b§lGARDEN MILESTONE") && !msg.contains("§r§b§lGARDEN LEVEL UP") && !msg.contains("§r§6§lOFFER ACCEPTED")) {
            return;
        }

        IChatComponent sendGuildButton = new ChatComponentText("§3§l[§3Send to guild§3§l]")
                .setChatStyle(new ChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("§aClick me to send the milestone to guild chat!")))
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gc " + OwnwnAddons.utils.stripFormatting(event.message.getFormattedText()))));

        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(OwnwnAddons.PREFIX + "§aSend milestone to guild chat? ").appendSibling(sendGuildButton));


    }
}
