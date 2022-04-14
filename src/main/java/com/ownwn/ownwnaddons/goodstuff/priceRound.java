package com.ownwn.ownwnaddons.goodstuff;

public class priceRound {
    public static String roundPrice(float price) {
        if (price >= 1000000000) {
            float floatPrice = price / 1000000000;
            return ((Math.round(floatPrice * 100.0) / 100.0) + "B");
        }
        else if (price >= 1000000) {
            float floatPrice = price / 1000000;
            return ((Math.round(floatPrice * 100.0) / 100.0) + "M");
        }
        else if (price >= 1000) {
            float floatPrice = price / 1000;
            return ((Math.round(floatPrice * 100.0) / 100.0) + "K");
        }
        else {

            return Float.toString(Math.round(price));
        }
    }

}
