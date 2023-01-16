package com.ownwn.ownwnaddons.utils;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class FetchRank {
    long joinTime = 0;
    @SubscribeEvent
    public void JoinServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        if (NewConfig.PLAYER_HYPIXEL_RANK != 0) {
            return;
        }
        if (System.currentTimeMillis() - joinTime < 5) {
            return;
        }
        joinTime = System.currentTimeMillis();




    }
    @SubscribeEvent
    public void OnTick(TickEvent.ClientTickEvent event) {
        if (joinTime == 0) {
            return;
        }
        if (System.currentTimeMillis() < (joinTime + 6)) {
            return;
        }
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(OwnwnAddons.PREFIX + "§aYour Hypixel rank isn't set! set it by §eclicking here §aor with §b/owa getrank").setChatStyle(new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/owa getrank"))));
        joinTime = 0;
    }
}
