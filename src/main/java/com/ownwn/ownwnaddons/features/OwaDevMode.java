package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.commands.Owa;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OwaDevMode {

    @SubscribeEvent
    public void onPacketReceive(RenderGameOverlayEvent event) {
        if (!Owa.devMode) {
            return;
        }

    }

}
