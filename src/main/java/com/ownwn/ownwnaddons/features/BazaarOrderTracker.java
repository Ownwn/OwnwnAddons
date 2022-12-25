package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BazaarOrderTracker {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChat(ClientChatReceivedEvent event) {

        if (!NewConfig.BAZAAR_ORDER_TRACKER) {
            return;
        }


        String msg = event.message.getFormattedText();
        String newMsg;


        if (!msg.contains("[Bazaar]")) {
            return;
        }
    }
}
