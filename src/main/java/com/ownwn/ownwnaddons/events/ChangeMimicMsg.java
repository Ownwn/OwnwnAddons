package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.goodstuff.SendMsg;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChangeMimicMsg {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (event.message.getUnformattedText().contains("$SKYTILS-DUNGEON-SCORE-MIMIC$")) {
            SendMsg.Msg("\u00A7eSkytils > \u00A7bMimic Killed!");
            event.setCanceled(true);
        }

    }
}