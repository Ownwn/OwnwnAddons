package com.ownwn.ownwnaddons.features.dungeons

import com.ownwn.ownwnaddons.OwnwnAddons
import com.ownwn.ownwnaddons.utils.NewConfig
import net.minecraft.client.Minecraft
import net.minecraft.init.Blocks
import net.minecraft.tileentity.TileEntitySkull
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class SecretClickSounds {
    private var lastSound: Long = 0
    private val witherEssenceUUID = "26bb1a8d-7c66-31c6-82d5-a9c04c94fb02"

    private val blockSet = setOf(Blocks.chest, Blocks.trapped_chest, Blocks.skull, Blocks.lever)




    @SubscribeEvent
    fun onInteract(event: PlayerInteractEvent) {

        if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) return

        if (NewConfig.SECRET_CLICK_SOUND.isEmpty()) return
        if (!OwnwnAddons.utils.checkLocGameMode("dungeon")) return

        val world = Minecraft.getMinecraft().theWorld
        val theBlock = world?.getBlockState(event.pos)?.block ?: return

        if (theBlock !in blockSet) return

        if (theBlock == Blocks.skull) {
            val tileEntity = world.getTileEntity(event.pos) as? TileEntitySkull ?: return
            val skullId = tileEntity.playerProfile?.id ?: return

            if (skullId.toString() != witherEssenceUUID) return
        }

        playSound()


    }

    private fun playSound() {
        if (System.currentTimeMillis() - lastSound < 50) return // don't kill ears

        Minecraft.getMinecraft().thePlayer.playSound(
            NewConfig.SECRET_CLICK_SOUND,
            NewConfig.SECRET_CLICK_VOLUME / 10,
            NewConfig.SECRET_CLICK_PITCH / 10
        )
        lastSound = System.currentTimeMillis()
    }


}