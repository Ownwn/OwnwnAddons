package com.ownwn.ownwnaddons.goodstuff;

public class PriceRound {
    public static String roundPrice(double price) {
        if (price >= 1000000000) {
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

}
