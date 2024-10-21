package com.ownwn.ownwnaddons.feature.dungeons.terminal

import cc.polyfrost.oneconfig.libs.universal.UChat
import com.ownwn.ownwnaddons.Config
import com.ownwn.ownwnaddons.OwnwnAddons
import com.ownwn.ownwnaddons.util.Game
import net.minecraft.client.Minecraft
import net.minecraft.event.HoverEvent
import net.minecraft.util.ChatComponentText
import net.minecraft.util.ChatStyle
import net.minecraft.util.IChatComponent

class TerminalRunSummary {
    private val widthOfSpace = Minecraft.getMinecraft().fontRendererObj.getCharWidth(' ')
    // width of space character for padding

    private val oldSoopy = 0
    private val improvedSoopy = 1 // config selector values



    private val gradeRanges = mapOf( // kinda arbitrary, could be changed a bit
        "§aA+" to 11.1..29.0,
        "§2A" to 7.1..11.0,
        "§eB" to 4.1..7.0,
        "§cC" to 0.0..4.0,
    )


    fun sendResults(map: MutableMap<String, MutableList<TerminalUtil.Player>>) {
        if (!Config.f7TerminalOverview) return
        if (map.isEmpty()) {
            UChat.chat(OwnwnAddons.PREFIX + "&cTerminal overview list is empty. this shouldn't happen...")
            return
        }


        when (Config.terminalOverviewStyle) {

            improvedSoopy -> {
                UChat.chat(OwnwnAddons.PREFIX + "&cTerminal Overview")

                val paddedPlayerMap = padPlayerMap(map)

                for ((playerName, statsList) in paddedPlayerMap) {
                    val playerStats = sumPlayerStats(statsList)
                    val performanceGrade = calculateGrade(playerStats)

                    Game.player!!.addChatMessage(generateHoverText(
                        "§6$playerName§7:" +
                                " §f${playerStats.terminals} §7terms, " +
                                "§f${playerStats.devices} §7devices, " +
                                "§f${playerStats.levers} §7levers! " +
                                performanceGrade,

                        genStatsText(playerName.trim(), statsList)

                    ))
                }
            }

            oldSoopy -> {

                for ((playerName, statsList) in map) {
                    val playerStats = sumPlayerStats(statsList)

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
                UChat.chat(OwnwnAddons.PREFIX + "&cExperimental Terminal Overview not yet implemented")
            }
        }
        TerminalUtil.clearTerms()
    }

    private fun genStatsText(playerName: String, stats: MutableList<TerminalUtil.Player>): String {
        var str = ""
        str += "§6$playerName§7:"

        for (playerStat in stats) {


            str += "\n §7${playerStat.terminals}§cT " +
                    "§7${playerStat.devices}§aD " +
                    "§7${playerStat.levers}§eL"
        }
        return str
    }

    private fun generateHoverText(msg: String, hover: String): IChatComponent {
        return ChatComponentText(msg).setChatStyle(
            ChatStyle().setChatHoverEvent(
                HoverEvent(
                    HoverEvent.Action.SHOW_TEXT,
                    ChatComponentText(hover)
                )
            )
        )
    }

    private fun padPlayerMap(map: MutableMap<String, MutableList<TerminalUtil.Player>>): MutableMap<String, MutableList<TerminalUtil.Player>> {
        val playerNameList = map.keys.toList()
        val longestPlayerName = playerNameList.maxByOrNull { stringWidth(it) } ?: ""

        val paddedPlayerNames = playerNameList.map { name ->
            val widthDifference = stringWidth(longestPlayerName) - stringWidth(name) // diff in width between longest name and current name
            val paddingAmount = " ".repeat(widthDifference / widthOfSpace) // calculate number of spaces for ideal padding

            "$paddingAmount$name"
        }

        val paddedPlayerMap = mutableMapOf<String, MutableList<TerminalUtil.Player>>()
        for (name in paddedPlayerNames) paddedPlayerMap[name] = map[name.trim()]!! // put the new padded names back into a map

        return paddedPlayerMap
    }



    private fun calculateGrade(player: TerminalUtil.Player): String {
        val score = (player.terminals) + (player.devices * 1.5) + (player.levers * 0.75)

        for ((grade, range) in gradeRanges) {
            if (score in range) return grade
        }
        return "§c?"

    }

    private fun sumPlayerStats(list: MutableList<TerminalUtil.Player>): TerminalUtil.Player {
        val player = TerminalUtil.Player()
        for (section in list) {
            player.terminals += section.terminals
            player.levers += section.levers
            player.devices += section.devices
        }
        return player
    }

    private fun stringWidth(string: String): Int { return Game.mc!!.fontRendererObj.getStringWidth(string) }
}