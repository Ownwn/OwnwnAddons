package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.HttpRequest;
import com.ownwn.ownwnaddons.utils.NewConfig;
import com.ownwn.ownwnaddons.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartyFinder {

    private static final Pattern PARTY_FINDER_JOIN = Pattern.compile("Party Finder > (\\w+) joined the dungeon group! \\((\\w+) Level (\\d+)\\)"); // for some reason formatting codes don't show on this message???

    @SubscribeEvent// thank you Cowlection for the code inspiration https://github.com/cow-mc/Cowlection/
    public void onChat(ClientChatReceivedEvent event) {
        if (event.message.getUnformattedText().contains("joined the dungeon group! (")) {
            if (!NewConfig.PARTY_FINDER_SWITCH) {
                return;
            }

            String message = EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getUnformattedText());

            Matcher dungMatch = PARTY_FINDER_JOIN.matcher(message);


            if (!dungMatch.find()) {
                return;
            }

            new Thread(() -> {
                String username = dungMatch.group(1);
                JsonObject goodProfile = null;

                String profileUrl = "https://sky.shiiyu.moe/api/v2/profile/" + username; // damn i better not forget to change this from localhost
                if (NewConfig.VERBOSE_CODE_SWITCH){ System.out.println("The profile api url is " + profileUrl);}

                JsonObject profileResponse = HttpRequest.getResponse(profileUrl);
                JsonObject profileList = profileResponse.get("profiles").getAsJsonObject();

                if (profileResponse.has("error")) {
                    String reason = profileResponse.get("error").getAsString();
                    UChat.chat(OwnwnAddons.PREFIX + "&cYou messed up: " + reason);
                    return;
                }


                for (Map.Entry<String, JsonElement> entry : profileList.entrySet()) {
                    String key = entry.getKey();

                    if (profileList.get(key).getAsJsonObject().get("current").getAsBoolean()) {
                        goodProfile = profileList.get(key).getAsJsonObject();
                        break;
                    }
                }

                if (goodProfile == null) {
                    UChat.chat(OwnwnAddons.PREFIX + "&cError getting current profile. Check the logs for more information");
                    return;
                }

                if (NewConfig.VERBOSE_CODE_SWITCH){ System.out.println("The current profile cutename is " + goodProfile.get("cute_name").getAsString());}

                JsonObject dungeons = goodProfile.get("data").getAsJsonObject().get("dungeons").getAsJsonObject();
                JsonObject rawStats = goodProfile.get("raw").getAsJsonObject().get("stats").getAsJsonObject();
                JsonArray inventory = goodProfile.get("items").getAsJsonObject().get("inventory").getAsJsonArray();

                double cataLvl = Utils.roundNum(dungeons.get("catacombs").getAsJsonObject().get("level").getAsJsonObject().get("level").getAsInt() + dungeons.get("catacombs").getAsJsonObject().get("level").getAsJsonObject().get("progress").getAsDouble(), 2);
                double skillAvg = Utils.roundNum(goodProfile.get("data").getAsJsonObject().get("average_level").getAsDouble(), 2);
                double senitherWeight = Utils.roundNum(goodProfile.get("data").getAsJsonObject().get("weight").getAsJsonObject().get("senither").getAsJsonObject().get("overall").getAsDouble(), 1);

                String networth = Utils.roundPrice(goodProfile.get("data").getAsJsonObject().get("networth").getAsJsonObject().get("networth").getAsLong());


                int bloodKills =
                Utils.JsonInt(rawStats, "kills_watcher_summon_undead") +
                Utils.JsonInt(rawStats, "kills_master_watcher_summon_undead") +

                Utils.JsonInt(rawStats, "kills_watcher_bonzo") +
                Utils.JsonInt(rawStats, "kills_master_watcher_bonzo") +

                Utils.JsonInt(rawStats, "kills_watcher_scarf") +
                Utils.JsonInt(rawStats, "kills_master_watcher_scarf") +

                Utils.JsonInt(rawStats, "kills_watcher_livid") +
                Utils.JsonInt(rawStats, "kills_master_watcher_livid") +

                (Utils.JsonInt(rawStats, "kills_watcher_giant_laser") * 4) + // estimate total precursor giant kills
                (Utils.JsonInt(rawStats, "kills_master_watcher_giant_laser") * 4); // ^^
                int normalCompletions = 0;
                int masterCompletions = 0;

                for (int i = 0; i <= 7; i++) {
                    String x = String.valueOf(i);
                    try {
                        normalCompletions += goodProfile.get("raw").getAsJsonObject().get("dungeons").getAsJsonObject().get("dungeon_types").getAsJsonObject().get("catacombs").getAsJsonObject().get("tier_completions").getAsJsonObject().get(x).getAsInt();
                    } catch (NullPointerException ignored) {}
                    try {
                        masterCompletions += goodProfile.get("raw").getAsJsonObject().get("dungeons").getAsJsonObject().get("dungeon_types").getAsJsonObject().get("master_catacombs").getAsJsonObject().get("tier_completions").getAsJsonObject().get(x).getAsInt();
                    } catch (NullPointerException ignored) {}
                }
                int totalTimesPlayed = normalCompletions + masterCompletions;


                String itemName = "";
                StringBuilder itemList = new StringBuilder();
                for (int i = 0; i<= 35; i++) {
                    try {
                        itemName = inventory.get(i).getAsJsonObject().get("tag").getAsJsonObject().get("display").getAsJsonObject().get("Name").getAsString();
                    } catch (NullPointerException ignored) {}

                    if (itemName.contains("Juju Shortbow")) {
                        itemList.append("\n &a- ").append(itemName);
                    } else if (itemName.contains("Hyperion") || itemName.contains("Astraea") || itemName.contains("Scylla") || itemName.contains("Valkyrie")) {
                        itemList.append("\n &a- ").append(itemName);
                    } else if (itemName.contains("Terminator")) {
                        itemList.append("\n &a- ").append(itemName);
                    } else if (itemName.contains("Midas Staff")) {
                        itemList.append("\n &a- ").append(itemName);
                    }

                }



                IChatComponent kickButton = new ChatComponentText(" §c§l[KICK]").setChatStyle(new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p kick " + username)));

                UChat.chat(OwnwnAddons.PREFIX + "&bStats for " + dungMatch.group(1) + "&b:" +
                "\n &bCata Lvl: &a" + cataLvl +
                "\n &bSkill Avg: &a" + skillAvg +

                "\n\n &bSenither Weight: &a" + senitherWeight +
                "\n &bNetworth: &a" + networth +

                "\n\n &bSecrets: &a" + dungeons.get("secrets_found").getAsInt() +
                "\n &bSecrets Per Run: &a" + Utils.roundNum(dungeons.get("secrets_found").getAsDouble() / (double) totalTimesPlayed, 2) +

                "\n\n &bBlood Mob Kills: &a" + bloodKills +
                "\n &bNotable Items: \n&a" + itemList



                );
                if (NewConfig.SECRET_PARTY_FINDER) {
                    UChat.chat("\n &bAverage sex per day: &a0" +
                    "\n &bNumber of times wiped: &a" + (int) Utils.roundNum(Math.random() * (9)+2, 0) +
                    "\n &bNumber of bitches: &a0");
                }
                Minecraft.getMinecraft().thePlayer.addChatMessage(kickButton); // clickable text


            }).start();

        }
    }
}
