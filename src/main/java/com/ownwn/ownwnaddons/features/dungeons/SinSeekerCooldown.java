package com.ownwn.ownwnaddons.features.dungeons;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SinSeekerCooldown {
    public static long timeLeft = 0; // milliseconds left until tp'ed back
    /* because the countdown activates when the player clicks and not when the server receives the click, the time left
    should show how long the player has until they can't click in time */

    @SubscribeEvent
    public void onUse(PlayerInteractEvent event) {
        if (!NewConfig.sinSeekerHUD.isEnabled()) {
            return;
        }
        if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            return;
        }
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;

        if (player.getHeldItem() == null) {
            return;
        }
        if (player.getHeldItem().getDisplayName() == null) {
            return;
        }
        String itemName = player.getHeldItem().getDisplayName();

        if (!OwnwnAddons.utils.stripFormatting(itemName).contains("Sinseeker Scythe")) {
            return;
        }

        timeLeft = System.currentTimeMillis() + 1000;



    }
}
