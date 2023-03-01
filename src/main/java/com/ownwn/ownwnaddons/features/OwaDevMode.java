package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.commands.Owa;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OwaDevMode {
    @SubscribeEvent
    public void entityThingo(RenderLivingEvent.Specials.Pre<EntityLivingBase> event) {
        if (!Owa.devMode) {
            return;
        }
        if (!(event.entity instanceof EntityPlayerSP)) {
            return;
        }
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        EntityPlayerSP playerThing = (EntityPlayerSP) event.entity;
        if (!playerThing.getCustomNameTag().contains("Ownwn")) {
        }
    }
}
