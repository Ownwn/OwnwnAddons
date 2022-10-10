package com.ownwn.ownwnaddons.events;

import com.ownwn.ownwnaddons.OwnwnAddons;
import gg.essential.universal.UChat;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Objects;


public class RepellingCandle {
    Integer yee = 1;

    @SubscribeEvent
    public void sadsada(TickEvent event) {

        if (!OwnwnAddons.config.REPELLING_CANDLE_SWITCH) {
            return;
        }

        if (Minecraft.getMinecraft().currentScreen != null ||
                Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }
        // code taken from NEU https://github.com/NotEnoughUpdates/NotEnoughUpdates

        ItemStack stack = Minecraft.getMinecraft().thePlayer.getHeldItem();

        if (stack != null && stack.hasTagCompound()) {

            NBTTagCompound tag = stack.getTagCompound();

            if (tag.hasKey("ExtraAttributes", 10)) {
                NBTTagCompound ea = tag.getCompoundTag("ExtraAttributes");
                if (ea.getTag("id").toString().equals("REPELLING_CANDLE")) {
                    return;
                }

                yee++;
                UChat.chat(OwnwnAddons.PREFIX + yee);
                // System.out.println(inHand.getDisplayName());


            }

        }
    }
}
