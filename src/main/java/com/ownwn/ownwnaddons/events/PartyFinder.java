package com.ownwn.ownwnaddons.events;

import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.outside.HttpRequest;
import gg.essential.universal.UChat;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.minecraft.util.EnumChatFormatting.getTextWithoutFormattingCodes;

public class PartyFinder {

    private static final Pattern PARTY_FINDER_JOIN = Pattern.compile("^Dungeon Finder > (\\w+) joined the dungeon group! \\(([A-Z][a-z]+) Level (\\d+)\\)$");

    @SubscribeEvent // thank you Cowlection for the code
    public void onChat(ClientChatReceivedEvent event) {
        if (event.message.getUnformattedText().contains("joined the dungeon group! (")) {

            if (!OwnwnAddons.config.PARTY_FINDER_SWITCH) {
                return;
            }
            String message = getTextWithoutFormattingCodes(event.message.getUnformattedText());

            Matcher dungMatch = PARTY_FINDER_JOIN.matcher(message);


            if (dungMatch.find()) {
                event.setCanceled(true);
                new Thread(() -> {



                    String username = dungMatch.group(1);
                    String key = OwnwnAddons.config.API_KEY_TEXT;

                    if (key.equals("")) {
                        UChat.chat(OwnwnAddons.PREFIX + "&cYou don't have an API key bozo. put it in the config");
                        return;
                    }
                    String uuid;
                    try {
                        uuid = HttpRequest.getUUID(username);
                    } catch (NullPointerException e){
                        UChat.chat(OwnwnAddons.PREFIX + "&cInvalid player name: " + username);
                        return;
                    }
                    String newestProfile;
                    try {
                        newestProfile = HttpRequest.getLatestProfileID(uuid, key);
                    } catch (Exception a) {
                        UChat.chat(OwnwnAddons.PREFIX + "&cError getting the latest profile id");
                        return;
                    }
                    if (newestProfile == null) return;
                    String profileURL = "https://api.hypixel.net/skyblock/profile?profile=" + newestProfile + "&key=" + key;
                    if (OwnwnAddons.config.VERBOSE_CODE_SWITCH){ System.out.println("The link to the latest profile is: " + profileURL);}
                    JsonObject profileResponse = HttpRequest.getResponse(profileURL);
                    if (!profileResponse.get("success").getAsBoolean()) {
                        String reason = profileResponse.get("cause").getAsString();
                        UChat.chat(OwnwnAddons.PREFIX + "you messed up: " + reason);
                        return;
                    }
                    String invBase64 = profileResponse.get("profile").getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject().get("inv_armor").getAsJsonObject().get("data").getAsString();
                    InputStream invStream = new ByteArrayInputStream(Base64.getDecoder().decode(invBase64));
                    try {
                        NBTTagCompound inventory = CompressedStreamTools.readCompressed(invStream);
                        NBTTagList inventoryList = inventory.getTagList("i", 10);

                        String slot1 = "&9None";
                        String slot2 = "&9None";
                        String slot3 = "&9None";
                        String slot4 = "&9None";

                        for (int i = 0; i < inventoryList.tagCount(); i++) {

                            NBTTagCompound inventorySlot = inventoryList.getCompoundTagAt(i);
                            if (inventorySlot.hasNoTags()) continue;

                            String itemName = inventorySlot.getCompoundTag("tag").getCompoundTag("display").getString("Name");

                            switch (i) {
                                case 0:
                                    slot1 = itemName;
                                    break;
                                case 1:
                                    slot2 = itemName;
                                    break;
                                case 2:
                                    slot3 = itemName;
                                    break;
                                case 3:
                                    slot4 = itemName;
                                    break;
                                default:
                                    System.err.println("An error has occurred.");
                                    break;

                            }
                        }
                        invStream.close();

                        UChat.chat(OwnwnAddons.PREFIX + "\n" +
                                "&b " + username + "'s Armour:\n&4 - " +
                                slot1 + "\n&4 - " +
                                slot2 + "\n&4 - " +
                                slot3 + "\n&4 - " +
                                slot4);





                    } catch (IOException ex) {
                        UChat.chat("&cshit went really wrong :(");
                        ex.printStackTrace();
                        return;
                    }

                    boolean joinedYourself = username.equals(Minecraft.getMinecraft().thePlayer.getName());
                    if (!joinedYourself) {
                        String dungeonClass = dungMatch.group(2) + " Lvl " + dungMatch.group(3);
                        UChat.chat("&a" + dungMatch.group(1) + "&7 - &b" + dungeonClass);

                    }

                }).start();
            }
        }
    }
}
