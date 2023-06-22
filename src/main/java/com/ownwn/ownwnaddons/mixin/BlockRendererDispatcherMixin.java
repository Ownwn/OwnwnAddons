    package com.ownwn.ownwnaddons.mixin;

    import com.ownwn.ownwnaddons.features.HubReskin;
    import com.ownwn.ownwnaddons.utils.NewConfig;
    import net.minecraft.block.state.IBlockState;
    import net.minecraft.client.Minecraft;
    import net.minecraft.client.renderer.BlockRendererDispatcher;
    import net.minecraft.client.renderer.WorldRenderer;
    import net.minecraft.util.BlockPos;
    import net.minecraft.world.IBlockAccess;
    import org.spongepowered.asm.mixin.Mixin;
    import org.spongepowered.asm.mixin.injection.At;
    import org.spongepowered.asm.mixin.injection.Inject;
    import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockRendererDispatcher.class)
public class BlockRendererDispatcherMixin {
    // this seems a bit buggy, it often doesn't work until you refresh your chunks (F3+A)
    @Inject(method = "renderBlock", at = @At(value = "HEAD"), cancellable = true)
    public void renderBlock(IBlockState state, BlockPos pos, IBlockAccess blockAccess, WorldRenderer worldRendererIn, CallbackInfoReturnable<Boolean> cir) {

        if (!NewConfig.WORLD_RESKIN_HUB) {
            return;
        }
        if (state.getBlock() == null) {
            return;
        }
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }

        // If true, cancel the block render and render a new one in the below method
        if (HubReskin.reskinHub(state, pos, blockAccess, worldRendererIn)) {
            cir.cancel();
        }
    }
}