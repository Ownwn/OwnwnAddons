package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WitherShieldSound {
    public long hypUseTime = 0;
    public long lastImplode = 0;
    public long lastShield = 0;

    @SubscribeEvent(priority = EventPriority.LOW)
    public void PlaySound(PlaySoundEvent event) {
        if (NewConfig.WITHER_SHIELD_SOUND.equals("") && NewConfig.WITHER_IMPLODE_SOUND.equals("")) {
            return;
        }

        if (event.name.equals("mob.zombie.remedy")) {
            if (System.currentTimeMillis() - hypUseTime > NewConfig.MAX_PING_NUM || System.currentTimeMillis() - lastShield < 3000) {
                return;
            }

            Minecraft.getMinecraft().thePlayer.playSound(NewConfig.WITHER_SHIELD_SOUND, NewConfig.WITHER_SHIELD_VOLUME/10, NewConfig.WITHER_SHIELD_PITCH/10);
            lastShield = System.currentTimeMillis();

        } else if (event.name.equals("random.explode")) {
            if (System.currentTimeMillis() - hypUseTime > NewConfig.MAX_PING_NUM || System.currentTimeMillis() - lastImplode < 50) {
                return;
            }

            Minecraft.getMinecraft().thePlayer.playSound(NewConfig.WITHER_IMPLODE_SOUND, NewConfig.WITHER_IMPLODE_VOLUME/10, NewConfig.WITHER_IMPLODE_PITCH/10);
            lastImplode = System.currentTimeMillis();
        }


    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void PlayerInteract(PlayerInteractEvent event) {
        if (!event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_AIR) && !event.action.equals((PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK))) {
            return;
        }
        if (Minecraft.getMinecraft().thePlayer.getHeldItem() == null) {
            return;
        }
        String heldItem = Minecraft.getMinecraft().thePlayer.getHeldItem().getItem().getRegistryName();
        if (!heldItem.equals("minecraft:iron_sword")) {
            return;
        }
        hypUseTime = System.currentTimeMillis();
    }
}
