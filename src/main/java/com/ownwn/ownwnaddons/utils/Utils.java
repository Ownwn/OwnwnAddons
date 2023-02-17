package com.ownwn.ownwnaddons.utils;

import com.google.gson.JsonObject;

import java.util.regex.Pattern;

public class Utils {
    public static int JsonInt(JsonObject source, String key) {
        int returnNum = 0;
        try {
            returnNum = source.get(key).getAsInt();
        } catch (NullPointerException bad) {
            if (NewConfig.VERBOSE_CODE_SWITCH) {
                System.out.println("Could not find integer value of " + key + " from JsonObject");
            }
        }

        return returnNum;

    }

    public static long JsonLong(JsonObject source, String key) {
        return source.get(key).getAsLong();
    }

    public static double JsonDouble(JsonObject source, String key) {
        return source.get(key).getAsDouble();
    }

    public static String JsonString(JsonObject source, String key) {
        return source.get(key).getAsString();
    }



    public static String roundPrice(double price) {
        if (price >= 1000000000000L) {
            double doublePrice = price / 1000000000000L;
            return ((Math.round(doublePrice * 100.0) / 100.0) + "T");
        }
        else if (price >= 1000000000) {
            double doublePrice = price / 1000000000;
            return ((Math.round(doublePrice * 100.0) / 100.0) + "B");
        }
        else if (price >= 1000000) {
            double doublePrice = price / 1000000;
            return ((Math.round(doublePrice * 100.0) / 100.0) + "M");
        }
        else if (price >= 1000) {
            double doublePrice = price / 1000;
            return ((Math.round(doublePrice * 100.0) / 100.0) + "K");
        }
        else {

            return Double.toString(Math.round(price));
        }
    }

    // https://stackoverflow.com/questions/22186778/using-math-round-to-round-to-one-decimal-place
    public static double roundNum (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public static String stripFormatting(String text) { // taken from https://github.com/BiscuitDevelopment/SkyblockAddons
        return Pattern.compile("(?i)§[0-9A-FK-ORZ]").matcher(text).replaceAll("");
    }
}
