package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.commands.Owa;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class OwaDevMode {
    public static void logLocraw(Packet packet) {
        if (!Owa.devMode) return;
        if (!(packet instanceof C01PacketChatMessage)) return;
//
        C01PacketChatMessage messagePacket = (C01PacketChatMessage) packet;

        System.out.println("Message sent: " + messagePacket.getMessage());
    }
}
