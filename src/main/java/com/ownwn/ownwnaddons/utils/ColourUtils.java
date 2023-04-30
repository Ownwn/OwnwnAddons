package com.ownwn.ownwnaddons.utils;

import com.ownwn.ownwnaddons.commands.Owa;
import net.minecraft.client.Minecraft;

public class ColourUtils {
    public static int colourNum = 0;
    public static long colourChangeDelay = 0;
    public static String[] formattingCodes = {
            "4",
            "c",
            "d",
            "5",
            "1",
            "9",
            "3",
            "b",
            "2",
            "a",
            "e",
            "6"
    };

    public static String getColour() {

        return formattingCodes[colourNum];

    }

    public static void addColour() {
        if (System.currentTimeMillis() - colourChangeDelay < NewConfig.SCUFFED_CHROMA_SPEED + 1) {
            return;
        }
        colourChangeDelay = System.currentTimeMillis();
        colourNum++;
        if (colourNum > 11) {//loop back to red colour
            colourNum = 0;
        }
    }

    public static String InsaneColour(String text) {
        StringBuilder newText = new StringBuilder();
        text = Utils.stripFormatting(text);
        for (int i = 0; i < text.length(); i++) {
            newText.append("§");
            newText.append(getColour());
            newText.append(text.charAt(i));
            newText.append("§r");

        }
        return String.valueOf(newText);
    }

    public static String replacePlayerName(String text) {
        if (Minecraft.getMinecraft().thePlayer == null) {
            return text;
        }

        String playerName = Minecraft.getMinecraft().thePlayer.getName();

        if (!text.contains(playerName)) {
            return text;
        }
        if (NewConfig.CUSTOM_NAME_MODE == 0) {
            return text;
        }

        if (Owa.devMode) {
            return ColourUtils.InsaneColour(text);
        }

        StringBuilder newName = new StringBuilder();
        newName.append("§");

        if (NewConfig.CUSTOM_NAME_MODE == 1) {
            newName.append("z");

        } else {
            newName.append(ColourUtils.getColour());
        }
        newName.append(playerName).append("§r");

        return text.replace(playerName, newName);
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

            if (name.equals(Minecraft.getMinecraft().thePlayer.getName())) {
                continue;
            }

            String newName = "§";
            if (NewConfig.CUSTOM_NAME_MODE == 1) {
                newName += "z" + name;

            } else {
                newName += ColourUtils.getColour() + name;
            }


            text = text.replace(name, newName + "§r");
        }
        return text;
    }

    public static String replaceChromaMessages(String text) {



        if (Minecraft.getMinecraft().thePlayer == null) {
            return text;
        }
        if (NewConfig.CHROMA_TEXT_REPLACE.equals("")) {
            return text;
        }
        String newColour = "§"; // no im not using a stringbuilder

        if (NewConfig.CHROMA_TYPE) {
            newColour += ColourUtils.getColour();
        } else {
            newColour += "z";
        }

        for (String replacementText: NewConfig.CHROMA_TEXT_REPLACE.replace("&&", "§").split(", ")) {
            if (replacementText.startsWith("§l") || replacementText.startsWith("§l", 2)) {
                newColour = newColour.replace("§l", "") + "§l";
            } else {
                newColour = newColour.replace("§l", "");
            }
            text = text.replace(replacementText, newColour + Utils.stripFormatting(replacementText) + "§r");
        }

        return text;
    }
}
