package com.ownwn.ownwnaddons.feature

import cc.polyfrost.oneconfig.events.event.TickEvent
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe
import com.ownwn.ownwnaddons.Config
import com.ownwn.ownwnaddons.util.Game
import com.ownwn.ownwnaddons.util.ServerJoinEvent

object ThirdPersonFOV {
    private var normalFov: Float = 0f
    private var isThirdPerson = false

    @Subscribe
    fun onTick(event: TickEvent) {
        if (!Config.thirdPersonFOV) {
            return
        }

        val mc = Game.mc!!


        val justChangedPerspective = mc.gameSettings.thirdPersonView == 1 != isThirdPerson
        if (!justChangedPerspective) {
            return
        }

        if (!isThirdPerson) {
            normalFov = mc.gameSettings.fovSetting // save player's FOV
            mc.gameSettings.fovSetting = Config.fovModifier
            isThirdPerson = true

        } else {
            mc.gameSettings.fovSetting = normalFov // reset FOV to normal
            isThirdPerson = false
        }


    }

    @Subscribe
    fun onServerJoin(event: ServerJoinEvent) {
        normalFov = Game.mc?.gameSettings?.fovSetting ?: return
    }
}