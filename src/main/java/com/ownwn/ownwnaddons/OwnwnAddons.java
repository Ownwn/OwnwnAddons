package com.ownwn.ownwnaddons;

import com.ownwn.ownwnaddons.commands.CmdTest;
import com.ownwn.ownwnaddons.commands.HyperionPrice;
import com.ownwn.ownwnaddons.commands.Owa;
import com.ownwn.ownwnaddons.events.*;

import com.ownwn.ownwnaddons.events.dojo.StaminaTimer;
import com.ownwn.ownwnaddons.outside.NewConfig;
import gg.essential.vigilance.Vigilance;
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
    public static final String VERSION = "1.1";
    public static final String PREFIX =
            EnumChatFormatting.DARK_PURPLE + "<" + EnumChatFormatting.LIGHT_PURPLE + "OWA"
                    + EnumChatFormatting.DARK_PURPLE + ">" + EnumChatFormatting.RESET + " ";

    public static final OwnwnAddons instance = new OwnwnAddons();
    public static NewConfig config = new NewConfig();

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Vigilance.initialize();
        config.preload();


        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new PartyFinder());
        MinecraftForge.EVENT_BUS.register(new StaminaTimer());
        MinecraftForge.EVENT_BUS.register(new ParkourUtils());
        MinecraftForge.EVENT_BUS.register(new RepellingCandle());
        MinecraftForge.EVENT_BUS.register(new confiugtest());
        ClientCommandHandler.instance.registerCommand(new Owa());
        ClientCommandHandler.instance.registerCommand(new HyperionPrice());
        ClientCommandHandler.instance.registerCommand(new CmdTest());
    }
}

