package com.ownwn.ownwnaddons;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = OwnwnAddons.MODID, version = OwnwnAddons.VERSION)
public class OwnwnAddons
{
    public static final String MODID = "ownwnaddons";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new Events());
        MinecraftForge.EVENT_BUS.register(new quizSolver());
        ClientCommandHandler.instance.registerCommand(new owa());
        ClientCommandHandler.instance.registerCommand(new iUseSbe());
    }
}

