package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.ColourUtils;
import com.ownwn.ownwnaddons.utils.FetchOnServerJoin;
import com.ownwn.ownwnaddons.utils.NewConfig;
import com.ownwn.ownwnaddons.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CustomNames {

    public static String username = "-Placeholder";
    // usernames cannot have dashes so there's no chance this will accidentally trigger.

    public static String[] hypixelRanks = {
            "", // leave empty for default rank
            "[VIP] ",
            "[VIP+] ",
            "[MVP] ",
            "[MVP+] ",
            "[MVP++] "

    };


    public static Pattern VIP_PATTERN;
    public static Pattern VIP_PLUS_PATTERN;
    public static Pattern MVP_PATTERN;
    public static Pattern MVP_PLUS_PATTERN;
    public static Pattern MVP_DOUBLEPLUS_PATTERN;

    public static String replacePlayerNameAndRank(String text) {
        if (!NewConfig.CUSTOM_NAME_TOGGLE || NewConfig.CUSTOM_NAME_EDITOR.equals("")) {
            return text;
        }
        if (Minecraft.getMinecraft().thePlayer == null) {
            return text;
        }


        if (!text.contains(username)) {
            return text;
        }

        if (NewConfig.CUSTOM_RANK_TOGGLE && !NewConfig.CUSTOM_RANK_EDITOR.equals("") && NewConfig.PLAYER_HYPIXEL_RANK != 0) {
            if (Utils.stripFormatting(text).contains(hypixelRanks[NewConfig.PLAYER_HYPIXEL_RANK] + username)) {
                Pattern chosenPattern;

                // kid named java 8 switch statement:
                switch (NewConfig.PLAYER_HYPIXEL_RANK) {
                    case 1:
                        chosenPattern = VIP_PATTERN;
                        break;
                    case 2:
                        chosenPattern = VIP_PLUS_PATTERN;
                        break;
                    case 3:
                        chosenPattern = MVP_PATTERN;
                        break;
                    case 4:
                        chosenPattern = MVP_PLUS_PATTERN;
                        break;
                    case 5:
                        chosenPattern = MVP_DOUBLEPLUS_PATTERN;
                        break;
                    default:
                        throw new IllegalArgumentException(OwnwnAddons.PREFIX + "&cInvalid rank: " + NewConfig.PLAYER_HYPIXEL_RANK);
                }

                Matcher matcher = chosenPattern.matcher(text);
                if (matcher.find()) {

                    String newRank = NewConfig.CUSTOM_RANK_EDITOR.replace("&&", "§");
                    text = text.replace(matcher.group(0), newRank + "§r " + username + "§r");
                }
            }
        }

        text = text.replace(username, NewConfig.CUSTOM_NAME_EDITOR.replace("&&", "§") + "§r");
        return text;
    }


    public static String replaceOtherNames(String text) {
        if (Minecraft.getMinecraft().thePlayer == null) {
            return text;
        }
        if (!NewConfig.SHARED_RAINBOW_NAMES) {
            return text;
        }
        if (FetchOnServerJoin.nameList == null || FetchOnServerJoin.nameList.size() == 0) {
            return text;
        }

        for (String name : FetchOnServerJoin.nameList) {

            if (name.equals(username)) {
                continue;
            }

            String newName = "§" + ColourUtils.chooseColour() + name + "§r";
            text = text.replace(name, newName);
        }
        text = text.replace("§zOwnwn§rAddons", "§zOwnwnAddons§r");
        // ^ stop "Ownwn" being coloured when typing the mod name
        return text;
    }


    public static String replaceChromaMessages(String text) {

        if (Minecraft.getMinecraft().thePlayer == null) {
            return text;
        }
        if (NewConfig.CHROMA_TEXT_REPLACE.equals("")) {
            return text;
        }
        String newColour = "§";

        newColour += ColourUtils.chooseColour();

        for (String replacementText: NewConfig.CHROMA_TEXT_REPLACE.replace("&&", "§").split(", ")) {
            if (replacementText.startsWith("§l") || replacementText.startsWith("§l", 2)) {
                newColour = newColour.replace("§l", "") + "§l";
            } else {
                newColour = newColour.replace("§l", "");
            }
            text = text.replace(replacementText, newColour + Utils.stripFormatting(replacementText) + "§r");
            // it *technically* works
        }

        return text;
    }


    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        /* listen for a chat message to ensure the player is not null so we can grab their name.
        this could be done with mc.get$ession().getUsername() but getting the session looks sus
        and probably doesn't support account switching (e.g. essential) */

        if (!username.equals("-Placeholder")) { // the username has already been set
            return;
        }
        username = Minecraft.getMinecraft().thePlayer.getName();

        // compile patterns when the game is open, so that we can include the players' username.
        VIP_PATTERN = Pattern.compile("(§a\\[VIP]) " + username);
        VIP_PLUS_PATTERN = Pattern.compile("(§a\\[VIP§6\\+§a]) " + username);

        MVP_PATTERN = Pattern.compile("(§b\\[MVP]) " + username);
        MVP_PLUS_PATTERN = Pattern.compile("(§b\\[MVP§.\\+§b]|§b\\[MVP(?:§r)+(?:§.)+\\+(?:§r)+(?:§b)+]) " + username);
        MVP_DOUBLEPLUS_PATTERN = Pattern.compile("(§6\\[MVP(?:§.)*\\+\\+(?:§.)*]) " + username);
        // ^ the MVP+ regex is completely cooked, for some reason my name gets load of extra section signs??
        // e.g.  §r§8[§r§5161§r§8] §r§b[MVP§r§4+§r§b] Ownwn§r§f: hi§r
        // have updated the MVP++ pattern just in case this occurs with MVP++ too.
    }
}
