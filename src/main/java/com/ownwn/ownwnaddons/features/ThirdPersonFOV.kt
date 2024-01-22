package com.ownwn.ownwnaddons.features


import com.ownwn.ownwnaddons.utils.ServerJoinEvent
import com.ownwn.ownwnaddons.utils.NewConfig
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

class ThirdPersonFOV {
    private var normalFov: Float = 0f
    private var isThirdPerson = false

    @SubscribeEvent
    fun onTick(event: TickEvent) {
        if (!NewConfig.THIRD_PERSON_FOV) return

        val mc = Minecraft.getMinecraft()
        val currentlyThirdPerson = mc.gameSettings.thirdPersonView == 1

        if (currentlyThirdPerson && !isThirdPerson) { // just entered f5
            normalFov = mc.gameSettings.fovSetting // save player's FOV
            mc.gameSettings.fovSetting = NewConfig.THIRD_PERSON_MODIFIER
            isThirdPerson = true

        } else if (!currentlyThirdPerson && isThirdPerson) { // just left f5
            mc.gameSettings.fovSetting = normalFov // reset FOV to normal
            isThirdPerson = false
        }
    }

    @SubscribeEvent
    fun onModInit(event: ServerJoinEvent) {
        normalFov = Minecraft.getMinecraft().gameSettings.fovSetting
    }
}