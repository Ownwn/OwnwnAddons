package com.ownwn.ownwnaddons.mixin;

import com.ownwn.ownwnaddons.features.OwaDevMode;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixin {

    @Inject(method = "addToSendQueue", at = @At(value = "HEAD"))
    public void stuff(Packet p_147297_1_, CallbackInfo ci) {
        OwaDevMode.logLocraw(p_147297_1_);



    }
}
