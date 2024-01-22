package com.ownwn.ownwnaddons.mixin;


import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public class RenderGlobalMixin {


    @Inject(method = "setDisplayListEntitiesDirty", at = @At(value = "HEAD"))
    public void tick(CallbackInfo ci) {
        if (!NewConfig.DUNGEONS_THIRD_PERSON) return;
        if (!OwnwnAddons.utils.checkLocGameMode("dungeon")) return;

        Minecraft mc = Minecraft.getMinecraft();

        if (mc.gameSettings.thirdPersonView == 2) {
            mc.gameSettings.thirdPersonView = 0;

            mc.entityRenderer.loadEntityShader(mc.getRenderViewEntity());
            // ^ call this as part of changing the player's perspective like normal
        }
    }
}
