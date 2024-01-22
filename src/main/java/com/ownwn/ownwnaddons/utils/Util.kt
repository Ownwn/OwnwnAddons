package com.ownwn.ownwnaddons.utils

import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil
import kotlin.math.pow
import kotlin.math.roundToInt

object Util {


    private val locraw get() = LocrawUtil.INSTANCE.locrawInfo
    private val colourCodeRegex = Regex("(?i)§[0-9A-FK-ORZ]")

    private val trillion = 1000000000000
    private val billion = 1000000000
    private val million = 1000000
    private val thousand = 1000



    fun stripFormatting(text: String): String {
        return text.replace(colourCodeRegex, "")
    }

    fun roundNum(value: Double) = roundNum(value, 1)
    fun roundNum(value: Double, precision: Int): Double {
        val multi = 10.0.pow(precision)
        return (value * multi).roundToInt() / multi
    }

    fun roundPrice(price: Double): String {
        return when {
            price >= trillion -> "${roundNum(price / trillion, 2)}T"
            price >= billion -> "${roundNum(price / billion, 2)}B"
            price >= million -> "${roundNum(price / million, 2)}M"
            price >= thousand -> "${roundNum(price / thousand, 2)}K"
            else -> price.roundToInt().toString()
        }
    }



    fun checkLocMap(location: String): Boolean {
        return (locraw?.mapName ?: false) == location
    }
    fun checkLocGameMode(gameMode: String): Boolean {
        return (locraw?.gameMode ?: false) == gameMode
    }


}