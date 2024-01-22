package com.ownwn.ownwnaddons.features.dungeons

import cc.polyfrost.oneconfig.libs.universal.UChat
import com.ownwn.ownwnaddons.OwnwnAddons
import com.ownwn.ownwnaddons.utils.NewConfig
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class TerminalOverview {

    private class Player(var devices: Int = 0, var levers: Int = 0, var terminals: Int = 0)

    private val devicePattern = Regex("^(\\w+) completed a device!")
    private val terminalPattern = Regex("^(\\w+) activated a terminal!")
    private val leverPattern = Regex("^(\\w+) activated a lever!")

    private val players = listOf(
        Pair(devicePattern, Player::devices),
        Pair(leverPattern, Player::levers),
        Pair(terminalPattern, Player::terminals)
    )

    private val widthOfSpace = Minecraft.getMinecraft().fontRendererObj.getCharWidth(' ')
    // width of space character for padding


    private val dungeonPlayerList = mutableMapOf<String, Player>() // string is username
    private var isActive = false

    private val activationMessages = setOf(
        "[BOSS] Goldor: The little ants have a brain it seems.",
        "[BOSS] Goldor: Who dares trespass into my domain?",
        "[BOSS] Goldor: I will replace that gate with a stronger one!",
        "[BOSS] Goldor: YOUR END IS NEAR!!"
    )

    private val gradeRanges = mapOf(
        "&aA+" to 11.1..29.0,
        "&2A" to 7.1..11.0,
        "&eB" to 4.1..7.0,
        "&cC" to 0.0..4.0,
    )

    @SubscribeEvent(priority = EventPriority.HIGH, receiveCanceled = true )
    fun onChat(event: ClientChatReceivedEvent) {
        if (!NewConfig.F7_TERMINAL_OVERVIEW) return

        val unformattedMsg = OwnwnAddons.utils.stripFormatting(event.message.formattedText)


        if (unformattedMsg == "The Core entrance is opening!") {
            sendResults()
            return
        }

        if (activationMessages.contains(unformattedMsg)) {
            isActive = true
            return
        }


        if (!isActive || !unformattedMsg.endsWith(")")) return


        for ((pattern: Regex, playerValue) in players) {
            val playerName = pattern.find(unformattedMsg)?.groupValues?.get(1) ?: continue // continue if regex doesn't find

            val currentPlayer = dungeonPlayerList.getOrPut(playerName) { Player() }
            val currentScore = playerValue.get(currentPlayer)

            playerValue.set(currentPlayer, currentScore + 1) // add to their device, lever or terminal count
            return

        }
    }

    private fun sendResults() {
        if (dungeonPlayerList.isEmpty()) {
            UChat.chat(OwnwnAddons.PREFIX + "&cTerminal overview list is empty.")
            return
        }



        when (NewConfig.TERMINAL_OVERVIEW_STYLE) {
            1 -> { // improved soopy style
                UChat.chat(OwnwnAddons.PREFIX + "&cTerminal Overview")

                val paddedPlayerMap = padPlayerMap(dungeonPlayerList)

                for ((playerName, playerStats) in paddedPlayerMap) {
                    val performanceGrade = calculateGrade(playerStats)

                    UChat.chat("&6$playerName&7:" +
                            " &f${playerStats.terminals} &7terms, " +
                            "&f${playerStats.devices} &7devices, " +
                            "&f${playerStats.levers} &7levers! " +
                            performanceGrade
                    )
                }


            }
            0 -> { // old soopy

                for ((playerName, playerStats) in dungeonPlayerList) {
                    UChat.chat("&6[OWNWN] &7$playerName completed" +
                            " &f${playerStats.terminals} &7terms," +
                            " &f${playerStats.devices} &7devices, and" +
                            " &f${playerStats.levers} &7levers! "
                    )

                }
            }
            else -> { // experimental style
    //            UChat.chat("&aTerminals: &b${playerStats.terminals}")
    //            UChat.chat("&aDevices: &b${playerStats.devices}")
    //            UChat.chat("&aLevers: &b${playerStats.levers}")
            }
        }

        clearTerms()
    }

    private fun padPlayerMap(map: MutableMap<String, Player>): MutableMap<String, Player> {
        val playerNameList = map.keys.toList()
        val longestPlayerName = playerNameList.maxByOrNull { stringWidth(it) } ?: ""

        val paddedPlayerNames = playerNameList.map { name ->
            val widthDifference = stringWidth(longestPlayerName) - stringWidth(name) // diff in width between longest name and current name
            val paddingAmount = " ".repeat(widthDifference / widthOfSpace) // calculate number of spaces for ideal padding

            "$paddingAmount$name"
        }

        val paddedPlayerMap = mutableMapOf<String, Player>()
        for (name in paddedPlayerNames) paddedPlayerMap[name] = map[name.trim()]!! // put the new padded names back into a map

        return paddedPlayerMap
    }

    private fun clearTerms() {
        isActive = false
        dungeonPlayerList.clear()
    }

    private fun calculateGrade(player: Player): String {
        val score = (player.terminals) + (player.devices * 1.5) + (player.levers * 0.75)

        for ((grade, range) in gradeRanges) {
            if (score in range) return grade
        }
        return "&c?"

    }

    private fun stringWidth(string: String): Int { return Minecraft.getMinecraft().fontRendererObj.getStringWidth(string) }


    @SubscribeEvent
    fun onWorldUnload(event: WorldEvent.Unload) { clearTerms() }
}
