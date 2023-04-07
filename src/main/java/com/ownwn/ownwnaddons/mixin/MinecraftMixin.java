package com.ownwn.ownwnaddons.mixin;

import com.ownwn.ownwnaddons.commands.Owa;
import com.ownwn.ownwnaddons.utils.NewConfig;
import com.ownwn.ownwnaddons.utils.ColourUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FontRenderer.class)
public class MinecraftMixin {
    @ModifyVariable(method = "drawString(Ljava/lang/String;FFIZ)I", at = @At(value = "HEAD"), argsOnly = true) // https://github.com/Cephetir/SkySkipped/blob/kotlin/src/main/java/me/cephetir/skyskipped/mixins/render/MixinFontRenderer.java
    public String drawText(String text) {
        if (Minecraft.getMinecraft().thePlayer == null) {
            return text;
        }

        String playerName = Minecraft.getMinecraft().thePlayer.getName();

        if (!text.contains(playerName)) {
            return text;
        }
        if (NewConfig.CUSTOM_NAME_MODE == 0) {
            return text;
        }

        if (Owa.devMode) {
            return ColourUtils.InsaneColour(text);
        }

        StringBuilder newName = new StringBuilder();
        newName.append("§");

        if (NewConfig.CUSTOM_NAME_MODE == 1) {
            newName.append("z");

        } else {
            newName.append(ColourUtils.getColour());
        }
        newName.append(playerName).append("§r");

        return text.replace(playerName, newName);
    }

}