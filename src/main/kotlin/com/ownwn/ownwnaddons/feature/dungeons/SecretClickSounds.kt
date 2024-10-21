package com.ownwn.ownwnaddons.feature.dungeons

import com.ownwn.ownwnaddons.Config
import com.ownwn.ownwnaddons.util.Game
import com.ownwn.ownwnaddons.util.Utils
import net.minecraft.init.Blocks
import net.minecraft.tileentity.TileEntitySkull
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object SecretClickSounds {
    private var lastSound: Long = 0
    private const val WITHER_ESSENCE_UUID = "26bb1a8d-7c66-31c6-82d5-a9c04c94fb02"

    private val blockSet = setOf(Blocks.chest, Blocks.trapped_chest, Blocks.skull, Blocks.lever)


    @SubscribeEvent
    fun onInteract(event: PlayerInteractEvent) {

        if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) return

        if (Config.secretClickSound.isEmpty()) return
        if (!Utils.isInGameMode("dungeon")) return

        val world = Game.world ?: return
        val theBlock = world.getBlockState(event.pos)?.block ?: return

        if (theBlock !in blockSet) return

        if (theBlock == Blocks.skull) {
            val tileEntity = world.getTileEntity(event.pos) as? TileEntitySkull ?: return
            val skullId = tileEntity.playerProfile?.id ?: return

            if (skullId.toString() != WITHER_ESSENCE_UUID) return
        }

        playSound()


    }

    private fun playSound() {
        if (System.currentTimeMillis() - lastSound < 50) return // don't kill ears

        Game.player!!.playSound(
            Config.secretClickSound,
            Config.secretClickVolume / 10,
            Config.secretClickPitch / 10
        )
        lastSound = System.currentTimeMillis()
    }
}