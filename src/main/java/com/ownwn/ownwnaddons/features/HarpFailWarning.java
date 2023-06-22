package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.CheckSlot;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HarpFailWarning {

    @SubscribeEvent
    public void onSound(PlaySoundEvent event) {
        // does not work when minecraft volume is set to 0%, I suggest using your OS's volume mixer instead
        if (!NewConfig.HARP_MISCLICK_WARNING) {
            return;
        }
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }

        if (!CheckSlot.openGuiName.startsWith("Harp")) {
            return;
        }
        if (!event.name.equals("note.bassattack") || event.sound.getVolume() != 1f) {
            return;
        }

        UChat.chat(OwnwnAddons.PREFIX + "&cHarp Failed!");
    }
}
