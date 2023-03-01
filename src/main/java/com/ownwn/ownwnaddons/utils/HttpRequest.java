package com.ownwn.ownwnaddons.utils;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class HttpRequest {
    public static JsonObject getResponse(String urlString) {

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (NewConfig.VERBOSE_CODE_SWITCH) { System.out.println("Response Code: " + conn.getResponseCode());}
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

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

        JsonObject profilesResponse = null;
        try {
            profilesResponse = getResponse("https://api.hypixel.net/skyblock/profiles?uuid=" + UUID + "&key=" + key);
        } catch (Exception gg) {
            UChat.chat(OwnwnAddons.PREFIX + "&cError fetching profiles with uuid and key");
        }
        try {
            if (profilesResponse != null && !profilesResponse.get("success").getAsBoolean()) {
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
        if (NewConfig.VERBOSE_CODE_SWITCH){ System.out.println("Looping through profiles...");}
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


    public static JsonObject lbin() {
        return HttpRequest.getResponse("https://moulberry.codes/lowestbin.json");
    }

    public static JsonObject bz() {

            return HttpRequest.getResponse("https://api.hypixel.net/skyblock/bazaar").getAsJsonObject("products");
    }

    public static void postRequest(String webUrl, String message) throws IOException { // inspiration taken from https://github.com/JellyLabScripts/FarmHelper
        String decompileInfo = "this features sends a customisable message to a customisable webhook, intended to replace bridge bots. It does not send any confidential information";

        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        URL url = new URL(webUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("User-Agent", "OwnwnAddons");
        conn.setDoOutput(true);


        //DiscordWebhook.JSONObject json = new DiscordWebhook.JSONObject();
        JsonObject json = new JsonObject();

        json.addProperty("content", message);
        json.addProperty("username", player.getName());
        json.addProperty("avatar_url", "https://crafatar.com/renders/head/" + player.getUniqueID());


        OutputStream stream = conn.getOutputStream();
        stream.write(json.toString().getBytes(StandardCharsets.UTF_8));
        stream.flush();
        stream.close();

        conn.getInputStream().close(); //I'm not sure why, but it doesn't work without getting the InputStream
        conn.disconnect();

    }

}




