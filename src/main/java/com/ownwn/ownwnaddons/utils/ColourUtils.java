package com.ownwn.ownwnaddons.utils;

public class ColourUtils {
    public static int colourNum = 0;
    public static long colourChangeDelay = 0;
    public static String[] formattingCodes = { // sorted by colour instead of alphabetically
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


    public static String scuffedChroma() {
        return formattingCodes[colourNum];
    }


    public static String chooseColour() {
        if (NewConfig.CHROMA_TYPE) {
            return scuffedChroma();
        }
        return "z"; // "z" for SBA's chroma §z

    }


    public static void addColour() {
        if (System.currentTimeMillis() - colourChangeDelay < NewConfig.SCUFFED_CHROMA_SPEED + 1) {
            return;
        }
        colourChangeDelay = System.currentTimeMillis();
        colourNum++;
        if (colourNum > 11) { //loop back to red colour
            colourNum = 0;
        }
    }
}
