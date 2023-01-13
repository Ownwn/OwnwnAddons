package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import com.ownwn.ownwnaddons.features.chat.TrevorChatCleanup;
import net.minecraft.client.Minecraft;

public class TrevorCooldown extends SingleTextHud {

    public TrevorCooldown() {
        super("Trevor Cooldown", true);
    }

    @Override
    protected String getText(boolean example) {
        String cooldown;
        long now = System.currentTimeMillis();

        if ((TrevorChatCleanup.finishTime - now) / 100 == 0 && TrevorChatCleanup.playFinishSound) {
            Minecraft.getMinecraft().thePlayer.playSound("ownwnaddons:trevorready", 1f, 1f);
            TrevorChatCleanup.playFinishSound = false;
        }
        if (now < TrevorChatCleanup.finishTime) {
            cooldown = (TrevorChatCleanup.finishTime - now + 1000) / 1000 + "s";
        } else {
            cooldown = "READY";
        }
        return cooldown;
    }

//    @SubscribeEvent
//    public void onrender(RenderGameOverlayEvent event) {
//
//        if (!NewConfig.TREVOR_COOLDOWN_GOOD) {
//            return;
//        }
//
//        if (Minecraft.getMinecraft().currentScreen != null || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
//            return;
//        }
//
//        String cooldown;
//        FontRenderer renderer = Minecraft.getMinecraft().fontRendererObj;
//        long now = System.currentTimeMillis();
//
//        if (now < TrevorChatCleanup.finishTime) {
//            cooldown = (TrevorChatCleanup.finishTime - now + 1000) / 1000 + "§es";
//        } else {
//            cooldown = "READY";
//        }
//
//        renderer.drawString("§eTrevor Cooldown: §a" + cooldown + "§r", NewConfig.TREVOR_COOLDOWN_X, NewConfig.TREVOR_COOLDOWN_Y, 0);
//
//    }
}
