package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import com.ownwn.ownwnaddons.commands.Owa;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OwaDevMode {

    @SubscribeEvent
    public void onPacketReceive(ItemTooltipEvent event) {
        if (!Owa.devMode) {
            return;
        }
//        if (!(event.packet instanceof S2FPacketSetSlot)) {
//            return;
//        }
//        S2FPacketSetSlot packet = (S2FPacketSetSlot) event.packet;

//        UChat.chat(packet.func_149174_e());
//        UChat.chat(packet.func_149173_d());
//        UChat.chat(packet.func_149175_c());
        UChat.chat((" "));
        for (String text: event.toolTip) {
            UChat.chat(text);
        }
        UChat.chat(" ");

    }

}
