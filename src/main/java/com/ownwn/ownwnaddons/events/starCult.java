package com.ownwn.ownwnaddons.events;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class starCult {
    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {

        if (mc.isSingleplayer() || mc.thePlayer.getClientBrand() == null ||
                !mc.thePlayer.getClientBrand().toLowerCase().contains("hypixel")) {

            Scoreboard scoreboard = mc.theWorld.getScoreboard();
            ScoreObjective sidebarObjective = scoreboard.getObjectiveInDisplaySlot(4);
            System.out.println(sidebarObjective);

            //if (com.ownwn.ownwnaddons.events.skyblockInfo.onSkyblock)
        }


    }
}
