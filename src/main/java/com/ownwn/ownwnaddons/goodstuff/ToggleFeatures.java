package com.ownwn.ownwnaddons.goodstuff;

import com.ownwn.ownwnaddons.outside.ConfigStuff;
import net.minecraft.util.EnumChatFormatting;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class ToggleFeatures {
    public static void write(String feature) {
        AtomicReference<String> toggle = new AtomicReference<>("true");
        Thread T = new Thread(() -> {

            String toggled;
            try {
                toggled = ConfigStuff.getString("features", feature);
            } catch (Exception b) {
                SendMsg.Msg(EnumChatFormatting.RED + "Error fetching config values");
                return;
            }
            if (Objects.equals(toggled, "true")) {
                toggle.set("false");
            }
            else {
                toggle.set("true");
            }

            try {
                ConfigStuff.writeStringConfig("features", feature, toggle.get());
                SendMsg.Msg(EnumChatFormatting.GREEN + "Successfully toggled " + EnumChatFormatting.AQUA + feature + EnumChatFormatting.GREEN + " to " + EnumChatFormatting.AQUA + toggle.get());
            } catch (Exception e) {
                SendMsg.Msg(EnumChatFormatting.RED + "Something went wrong writing to the config!");
            }

        });
        T.start();
    }
}
