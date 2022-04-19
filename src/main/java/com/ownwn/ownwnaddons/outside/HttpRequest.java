package com.ownwn.ownwnaddons.outside;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.goodstuff.SendMsg;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.io.IOUtils;

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
            SendMsg.Msg(EnumChatFormatting.RED + "Error connecting to: \n " + EnumChatFormatting.RED + urlString + "\n" + EnumChatFormatting.RED + " See logs for more details.");
            ex.printStackTrace();
        }
        return new JsonObject();
    }

    public static String getUUID(String username) { //ty for the code DSM :)
        JsonObject uuidResponse = getResponse("https://api.mojang.com/users/profiles/minecraft/" + username);
        return uuidResponse.get("id").getAsString();
    }


    public static String getLatestProfileID(String UUID, String key) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        // Get profiles
        System.out.println("Fetching profiles...");

        JsonObject profilesResponse = getResponse("https://api.hypixel.net/skyblock/profiles?uuid=" + UUID + "&key=" + key);
        if (!profilesResponse.get("success").getAsBoolean()) {
            String reason = profilesResponse.get("cause").getAsString();
            SendMsg.Msg(EnumChatFormatting.RED + "Failed with reason: " + reason);
            return null;
        }
        if (profilesResponse.get("profiles").isJsonNull()) {
            SendMsg.Msg(EnumChatFormatting.RED + "This player hasn't played Skyblock!");
            return null;
        }

        // Loop through profiles to find latest
        System.out.println("Looping through profiles...");
        String latestProfile = "";
        long latestSave = 0;
        JsonArray profilesArray = profilesResponse.get("profiles").getAsJsonArray();

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

        return latestProfile;
    }


    public static int lbin(String item) {
        JsonObject lowestbin = HttpRequest.getResponse("https://moulberry.codes/lowestbin.json");
        return lowestbin.get(item).getAsInt();
    }

    public static float bz(String item) throws IOException {
        JsonObject bazaar = HttpRequest.getResponse("https://api.hypixel.net/skyblock/bazaar");

        return bazaar.getAsJsonObject("products").getAsJsonObject(item).getAsJsonArray("sell_summary").get(0).getAsFloat(); //.getDouble("pricePerUnit") cross off get asfloat
    }

}




