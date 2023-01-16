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
}
