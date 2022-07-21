package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.goodstuff.ScoreBoardInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

import static com.ownwn.ownwnaddons.goodstuff.ScoreBoardInterface.getSidebarLines;
import static com.ownwn.ownwnaddons.goodstuff.ScoreBoardInterface.goodScore;

public class ParkourUtils {

    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent event) throws IOException {

        if (event.type != RenderGameOverlayEvent.ElementType.TEXT || event.isCancelable()) {
            return;
        }

        if (Minecraft.getMinecraft().currentScreen != null ||
                Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }
        if (goodScore("Ownwn")) {

            Minecraft.getMinecraft().fontRendererObj.drawSplitString(
                    getSidebarLines().get(1), 300, 300, 500, 2013291);


        }
    }
}
