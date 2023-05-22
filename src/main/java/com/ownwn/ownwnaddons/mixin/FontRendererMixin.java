    package com.ownwn.ownwnaddons.mixin;

import com.ownwn.ownwnaddons.features.CustomNames;
import com.ownwn.ownwnaddons.utils.ColourUtils;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FontRenderer.class)
public class FontRendererMixin {

    @ModifyVariable(method = "drawString(Ljava/lang/String;FFIZ)I", at = @At(value = "HEAD"), argsOnly = true) // inspiration from https://github.com/Cephetir/SkySkipped/blob/kotlin/src/main/java/me/cephetir/skyskipped/mixins/render/MixinFontRenderer.java
    public String drawText(String text) {

        return compressReplacements(text);
    }


    @ModifyVariable(method = "getStringWidth", at = @At(value = "HEAD"), argsOnly = true)
    public String getStringWidth(String text) {

        return compressReplacements(text);
        // stop text overflowing/underflowing on tooltips
    }


    private String compressReplacements(String text) {
        text = CustomNames.replacePlayerNameAndRank(text);
        text = CustomNames.replaceOtherNames(text);
        text = CustomNames.replaceChromaMessages(text);
        text = text.replace("§x", "§" + ColourUtils.scuffedChroma());

        return text;
    }
}