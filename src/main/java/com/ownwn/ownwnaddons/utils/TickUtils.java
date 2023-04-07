package com.ownwn.ownwnaddons.utils;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TickUtils {
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        ColourUtils.addColour();
    }
}
