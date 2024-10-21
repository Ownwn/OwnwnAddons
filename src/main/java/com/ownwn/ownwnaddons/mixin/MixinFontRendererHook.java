package com.ownwn.ownwnaddons.mixin;


import com.ownwn.ownwnaddons.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "codes.biscuit.skyblockaddons.asm.hooks.FontRendererHook")
public class MixinFontRendererHook {

    @Shadow private static boolean modInitialized;
    @Inject(method = "shouldRenderChroma", at = @At(value = "HEAD"), cancellable = true)
    private static void modifyFormatPriceReturnValue(CallbackInfoReturnable<Boolean> cir) {

        // force SBA to always render chroma text, even outside skyblock
        // https://github.com/BiscuitDevelopment/SkyblockAddons/blob/main/src/main/java/codes/biscuit/skyblockaddons/asm/hooks/FontRendererHook.java
        if (Config.INSTANCE.getForceSbaChroma()) {
            cir.setReturnValue(modInitialized);
        }

    }
}
