package com.ownwn.ownwnaddons.mixin;


import com.ownwn.ownwnaddons.feature.CustomName;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FontRenderer.class)
public class FontRendererMixin {

    @ModifyVariable(method = "drawString(Ljava/lang/String;FFIZ)I", at = @At(value = "HEAD"), argsOnly = true) // inspiration from https://github.com/Cephetir/SkySkipped/blob/kotlin/src/main/java/me/cephetir/skyskipped/mixins/render/MixinFontRenderer.java
    public String drawText(String text) {
        return CustomName.INSTANCE.replaceAllText(text);
    }


    @ModifyVariable(method = "getStringWidth", at = @At(value = "HEAD"), argsOnly = true)
    public String getStringWidth(String text) {
        return CustomName.INSTANCE.replaceAllText(text);
        // stop text overflowing/underflowing on tooltips
    }
}