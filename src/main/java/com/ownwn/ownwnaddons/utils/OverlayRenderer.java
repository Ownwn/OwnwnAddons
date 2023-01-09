package com.ownwn.ownwnaddons.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class OverlayRenderer {

    static FontRenderer renderer = Minecraft.getMinecraft().fontRendererObj;
    public static void countdown(String text, long finishTime, int x, int y) {

        if (Minecraft.getMinecraft().currentScreen != null || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }

        long now = System.currentTimeMillis();
        if (now < finishTime) {
            renderer.drawString(text, x, y, 0);
        }

    }


    public static void stopwatch(String text, int x, int y) {
        if (Minecraft.getMinecraft().currentScreen != null || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }

        renderer.drawString(text, x, y, 0);
    }
}
