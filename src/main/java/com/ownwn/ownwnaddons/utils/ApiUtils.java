package com.ownwn.ownwnaddons.utils;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;

public class ApiUtils {
    public static JsonObject bz() {

            return HttpRequest.getResponse("https://api.hypixel.net/skyblock/bazaar").getAsJsonObject("products");
    }

    public static JsonObject lbin() {
        return HttpRequest.getResponse("https://moulberry.codes/lowestbin.json");
    }

    public static int parseBz(String itemName, JsonObject bazaar) {
        try {
            return bazaar.get(itemName).getAsJsonObject().get("quick_status").getAsJsonObject().get("buyPrice").getAsInt();
        } catch (Exception e) {
            UChat.chat(OwnwnAddons.PREFIX + "&cError reaching the Bazaar API! ");
            e.printStackTrace();
            return 0;
        }
    }
}
