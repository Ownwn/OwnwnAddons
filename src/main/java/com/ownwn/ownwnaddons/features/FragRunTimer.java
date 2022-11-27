package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.ChatListener;
import com.ownwn.ownwnaddons.utils.OverlayRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FragRunTimer {
    @SubscribeEvent
    public void onrender(RenderGameOverlayEvent event) {
        if (!OwnwnAddons.config.FRAG_RUN_TIMER) {
            return;
        }
        if (!ChatListener.toggleTimer) {
            return;
        }

        OverlayRenderer.stopwatch("§aFragrun Timer: §c" + (System.currentTimeMillis() - ChatListener.fragStartTime) / 1000, 100, 100);




    }
}
