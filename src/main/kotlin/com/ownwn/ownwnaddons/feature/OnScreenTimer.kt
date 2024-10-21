package com.ownwn.ownwnaddons.feature

import cc.polyfrost.oneconfig.hud.SingleTextHud
import com.ownwn.ownwnaddons.util.Utils


class OnScreenTimer : SingleTextHud("Timer", false) {

    private var isActive: Boolean = false
    private var startTime: Long = 0
    private var elapsed: Long = 0

    override fun getText(example: Boolean): String {
        if (example) {
            return "1.1s"
        }

        if (isActive) {
            val now = System.currentTimeMillis()
            elapsed += now - startTime
            startTime = now
        }
        return "${Utils.roundNum(elapsed / 1000.0)}s"
    }

    fun toggleTimer() {
        isActive = !isActive
        if (isActive) {
            startTime = System.currentTimeMillis()
        }
    }

    fun resetTimer() {
        isActive = false
        elapsed = 0
    }
}