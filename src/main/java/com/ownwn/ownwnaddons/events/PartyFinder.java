package com.ownwn.ownwnaddons.events;

import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.goodstuff.SendMsg;
import com.ownwn.ownwnaddons.outside.HttpRequest;
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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartyFinder {

    private static final Pattern PARTY_FINDER_JOIN = Pattern.compile("^Dungeon Finder > (\\w+) joined the dungeon group! \\(([A-Z][a-z]+) Level (\\d+)\\)$");

    @SubscribeEvent // thank you Cowlection for the code
    public void onChat(ClientChatReceivedEvent event) {
        if (event.message.getUnformattedText().contains("joined the dungeon group! (")) {

            if (!OwnwnAddons.config.PARTY_FINDER_SWITCH) {
                return;
            }
            String message = EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getUnformattedText());

            Matcher dungMatch = PARTY_FINDER_JOIN.matcher(message);


            if (dungMatch.find()) {
                event.setCanceled(true);
                new Thread(() -> {



                    String username = dungMatch.group(1);
                    String key = OwnwnAddons.config.API_KEY_TEXT;

                    if (key.equals("")) {
                        SendMsg.Msg(EnumChatFormatting.RED + "You don't have an API key bozo. put it in the config");
                        return;
                    }
                    String uuid;
                    try {
                        uuid = HttpRequest.getUUID(username);
                    } catch (NullPointerException e){
                        SendMsg.Msg(EnumChatFormatting.RED + "Invalid player name: " + username);
                        return;
                    }
                    String newestProfile;
                    try {
                        newestProfile = HttpRequest.getLatestProfileID(uuid, key);
                    } catch (Exception a) {
                        SendMsg.Msg(EnumChatFormatting.RED + "Error getting the latest profile id");
                        return;
                    }
                    if (newestProfile == null) return;
                    System.out.println(newestProfile);
                    String profileURL = "https://api.hypixel.net/skyblock/profile?profile=" + newestProfile + "&key=" + key;
                    System.out.println(profileURL);
                    JsonObject profileResponse = HttpRequest.getResponse(profileURL);
                    if (!profileResponse.get("success").getAsBoolean()) {
                        String reason = profileResponse.get("cause").getAsString();
                        SendMsg.Msg("you messed up: " + reason);
                        return;
                    }

                    String invBase64 = profileResponse.get("profile").getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject().get("inv_contents").getAsJsonObject().get("data").getAsString();
                    InputStream invStream = new ByteArrayInputStream(Base64.getDecoder().decode(invBase64));
                    try {
                        NBTTagCompound inventory = CompressedStreamTools.readCompressed(invStream);
                        NBTTagList inventoryList = inventory.getTagList("i", 10);

                        String slot1 = EnumChatFormatting.BLUE + "None";
                        String slot2 = EnumChatFormatting.BLUE + "None";
                        String slot3 = EnumChatFormatting.BLUE + "None";
                        String slot4 = EnumChatFormatting.BLUE + "None";

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

                        SendMsg.Msg("" + EnumChatFormatting.BOLD + "-------------------\n" +
                                EnumChatFormatting.AQUA + " " + username + "'s Armour:\n" +
                                EnumChatFormatting.YELLOW + " Helmet:      " + slot1 + "\n" +
                                EnumChatFormatting.YELLOW + " Chestplate: " + slot2 + "\n" +
                                EnumChatFormatting.YELLOW + " Leggings:   " + slot3 + "\n" +
                                EnumChatFormatting.YELLOW + " Boots:       " + slot4 + "\n" +
                                EnumChatFormatting.YELLOW + " " + EnumChatFormatting.BOLD + "-------------------");





                    } catch (IOException ex) {
                        SendMsg.Msg(EnumChatFormatting.RED + "shit went really wrong :(");
                        ex.printStackTrace();
                        return;
                    }

                    boolean joinedYourself = username.equals(Minecraft.getMinecraft().thePlayer.getName());
                    if (!joinedYourself) {
                        String dungeonClass = dungMatch.group(2) + " Lvl " + dungMatch.group(3);
                        SendMsg.Msg(EnumChatFormatting.GREEN + dungMatch.group(1) + EnumChatFormatting.GRAY + " - " + EnumChatFormatting.AQUA + dungeonClass);

                    }

                }).start();
            }
        }
    }
}
