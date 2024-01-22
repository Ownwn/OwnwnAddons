package com.ownwn.ownwnaddons.utils

import com.ownwn.ownwnaddons.OwnwnAddons
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.eventhandler.Event
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.network.FMLNetworkEvent

class OnJoinServer {
    private var hasJustJoined = false

    @SubscribeEvent
    fun onJoinServer(event: FMLNetworkEvent.ClientConnectedToServerEvent) {
        hasJustJoined = true
    }

    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (!hasJustJoined) return
        if (Minecraft.getMinecraft().thePlayer == null) return

        hasJustJoined = false
        OwnwnAddons.postEvent(ServerJoinEvent())
    }
}
class ServerJoinEvent : Event()
