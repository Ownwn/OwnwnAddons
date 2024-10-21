package com.ownwn.ownwnaddons.feature.dungeons.terminal

import cc.polyfrost.oneconfig.config.annotations.Exclude
import cc.polyfrost.oneconfig.config.annotations.Switch
import cc.polyfrost.oneconfig.hud.TextHud

class TerminalHud : TextHud(false) {


    @Exclude
    private val exampleList = listOf(
        "§aTerms: §7(§b3§7/§b4§7)",
        "§dLevers: §7(§b1§7/§b2§7)",
        "§cDevice: §aYES")

    @Switch(
        name = "Hide Completion Messages",
        description = "Hides the completion messages in chat",
    )
    var hideCompletionMessages: Boolean = false


    override fun getLines(lines: MutableList<String>?, example: Boolean) {
        if (lines == null) return

        if (example) {
            for (string in exampleList) lines.add(string)
            return
        }


        lines.add(getTerminalFormat(TerminalUtil.TerminalType.TERMINAL, TerminalUtil.TerminalTypesDone.terminalsDone))
        lines.add(getTerminalFormat(TerminalUtil.TerminalType.LEVER, TerminalUtil.TerminalTypesDone.leversDone))
        lines.add(getTerminalFormat(TerminalUtil.TerminalType.DEVICE, TerminalUtil.TerminalTypesDone.deviceDone))

    }

    override fun shouldShow(): Boolean {
        return super.shouldShow() && TerminalUtil.display
    }


    private fun getTerminalFormat(type: TerminalUtil.TerminalType, numCompleted: Int): String {

        var totalTerminalCount = (TerminalUtil.TerminalTypesDone.totalTerminals - 3).toString()  // subtract device and 2 levers
        if (totalTerminalCount == "-3") totalTerminalCount = "?" // if it was originally 0


        return when (type) {
            TerminalUtil.TerminalType.TERMINAL -> {
                formatCompletionAmount("Terms", numCompleted, totalTerminalCount)
            }
            TerminalUtil.TerminalType.LEVER -> {
                formatCompletionAmount("Levers", numCompleted, "2")
            }
            TerminalUtil.TerminalType.DEVICE -> {
                if (numCompleted == 0) "§cDevice: NO" else "§aDevice: YES"
            }
        }
    }

    private fun formatCompletionAmount(terminalName: String, currentCount: Int, totalCount: String): String {
        if (totalCount == currentCount.toString()) return "§a$terminalName: ($currentCount/$totalCount)" // complete

        return "§3$terminalName: §7(§b$currentCount§7/§b$totalCount§7)" // not complete
    }

}