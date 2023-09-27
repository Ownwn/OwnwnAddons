    package com.ownwn.ownwnaddons.mixin;

    import com.ownwn.ownwnaddons.features.CustomNames;
    import com.ownwn.ownwnaddons.utils.ColourUtils;
    import com.ownwn.ownwnaddons.utils.NewConfig;
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
        if (text.isEmpty() || text.equals("§r")) return text;

        text = CustomNames.replaceLevelNumber(text);
        text = CustomNames.replacePlayerNameAndRank(text);
        text = CustomNames.replaceOtherNames(text);
        text = CustomNames.replaceChromaMessages(text);
        text = text.replace("§x", "§" + ColourUtils.scuffedChroma());


        if (!NewConfig.CUSTOM_SIDEBAR_URL.isEmpty()) {
            // kinda dirty text replacement, would be better practice to edit the scoreboard itself. oh well
            text = text.replace("§ewww.hypixel.ne\uD83C\uDF82§et", NewConfig.CUSTOM_SIDEBAR_URL.replace("&&", "§"));
            // yes, the hypixel url has a birthday cake unicode character. who knows???
        }
        return text;
    }
}