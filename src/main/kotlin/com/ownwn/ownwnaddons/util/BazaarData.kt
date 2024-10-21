package com.ownwn.ownwnaddons.util

import cc.polyfrost.oneconfig.libs.universal.UChat
import cc.polyfrost.oneconfig.utils.NetworkUtils
import com.google.gson.JsonObject
import com.ownwn.ownwnaddons.OwnwnAddons

object BazaarData {
    private var json: JsonObject? = null

    fun load() {
        json = NetworkUtils.getJsonElement("https://api.hypixel.net/skyblock/bazaar").asJsonObject.get("products").asJsonObject
    }

    fun getItemPrice(name: String): Double {
        val price = json?.get(name)?.asJsonObject?.get("quick_status")?.asJsonObject?.get("buyPrice")?.asDouble ?: run {
            UChat.chat(OwnwnAddons.PREFIX + "&cError reaching the Bazaar API! ");
            Double.NaN
        }
        return price
    }
}