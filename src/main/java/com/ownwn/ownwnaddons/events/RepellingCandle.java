package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.goodstuff.SendMsg;
import com.ownwn.ownwnaddons.outside.ConfigStuff;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Objects;


public class RepellingCandle {
    Integer yee = 1;

    @SubscribeEvent
    public void sadsada(TickEvent event) {
        if (Minecraft.getMinecraft().currentScreen != null ||
                Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }

        if (!OwnwnAddons.config.REPELLING_CANDLE_SWITCH) {
            return;
        }

        ItemStack inHand = Minecraft.getMinecraft().thePlayer.getHeldItem();

        if (inHand == null) {
            return;
        }
        if (inHand.getDisplayName().equals("Repelling Candle")) {


            yee++;
            SendMsg.Msg(String.valueOf(yee));
            // System.out.println(inHand.getDisplayName());



        }


    }
}
