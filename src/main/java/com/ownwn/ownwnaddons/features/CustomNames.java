package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.ColourUtils;
import com.ownwn.ownwnaddons.utils.FetchOnServerJoin;
import com.ownwn.ownwnaddons.utils.NewConfig;
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

    private static final Pattern skyblockLevelPattern = Pattern.compile("§8\\[(§r)*§.(\\d{0,3})(§r)*§8]");

    public static String replacePlayerNameAndRank(String text, String unformatted) {
        if (!NewConfig.CUSTOM_NAME_TOGGLE || NewConfig.CUSTOM_NAME_EDITOR.isEmpty()) {
            return text;
        }

        if (!unformatted.contains(username)) return text;

        if (NewConfig.CUSTOM_RANK_TOGGLE && !NewConfig.CUSTOM_RANK_EDITOR.isEmpty() && NewConfig.PLAYER_HYPIXEL_RANK != 0) {
            if (unformatted.contains(hypixelRanks[NewConfig.PLAYER_HYPIXEL_RANK] + username)) {
                Matcher matcher = selectPlayerRank(text);
                if (matcher.find()) {

                    String newRank = NewConfig.CUSTOM_RANK_EDITOR.replace("&&", "§");
                    text = text.replace(matcher.group(0), newRank + "§r " + username + "§r");
                }
            }
        }

        text = text.replace(username, NewConfig.CUSTOM_NAME_EDITOR.replace("&&", "§") + "§r");
        return text;
    }


        public static String replaceOtherNames(String text, String unformatted) {
            if (!NewConfig.SHARED_RAINBOW_NAMES) return text;

            if (FetchOnServerJoin.nameList == null || FetchOnServerJoin.nameList.isEmpty()) return text;

            for (String name : FetchOnServerJoin.nameList) {

                if (name.equals(username)) continue;
                if (!unformatted.contains(name)) continue;

                String newName = "§" + ColourUtils.chooseColour() + name + "§r";
                text = text.replace(name, newName);
            }

            return text;
        }


    public static String replaceChromaMessages(String text) {

        if (NewConfig.CHROMA_TEXT_REPLACE.isEmpty()) return text;

        String newColour = "§" + ColourUtils.chooseColour();
        String[] replacementList = NewConfig.CHROMA_TEXT_REPLACE.replace("&&", "§").split(", ");

        for (String replacement : replacementList) {
            text = text.replace(replacement, newColour + replacement + "§r");
        }

        return text;
    }

    public static String replaceLevelNumber(String text, String unformatted) {
        if (!NewConfig.CUSTOM_LEVEL_TOGGLE || NewConfig.CUSTOM_LEVEL_EDITOR.isEmpty()) {
            return text;
        }

        if (!unformatted.contains("] " + Minecraft.getMinecraft().thePlayer.getName())) {
            return text;
        }

        Matcher levelMatcher = skyblockLevelPattern.matcher(text);
        StringBuffer result = new StringBuffer();
        while (levelMatcher.find()) {
            levelMatcher.appendReplacement(result, "§8[§." + NewConfig.CUSTOM_LEVEL_EDITOR.replace("&&", "§") + "§8]§r");
        }
        levelMatcher.appendTail(result);

        return result.toString();
    }


    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        /* listen for a chat message to ensure the player is not null so we can grab their name.
        this could be done with mc.get$ession().getUsername() but getting the session looks sus
        and probably doesn't support account switching (e.g. essential) */

        if (!username.equals("-Placeholder")) return; // the username has already been set
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


    private static Matcher selectPlayerRank(String text) {
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

        return chosenPattern.matcher(text);
    }


//    @SubscribeEvent
//    public void onChat2(ClientChatReceivedEvent event) {
//        // event.message.getFormattedText() will look like this: §8[§f66§8] §a[VIP] Jaxxkk§f§r§f: test§r
//        String unformattedText = Utils.stripFormatting(event.message.getFormattedText());
//        String noRankText = unformattedText.replace("[VIP] ", "");
//        noRankText = noRankText.replace("[VIP+] ", "");
//        noRankText = noRankText.replace("[VIP+] ", "");
//        noRankText = noRankText.replace("[MVP] ", "");
//        noRankText = noRankText.replace("[MVP+] ", "");
//        noRankText = noRankText.replace("[MVP++] ", "");
//        noRankText = noRankText.replace("[YOUTUBE] ", "");
//        if (!noRankText.matches("^\\[\\d+] " + username)) {
//            return;
//        }
//        // we got here
//    }
}
