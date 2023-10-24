    package com.ownwn.ownwnaddons.mixin;

    import com.ownwn.ownwnaddons.features.CustomNames;
    import com.ownwn.ownwnaddons.utils.ColourUtils;
    import com.ownwn.ownwnaddons.utils.NewConfig;
    import com.ownwn.ownwnaddons.utils.Utils;
    import net.minecraft.client.Minecraft;
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


    private static String compressReplacements(String text) {

        if (text.isEmpty() || text.equals("§r")) return text;
        if (Minecraft.getMinecraft().thePlayer == null) return text;

        String unformatted = Utils.stripFormatting(text);
        if (unformatted.isEmpty()) return text;

        text = CustomNames.replaceLevelNumber(text, unformatted);
        text = CustomNames.replacePlayerNameAndRank(text, unformatted);
        text = CustomNames.replaceOtherNames(text, unformatted);
        text = CustomNames.replaceChromaMessages(text);

        if (NewConfig.CHROMA_TYPE) text = text.replace("§x", "§" + ColourUtils.scuffedChroma());


        if (!NewConfig.CUSTOM_SIDEBAR_URL.isEmpty()) {
            if (!text.startsWith("§ewww.hypixel")) return text;

            // kinda dirty text replacement, would be better practice to edit the scoreboard itself. oh well
            text = text.replace("§ewww.hypixel.ne\uD83C\uDF82§et", NewConfig.CUSTOM_SIDEBAR_URL.replace("&&", "§"));
            // yes, the hypixel url has a birthday cake unicode character. who knows???
        }

        return text;
    }
}