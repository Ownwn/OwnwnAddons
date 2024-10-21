package com.ownwn.ownwnaddons.util

import cc.polyfrost.oneconfig.events.event.TickEvent
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe
import net.minecraft.client.Minecraft
import net.minecraft.client.entity.EntityPlayerSP

object Game {
    // store variables for easy access

    var mc: Minecraft? = null

    var player: EntityPlayerSP? = null
    var name: String? = null

    @Subscribe
    fun onServerJoin(event: ServerJoinEvent) {
        player = mc!!.thePlayer
        name = player!!.name
    }
}