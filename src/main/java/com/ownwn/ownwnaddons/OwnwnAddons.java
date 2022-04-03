package com.ownwn.ownwnaddons;

import net.minecraft.util.EnumChatFormatting;
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
    public static final String PREFIX =
            EnumChatFormatting.BLACK + "<" + EnumChatFormatting.RED + "OWA"
                    + EnumChatFormatting.BLACK + ">" + EnumChatFormatting.RESET + " ";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new quizSolver());
        MinecraftForge.EVENT_BUS.register(new changeMimicMsg());
        ClientCommandHandler.instance.registerCommand(new owa());
        ClientCommandHandler.instance.registerCommand(new iUseSbe());
        ClientCommandHandler.instance.registerCommand(new apiCommandThingy());
    }
}

