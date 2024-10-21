package com.ownwn.ownwnaddons.feature.chat

import cc.polyfrost.oneconfig.events.event.ChatReceiveEvent
import com.ownwn.ownwnaddons.Config
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent



object BazaarChatFormatter { // todo continue

    private val uselessMessages = arrayOf("§r§6[Bazaar] §r§7Putting goods in escrow...§r",
        "§r§6[Bazaar] §r§7Submitting sell offer...§r",
        "§r§6[Bazaar] §r§7Submitting buy order...§r",
        "§r§6[Bazaar] §r§7Executing instant buy...§r",
        "§r§6[Bazaar] §r§7Executing instant sell...§r",
        "§r§6[Bazaar] §r§7Claiming order...§r",
        "§r§6[Bazaar] §r§7Cancelling order...§r")

    private val buyFilled = Regex("§6\\[Bazaar] §eYour §aBuy Order §efor §a((?:\\d|,){1,6}§7x) (.+) §ewas filled!§r")
    private val instaBuy = Regex("§r§6\\[Bazaar] §r§7Bought §r§a((?:\\d|,){1,6}§r§7x) §r(.+) §r§7for §r(§6(?:\\d|,|.)+) coins§r§7!§r")

    @SubscribeEvent(priority = EventPriority.LOW)
    fun onChat(event: ChatReceiveEvent) {
        if (!Config.bazaarSpamFilter) {
            return
        }

        val msg = event.message.formattedText
        var newMsg: String


        if (!msg.startsWith("§r§6[Bazaar]")) {
            return
        }

        if (msg in uselessMessages) {
            event.isCancelled = true
            return
        }
    }
}