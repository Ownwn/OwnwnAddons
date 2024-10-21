package com.ownwn.ownwnaddons.util

import cc.polyfrost.oneconfig.events.event.TickEvent
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe
import com.ownwn.ownwnaddons.Config

object ColourUtils {
    private var colourNum: Int = 0
    private var colourChangeDelay: Long = 0
    // sorted by colour instead of alphabetically
    private val formattingCodes: Array<String> = arrayOf("4", "c", "d", "5", "1", "9", "3", "b", "2", "a", "e", "6")


    fun scuffedChroma(): String {
        return formattingCodes[colourNum]
    }


    fun chooseColour(): String {
        if (Config.chromaType) {
            return scuffedChroma()
        }
        return "z" // "z" for SBA's chroma §z
    }


    private fun incrementColour() {
        if (System.currentTimeMillis() - colourChangeDelay < Config.scuffedChromaSpeed + 1) {
            return
        }
        colourChangeDelay = System.currentTimeMillis()
        colourNum++
        if (colourNum > 11) { //loop back to red colour
            colourNum = 0
        }
    }

    @Subscribe
    fun onTick(event: TickEvent) {
        incrementColour()
    }
}