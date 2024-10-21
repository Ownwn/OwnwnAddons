package com.ownwn.ownwnaddons.command

import cc.polyfrost.oneconfig.libs.universal.UChat
import cc.polyfrost.oneconfig.utils.Multithreading
import cc.polyfrost.oneconfig.utils.commands.annotations.*
import com.ownwn.ownwnaddons.Config
import com.ownwn.ownwnaddons.OwnwnAddons
import com.ownwn.ownwnaddons.feature.CustomName
import com.ownwn.ownwnaddons.feature.dungeons.EnderPearlGrabber
import java.util.concurrent.TimeUnit


@Command(value = OwnwnAddons.MODID, description = "Access the " + OwnwnAddons.NAME + " GUI.", aliases = ["owa"])
object MainCommand {
    @Main
    fun main() {
        Config.openGui()
    }

    @SubCommand(description = "Displays a customizable, formatted chat message in your chat. \"&\"s will be replaced with formatting codes.")
    private fun preview(@Description("message") @Greedy message: String) {
        if (message.isEmpty()) {
            UChat.chat(OwnwnAddons.PREFIX + "&cPlease enter a message to preview!")
            return
        }
        UChat.chat(message.replace("&", "§"))
    }

    @SubCommand(description = "Refreshes custom names without rejoining")
    private fun fetchnames() {
        UChat.chat(OwnwnAddons.PREFIX + "&aRefreshing names...")
        CustomName.lastFetchedNames = System.currentTimeMillis()
        Multithreading.schedule(CustomName.fetchOtherNames, 0, TimeUnit.SECONDS)
    }

    @SubCommand(description = "Grabs ender pearls from your sacks to keep 16 in your inventory")
    private fun pearls() {
        EnderPearlGrabber.grabPearls()
    }
}