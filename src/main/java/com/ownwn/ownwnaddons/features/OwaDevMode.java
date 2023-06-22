package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import com.ownwn.ownwnaddons.commands.Owa;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OwaDevMode {

    @SubscribeEvent
    public void onPacketReceive(RenderGameOverlayEvent event) {
        if (!Owa.devMode) {
            return;
        }

        Block underBlock = Minecraft.getMinecraft().theWorld.getBlockState(Minecraft.getMinecraft().thePlayer.getPosition().add(1, 0, 0)).getBlock();
        UChat.chat(underBlock);
        Owa.devMode = false;

    }

}
