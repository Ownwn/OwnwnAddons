package com.ownwn.ownwnaddons.utils;

import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CheckSlot {
    public static String openGuiName = ""; // the name of the currently open chest gui

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onOpenGui(GuiOpenEvent event) { //https://github.com/Moulberry/NotEnoughUpdates/blob/master/src/main/java/io/github/moulberry/notenoughupdates/util/SBInfo.java
        if (!(event.gui instanceof GuiChest)) {
            openGuiName = "";
            return;
        }

        GuiChest chest = (GuiChest) event.gui;
        ContainerChest chestContainer = (ContainerChest) chest.inventorySlots;

        openGuiName = OwnwnAddons.utils.stripFormatting(chestContainer.getLowerChestInventory().getDisplayName().getUnformattedText());
    }

    public static boolean checkSlotNameAndLore(Slot slot, String name, String[] lore) {



        // For some reason, hasDisplayName() returns true if the item has an italic name (has been renamed in an anvil)
        // or has been renamed with a plugin/command (like in sb), but getDisplayName() correctly grabs the sb name.

        ItemStack slotStack = slot.getStack();

        if (!OwnwnAddons.utils.stripFormatting(slotStack.getDisplayName()).contains(name)) {
            return false;
        }

        for (String itemLore : slotStack.getTooltip(Minecraft.getMinecraft().thePlayer, false)) { // loop through the lines of lore on the item
            itemLore = OwnwnAddons.utils.stripFormatting(itemLore);

            for (String loreMatch : lore) { // loop through the String[] array of item names
                if (itemLore.contains(loreMatch)) {
                    return true;
                }
            }
        }

        return false;
    }
}
