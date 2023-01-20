package com.ownwn.ownwnaddons.features;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.inventory.Slot;

public class CreateGhostPick {
    public static long lastInput = 0;
    public static Runnable runnable = () -> {

        if (System.currentTimeMillis() - lastInput < 500) {
            return;
        }
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }

        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        try {
            String itemName = player.getHeldItem().getItem().getRegistryName().toLowerCase();
            if (itemName.contains("pickaxe") || itemName.contains("sword") || itemName.contains("star")) {
                return;
            }

        } catch (NullPointerException ignored) {}

        for (int i = 0; i < 9; i++) {
            if (player.inventoryContainer.getSlotFromInventory(player.inventory, i).getStack() == null) {
                continue;
            }
            Slot slot = player.inventoryContainer.getSlotFromInventory(player.inventory, i);

            if (slot.getStack().getItem().getRegistryName().toLowerCase().contains("pickaxe")) {

                player.inventoryContainer.getSlotFromInventory(player.inventory, player.inventory.currentItem).putStack(slot.getStack());
                return;
            }
        }

    };
}
