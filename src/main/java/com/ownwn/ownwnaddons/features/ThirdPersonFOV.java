package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ThirdPersonFOV {
    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (!NewConfig.THIRD_PERSON_FOV) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.gameSettings.thirdPersonView == 1) {
            if (mc.gameSettings.fovSetting == NewConfig.THIRD_PERSON_MODIFIER) {
                return;
            }
            mc.gameSettings.fovSetting = NewConfig.THIRD_PERSON_MODIFIER;
        } else {
            if (mc.gameSettings.fovSetting == NewConfig.STANDARD_VIEW_MODIFIER) {
                return;
            }
            mc.gameSettings.fovSetting = NewConfig.STANDARD_VIEW_MODIFIER;
        }


    }
}
