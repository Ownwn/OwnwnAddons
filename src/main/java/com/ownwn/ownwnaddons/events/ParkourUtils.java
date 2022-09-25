package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.goodstuff.ScoreBoardInterface;
import com.ownwn.ownwnaddons.goodstuff.SendMsg;
import com.ownwn.ownwnaddons.outside.ConfigStuff;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;
import java.util.Objects;

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

        if (!Objects.equals(ConfigStuff.getString("features", "Parkour"), "true")) {
            return;
        }

        if (goodScore("Ownwn")) {
            try {
                Minecraft.getMinecraft().fontRendererObj.drawSplitString(
                        getSidebarLines().get(1 ), 300, 300, 500, 2013291);
            } catch (Exception e) {
                SendMsg.Msg(EnumChatFormatting.RED + "Error parsing scoreboard");
            }


        }
    }
}
