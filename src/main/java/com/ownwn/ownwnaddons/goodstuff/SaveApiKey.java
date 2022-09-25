package com.ownwn.ownwnaddons.goodstuff;

import com.ownwn.ownwnaddons.outside.ConfigStuff;
import net.minecraft.util.EnumChatFormatting;

public class SaveApiKey {
    public static void writeKey(String key) {
        Thread T = new Thread(() -> {
            try {
                ConfigStuff.writeStringConfig("api", "Key", key);
                SendMsg.Msg(EnumChatFormatting.GREEN + "Successfully wrote " + EnumChatFormatting.AQUA + key + EnumChatFormatting.GREEN + " to the config!");
            } catch (Exception e) {
                SendMsg.Msg(EnumChatFormatting.RED + "Something went wrong writing to the config!");
            }

        });
        T.start();
    }
}
