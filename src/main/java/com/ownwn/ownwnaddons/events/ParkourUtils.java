package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.goodstuff.SendMsg;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.ownwn.ownwnaddons.goodstuff.ScoreBoardInterface.getSidebarLines;
import static com.ownwn.ownwnaddons.goodstuff.ScoreBoardInterface.goodScore;

public class ParkourUtils {

    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent event) {

        if (event.type != RenderGameOverlayEvent.ElementType.TEXT || event.isCancelable()) {
            return;
        }

        if (Minecraft.getMinecraft().currentScreen != null ||
                Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }

        if (!OwnwnAddons.config.PARKOUR_UTILS_SWITCH) {
            return;
        }

        if (goodScore("Ownwn")) {
            try {
                Minecraft.getMinecraft().fontRendererObj.drawSplitString(
                        getSidebarLines().get(1), 300, 300, 500, 2013291);
            } catch (Exception e) {
                SendMsg.Msg(EnumChatFormatting.RED + "Error parsing scoreboard");
            }


        }
    }
}
