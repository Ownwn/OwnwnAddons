package com.ownwn.ownwnaddons.util

import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil
import kotlin.math.pow
import kotlin.math.roundToInt

object Utils {
    private val locraw get() = LocrawUtil.INSTANCE.locrawInfo
    private val colourCodeRegex = Regex("(?i)§[0-9A-FK-ORZ]")

    private const val TRILLION = 1000000000000
    private const val BILLION = 1000000000
    private const val MILLION = 1000000
    private const val THOUSAND = 1000



    fun stripFormatting(text: String?): String {
        return text!!.replace(colourCodeRegex, "")
    }

    fun roundNum(value: Double) = roundNum(value, 1)
    fun roundNum(value: Double, precision: Int): Double {
        val multi = 10.0.pow(precision)
        return (value * multi).roundToInt() / multi
    }

    fun roundPrice(price: Double): String {
        return when {
            price >= TRILLION -> "${roundNum(price / TRILLION, 2)}T"
            price >= BILLION -> "${roundNum(price / BILLION, 2)}B"
            price >= MILLION -> "${roundNum(price / MILLION, 2)}M"
            price >= THOUSAND -> "${roundNum(price / THOUSAND, 2)}K"
            else -> price.roundToInt().toString()
        }
    }



    fun isInIsland(location: String): Boolean {
        return (locraw?.mapName ?: false) == location
    }
    fun isInGameMode(gameMode: String): Boolean {
        return (locraw?.gameMode ?: false) == gameMode
    }
}