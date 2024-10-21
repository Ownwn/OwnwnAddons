package com.ownwn.ownwnaddons.util

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.inventory.ContainerChest
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


object CheckSlot {
    var openGuiName: String = "" // the name of the currently open chest gui

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun onOpenGui(event: GuiOpenEvent) { // https://github.com/Moulberry/NotEnoughUpdates/blob/master/src/main/java/io/github/moulberry/notenoughupdates/util/SBInfo.java
        if (event.gui !is GuiChest) {
            openGuiName = ""
            return
        }

        val chest = event.gui as GuiChest
        val chestContainer = chest.inventorySlots as ContainerChest

        openGuiName = Utils.stripFormatting(chestContainer.lowerChestInventory.displayName.unformattedText)
    }

    fun checkSlotNameAndLore(slot: Slot, name: String, lore: Array<String>): Boolean {
        // hasDisplayName() returns true if the item has an italic name (has been renamed in an anvil)
        // or has been renamed with a plugin/command (like in sb), but getDisplayName() correctly grabs the sb name.


        val slotStack: ItemStack = slot.stack

        if (!Utils.stripFormatting(slotStack.displayName).contains(name)) {
            return false
        }

        val loreList = slotStack.getTooltip(Game.player!!, false)

        for (itemLore in loreList) { // loop through the lines of lore on the item

            for (loreMatch in lore) { // loop through the String[] array of item names
                if (Utils.stripFormatting(itemLore).contains(loreMatch)) {
                    return true
                }
            }
        }

        return false
    }
}