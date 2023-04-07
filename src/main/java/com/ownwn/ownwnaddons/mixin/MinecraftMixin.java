package com.ownwn.ownwnaddons.mixin;

import com.ownwn.ownwnaddons.utils.ColourUtils;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FontRenderer.class)
public class MinecraftMixin {
    @ModifyVariable(method = "drawString(Ljava/lang/String;FFIZ)I", at = @At(value = "HEAD"), argsOnly = true) // https://github.com/Cephetir/SkySkipped/blob/kotlin/src/main/java/me/cephetir/skyskipped/mixins/render/MixinFontRenderer.java
    public String drawText(String text) {

        text = ColourUtils.replacePlayerName(text);
        text = ColourUtils.replaceChromaMessages(text);
        text = text.replace("§x", "§" + ColourUtils.getColour());


        return text;
    }

}