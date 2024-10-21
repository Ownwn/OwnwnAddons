package com.ownwn.ownwnaddons.mixin;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import com.ownwn.ownwnaddons.Config;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.util.CheckSlot;
import com.ownwn.ownwnaddons.util.Game;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(GuiContainer.class)
public class HandleMouseClickMixin {

    @Inject(method = "handleMouseClick", at = @At(value = "HEAD"), cancellable = true)
    public void handleMouseClick(Slot slotIn, int slotId, int mouseButtonClicked, int clickType, CallbackInfo ci) {

        if (Game.INSTANCE.getPlayer() == null) {
            return;
        }
        if (slotIn == null || slotIn.getStack() == null) {
            return;
        }


        if (Config.INSTANCE.getStopNecromancerDelete()) {
            if (!CheckSlot.INSTANCE.getOpenGuiName().equals("Soul Menu")) {
                return;
            }

            if (CheckSlot.INSTANCE.checkSlotNameAndLore(slotIn, "", new String[]{"Base Mana Cost"})) { // leave name blank because the mob souls have different item names
                ci.cancel();
                UChat.chat(OwnwnAddons.PREFIX + "&aStopped you from deleting that soul!" +
                        "\n &aDisable this in &b/owa");
            }
        }
    }
}
