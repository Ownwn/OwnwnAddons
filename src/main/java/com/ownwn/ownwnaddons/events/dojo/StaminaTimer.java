package com.ownwn.ownwnaddons.events.dojo;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

import static com.ownwn.ownwnaddons.goodstuff.ScoreBoardInterface.getSidebarLines;
import static com.ownwn.ownwnaddons.goodstuff.ScoreBoardInterface.goodScore;

public class StaminaTimer {
// \u00A7r\u00A7dTest of Stamina \u00A7r\u00A7e\u00A7lOBJECTIVES\u00A7r

    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent event) {

        if (event.type != RenderGameOverlayEvent.ElementType.TEXT || event.isCancelable()) {
            return;
        }

        if (Minecraft.getMinecraft().currentScreen != null ||
                Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }

        if (!OwnwnAddons.config.DOJO_STAMINA_SWITCH) {
            return;
        }

        if (goodScore("Ownwn")) {
        try {
            Minecraft.getMinecraft().fontRendererObj.drawSplitString(
                    getSidebarLines().get(3), 300, 300, 500, 2013291);

        } catch (Exception nosidebar3) {
            if (OwnwnAddons.config.VERBOSE_CODE_SWITCH){ System.out.println("Error getting sidebar lines, player must not be in the dojo");}
            }


        }
    }
}