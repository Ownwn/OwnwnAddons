package com.ownwn.ownwnaddons;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class quizSolver {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (event.message.getUnformattedText().contains("<Ownwn> hi")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A74\u00A7lYou Are Pog"));
        }
//        if (event.message.getUnformattedText().contains("Unscramble the word")) {
//            Minecraft.getMinecraft().thePlayer.sendChatMessage("Minecraft");
//        }
//        if (event.message.getUnformattedText().contains("Type the word")) {
//            Minecraft.getMinecraft().thePlayer.sendChatMessage("Minecraft");
//        }
    }
}
