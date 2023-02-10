package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DungeonsMimicAlert {
    @SubscribeEvent
    public void onEntity(EntityJoinWorldEvent event) {
        if (!NewConfig.MIMIC_SPAWN_ALERT) {
           return;
        }

        try {
            String location = LocrawUtil.INSTANCE.getLocrawInfo().getGameMode();
            if (!location.equals("dungeon")) {
                return;
            }
        } catch (Exception ignored) {
            return;
        }

        if (!(event.entity instanceof EntityArmorStand)) {
            return;
        }
        String entityName;
        try {
            entityName = event.entity.getCustomNameTag();
        } catch (NullPointerException ignored) {
            return;
        }
        if (!entityName.contains("§8] §c§lMimic§r §a")) {
            return;
        }
        UChat.chat(OwnwnAddons.PREFIX + "&aMimic Detected!");
        try {
            Minecraft.getMinecraft().thePlayer.playSound("ownwnaddons:mimicspawned", 1f, 1f);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
