package com.ownwn.ownwnaddons.feature.dungeons.terminal

import com.ownwn.ownwnaddons.Config
import com.ownwn.ownwnaddons.util.Utils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object TerminalUtil {
    var display = false

    private val playerMap = mutableMapOf<String, MutableList<Player>>()  // string is username
    val defaultPlayer = Player(0, 0, 0)


    private val messageRegex = Regex("^(\\w{3,16}) (?:activated a |completed a )(device|lever|terminal)! \\(\\d/(\\d)\\)")

    val terminalObject = TerminalTypesDone

    private var section = -1

    private fun getInitializedPlayerList(): MutableList<Player> {
        // Create new, independent Player instances
        return MutableList(4) { Player() }
    }



    private val activationMessages = setOf(
        "[BOSS] Goldor: The little ants have a brain it seems.",
        "[BOSS] Goldor: Who dares trespass into my domain?",
        "[BOSS] Goldor: I will replace that gate with a stronger one!",
        "[BOSS] Goldor: YOUR END IS NEAR!!"
    )

    @SubscribeEvent(priority = EventPriority.LOW)
    fun onChat(event: ClientChatReceivedEvent) {
        if (!Config.terminalHud.isEnabled && !Config.f7TerminalOverview) return


        val unformattedMsg = Utils.stripFormatting(event.message.formattedText)

        if (unformattedMsg == "The Core entrance is opening!") {
            TerminalRunSummary().sendResults(playerMap)
            resetScore()
            display = false
            section = -1
        }

        if (activationMessages.contains(unformattedMsg)) {
            resetScore()
            display = true
            section++
        }

        if (!unformattedMsg.endsWith(")")) return


        val (username, terminalType, sectionTerminalCount) = messageRegex.find(unformattedMsg)?.destructured ?: return

        TerminalTypesDone.totalTerminals = sectionTerminalCount.toInt()

        if (Config.terminalHud.hideCompletionMessages && Config.terminalHud.isEnabled) event.isCanceled = true

        if (!Config.f7TerminalOverview) return
        playerMap.putIfAbsent(username, MutableList(4) { Player() })

        val player = playerMap[username]!![section]

        when (terminalType) {
            "terminal" -> {
                TerminalTypesDone.terminalsDone++
                player.terminals++
            }

            "lever" -> {
                TerminalTypesDone.leversDone++
                player.levers++
            }

            "device" -> {
                TerminalTypesDone.deviceDone++
                player.devices++
            }
        }

        playerMap[username]!![section] = player



    }


    @SubscribeEvent
    fun onWorldUnload(event: WorldEvent.Unload) {
        clearTerms()
    }

    fun clearTerms() {
        display = false
        playerMap.clear()
    }


    class Player(        var devices: Int = 0,
                         var levers: Int = 0,
                         var terminals: Int = 0)


    object TerminalTypesDone {
        var terminalsDone = 0
        var leversDone = 0
        var deviceDone = 0
        var totalTerminals = 0
    }

    enum class TerminalType {
        TERMINAL, LEVER, DEVICE
    }

    private fun resetScore() {
        TerminalTypesDone.terminalsDone = 0
        TerminalTypesDone.leversDone = 0
        TerminalTypesDone.deviceDone = 0
        TerminalTypesDone.totalTerminals = 0
    }
}