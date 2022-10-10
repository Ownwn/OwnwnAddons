package com.ownwn.ownwnaddons.outside;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import gg.essential.universal.UChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpRequest {
    public static JsonObject getResponse(String urlString) {

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if (OwnwnAddons.config.VERBOSE_CODE_SWITCH) {
                    System.out.println("connection is OK");
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String input;
                StringBuilder response = new StringBuilder();

                while ((input = in.readLine()) != null) {
                    response.append(input);
                }
                in.close();

                Gson gson = new Gson();
                return gson.fromJson(response.toString(), JsonObject.class);

            }
        } catch (IOException ex) {
            UChat.chat(OwnwnAddons.PREFIX + "&cError connecting to: \n " + urlString + "\n See logs for more details.");
            ex.printStackTrace();
        }
        return new JsonObject();
    }

    public static String getUUID(String username) { //ty for the code DSM :)
        JsonObject uuidResponse = getResponse("https://api.mojang.com/users/profiles/minecraft/" + username);
        return uuidResponse.get("id").getAsString();
    }


    public static String getLatestProfileID(String UUID, String key) {
        // Get profiles

        if (OwnwnAddons.config.VERBOSE_CODE_SWITCH){ System.out.println("Fetching profiles...");}
        JsonObject profilesResponse = null;
        try {
            profilesResponse = getResponse("https://api.hypixel.net/skyblock/profiles?uuid=" + UUID + "&key=" + key);
            if (OwnwnAddons.config.VERBOSE_CODE_SWITCH){ System.out.println("The list of profiles is: https://api.hypixel.net/skyblock/profiles?uuid=" + UUID + "&key=" + key);}
        } catch (Exception gg) {
            UChat.chat(OwnwnAddons.PREFIX + "&cError fetching profiles with uuid and key");
        }
        try {
            if (profilesResponse != null && !profilesResponse.get("success").getAsBoolean()) {
                if (OwnwnAddons.config.VERBOSE_CODE_SWITCH){ System.out.println("we got here");}
                String reason = profilesResponse.get("cause").getAsString();
                UChat.chat(OwnwnAddons.PREFIX + "&cFailed with reason: " + reason);
                return null;
            }
        } catch (Exception ignored) {
        }
        if (profilesResponse != null && profilesResponse.get("profiles").isJsonNull()) {
            UChat.chat(OwnwnAddons.PREFIX + "&cThis player hasn't played Skyblock!");
            return null;
        }

        // Loop through profiles to find latest
        if (OwnwnAddons.config.VERBOSE_CODE_SWITCH){ System.out.println("Looping through profiles...");}
        String latestProfile = "";
        long latestSave = 0;
        JsonArray profilesArray = null;
        if (profilesResponse != null) {
            profilesArray = profilesResponse.get("profiles").getAsJsonArray();
        }

        if (profilesArray != null) {
            for (JsonElement profile : profilesArray) {
                JsonObject profileJSON = profile.getAsJsonObject();
                long profileLastSave = 1;
                if (profileJSON.get("members").getAsJsonObject().get(UUID).getAsJsonObject().has("last_save")) {
                    profileLastSave = profileJSON.get("members").getAsJsonObject().get(UUID).getAsJsonObject().get("last_save").getAsLong();
                }

                if (profileLastSave > latestSave) {
                    latestProfile = profileJSON.get("profile_id").getAsString();
                    latestSave = profileLastSave;
                }
            }
        }

        return latestProfile;
    }


    public static int lbin(String item) {
        JsonObject lowestbin = HttpRequest.getResponse("https://moulberry.codes/lowestbin.json");
        return lowestbin.get(item).getAsInt();
    }

    public static int bz(String item) {

            JsonObject bazaar = HttpRequest.getResponse("https://api.hypixel.net/skyblock/bazaar");

            return bazaar.getAsJsonObject("products").getAsJsonObject(item).getAsJsonObject("quick_status").get("buyPrice").getAsInt();

    }

}




