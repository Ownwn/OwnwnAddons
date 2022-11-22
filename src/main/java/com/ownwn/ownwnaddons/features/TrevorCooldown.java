package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TrevorCooldown {
    @SubscribeEvent
    public void onrender(RenderGameOverlayEvent event) {

        if (!OwnwnAddons.config.TREVOR_COOLDOWN_GOOD) {
            return;
        }

        if (Minecraft.getMinecraft().currentScreen != null || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }

        String cooldown;
        FontRenderer renderer = Minecraft.getMinecraft().fontRendererObj;
        long now = System.currentTimeMillis();

        if (now < TrevorChatCleanup.finishTime) {
            cooldown = (TrevorChatCleanup.finishTime - now + 1000) / 1000 + "§es";
        } else {
            cooldown = "READY";
        }

        renderer.drawString("§eTrevor Cooldown: §a" + cooldown + "§r", OwnwnAddons.config.TREVOR_COOLDOWN_X, OwnwnAddons.config.TREVOR_COOLDOWN_Y, 0);

    }
}
