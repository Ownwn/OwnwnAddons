package com.ownwn.ownwnaddons.mixin;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.CheckSlot;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(GuiContainer.class)
public class HandleMouseClickMixin {

    private static final String[] DUNGEON_ITEMS = {"Kismet", "Spirit Leap", "Superboom TNT"};
    // this should probably be user-adjustable, but it works for now

    @Inject(method = "handleMouseClick", at = @At(value = "HEAD"), cancellable = true)
    public void handleMouseClick(Slot slotIn, int slotId, int mouseButtonClicked, int clickType, CallbackInfo ci) {

        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        if (slotIn == null || slotIn.getStack() == null) {
            return;
        }


        if (NewConfig.STOP_INSTA_SELL) {
            if (CheckSlot.checkSlotNameAndLore(slotIn, "Sell Inventory Now", DUNGEON_ITEMS)) {
                ci.cancel();
                UChat.chat(OwnwnAddons.PREFIX + "&aStopped you from insta-selling your dungeon items!" +
                        "\n &aDisable this in &b/owa");
                return;
            }
        }

        if (NewConfig.STOP_NECROMANCER_REMOVE) {
            if (!CheckSlot.openGuiName.equals("Soul Menu")) {
                return;
            }

            if (CheckSlot.checkSlotNameAndLore(slotIn, "", new String[]{"Base Mana Cost"})) { // leave name blank because the mob souls have different item names
                ci.cancel();
                UChat.chat(OwnwnAddons.PREFIX + "&aStopped you from deleting that soul!" +
                        "\n &aDisable this in &b/owa");
            }
        }
    }
}
