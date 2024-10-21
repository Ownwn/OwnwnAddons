package com.ownwn.ownwnaddons.feature.dungeons

import cc.polyfrost.oneconfig.libs.universal.UChat
import com.ownwn.ownwnaddons.util.Game
import net.minecraft.init.Items

object EnderPearlGrabber {
    fun grabPearls() {
        val player = Game.player ?: return

        var pearlsCount = 0
        val pearlNum = 16

        for (itemStack in player.inventory.mainInventory) {
            if (itemStack == null) continue

            if (itemStack.item != Items.ender_pearl) continue
            pearlsCount += itemStack.stackSize
        }

        if (pearlsCount >= pearlNum) return

        UChat.say("/gfs ender_pearl ${pearlNum - pearlsCount}")
    }
}