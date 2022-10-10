package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.OwnwnAddons;
import gg.essential.universal.UChat;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


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
            UChat.chat(OwnwnAddons.PREFIX + yee);
            // System.out.println(inHand.getDisplayName());



        }


    }
}
