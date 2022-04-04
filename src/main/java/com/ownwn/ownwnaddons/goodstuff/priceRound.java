package com.ownwn.ownwnaddons.goodstuff;

public class priceRound {
    public static String roundPrice(int price) {
        float finalPrice = price / 1000;
        return Float.toString(finalPrice);
    }

}
