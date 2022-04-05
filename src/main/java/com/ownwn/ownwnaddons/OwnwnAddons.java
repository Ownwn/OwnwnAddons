package com.ownwn.ownwnaddons;

import com.google.common.collect.Sets;
import com.ownwn.ownwnaddons.commands.lowestBin;
import com.ownwn.ownwnaddons.commands.owa;
import com.ownwn.ownwnaddons.events.changeMimicMsg;
import com.ownwn.ownwnaddons.events.quizSolver;
import com.ownwn.ownwnaddons.events.starCult;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.Set;

@Mod(modid = OwnwnAddons.MODID, version = OwnwnAddons.VERSION)
public class OwnwnAddons
{
    public static final String MODID = "ownwnaddons";
    public static final String VERSION = "1.0";
    public static final String PREFIX =
            EnumChatFormatting.DARK_PURPLE + "<" + EnumChatFormatting.LIGHT_PURPLE + "OWA"
                    + EnumChatFormatting.DARK_PURPLE + ">" + EnumChatFormatting.RESET + " ";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new quizSolver());
        MinecraftForge.EVENT_BUS.register(new changeMimicMsg());
        ClientCommandHandler.instance.registerCommand(new owa());
        ClientCommandHandler.instance.registerCommand(new lowestBin());
        MinecraftForge.EVENT_BUS.register(new starCult());
    }
}

