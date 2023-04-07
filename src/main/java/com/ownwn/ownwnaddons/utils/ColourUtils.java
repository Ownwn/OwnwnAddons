package com.ownwn.ownwnaddons.utils;

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


//            "a",
//            "b",
//            "c",
//            "d",
//            "e",
//            "f",
//            "0",
//            "1",
//            "2",
//            "3",
//            "4",
//            "5",
//            "6",
//            "7",
//            "8",
//            "9"
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
}
