package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.goodstuff.SendMsg;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class confiugtest {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (event.message.getUnformattedText().contains("yea rat")) {


            String heldHand;
            try {
                heldHand = Minecraft.getMinecraft().thePlayer.getHeldItem().getDisplayName();
            } catch (Exception lolimnevergonnausethis) {
                return;
            }

            if (heldHand.equals("poopy poo")) {
                System.out.println("we");
                if (!OwnwnAddons.config.CONFIG_TEST_SWITCH) {
                    return;
                }
                SendMsg.Msg("cool kid");
                // System.out.println(inHand.getDisplayName());


            }
        }
    }
}
