package com.ownwn.ownwnaddons.features.dungeons;

import com.mojang.authlib.GameProfile;
import com.ownwn.ownwnaddons.utils.NewConfig;
import com.ownwn.ownwnaddons.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SecretClickSounds {
    public long lastSound;
    private static final String WITHER_ESSENCE_UUID = "26bb1a8d-7c66-31c6-82d5-a9c04c94fb02";

    // inspiration taken from AtonAddons: https://github.com/FloppaCoding/AtonAddons/blob/main/src/main/kotlin/atonaddons/module/impl/dungeon/SecretChime.kt
    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {

        if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) return;
        new Bob().yeah();
        if (NewConfig.SECRET_CLICK_SOUND.isEmpty()) return;
        if (!Utils.checkLocGameMode("dungeon")) return;

        IBlockState blockState = Minecraft.getMinecraft().theWorld.getBlockState(event.pos);
        if (blockState == null || blockState.getBlock() == null) return;

        Block theBlock = blockState.getBlock();

        if (theBlock != Blocks.chest && theBlock != Blocks.trapped_chest && theBlock != Blocks.skull && theBlock != Blocks.lever) return;


        if (theBlock == Blocks.skull) {

            TileEntity tileEntity = Minecraft.getMinecraft().theWorld.getTileEntity(event.pos);
            if (!(tileEntity instanceof TileEntitySkull)) return;

            GameProfile skullProfile = ((TileEntitySkull) tileEntity).getPlayerProfile();
            if (skullProfile == null || skullProfile.getId() == null) return;

            String skullId = skullProfile.getId().toString();




            if (!skullId.equals(WITHER_ESSENCE_UUID)) return;
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
