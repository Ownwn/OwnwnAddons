package com.ownwn.ownwnaddons.mixin;


import com.ownwn.ownwnaddons.utils.NewConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "io.github.moulberry.notenoughupdates.ItemPriceInformation")
public class MixinItemPriceInformation {

    // https://github.com/NotEnoughUpdates/NotEnoughUpdates/blob/master/src/main/java/io/github/moulberry/notenoughupdates/ItemPriceInformation.java
    @Inject(method = "formatPrice", at = @At(value = "RETURN"), cancellable = true)
    private static void modifyFormatPriceReturnValue(String label, double price, CallbackInfoReturnable<String> cir) {
        if (!NewConfig.CHANGE_NEU_TOOLTIP) {
            return;
        }
        String newString = cir.getReturnValue();

        newString = newString.replace("§e§l", "§z§l");
        newString = newString.replace(" coins", "");
        newString = newString.replace("Lowest BIN", "Price");


        cir.setReturnValue(newString);
    }
}
