package com.ownwn.ownwnaddons.features.dungeons;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SecretClickSounds {
    public long lastSound;
    // inspiration taken from AtonAddons: https://github.com/FloppaCoding/AtonAddons/blob/main/src/main/kotlin/atonaddons/module/impl/dungeon/SecretChime.kt
    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {

        if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (NewConfig.SECRET_CLICK_SOUND.equals("")) {
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


        Block blockType = null;
        try {
            blockType = Minecraft.getMinecraft().theWorld.getBlockState(event.pos).getBlock();
        } catch (Exception e) {
            UChat.chat(OwnwnAddons.PREFIX + "&c Error getting blocktype for SecretClickSounds, check the logs");
            e.printStackTrace();
        }

        if (blockType == null) {
            return;
        }
        if (blockType != Blocks.chest && blockType != Blocks.trapped_chest && blockType != Blocks.skull && blockType != Blocks.lever) {
            return;
        }

        if (blockType == Blocks.skull) {
            String skullId = null;
            try {
                skullId = ((TileEntitySkull) Minecraft.getMinecraft().theWorld.getTileEntity(event.pos)).getPlayerProfile().getId().toString();
            } catch (Exception ignored) {
            }

            if (skullId != null && skullId.equals("26bb1a8d-7c66-31c6-82d5-a9c04c94fb02")) { // wither essence player head
                playSound();
            }
            return;
        }
        playSound();




    }
    public void playSound() {
        if (System.currentTimeMillis() - lastSound > 50) { // don't kill ears
            Minecraft.getMinecraft().thePlayer.playSound(NewConfig.SECRET_CLICK_SOUND, NewConfig.SECRET_CLICK_VOLUME/10, NewConfig.SECRET_CLICK_PITCH/10);
            lastSound = System.currentTimeMillis();
        }
    }
}
