package com.ownwn.ownwnaddons;

import com.ownwn.ownwnaddons.commands.FragRunCalc;
import com.ownwn.ownwnaddons.commands.HyperionPrice;
import com.ownwn.ownwnaddons.commands.Owa;
import com.ownwn.ownwnaddons.features.*;
import com.ownwn.ownwnaddons.utils.ChatListener;
import com.ownwn.ownwnaddons.utils.NewConfig;
import com.ownwn.ownwnaddons.utils.OverlayRenderer;
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
    public static final String VERSION = "1.2.3";
    public static final String PREFIX =
            EnumChatFormatting.DARK_PURPLE + "<" + EnumChatFormatting.LIGHT_PURPLE + "OWA"
                    + EnumChatFormatting.DARK_PURPLE + ">" + EnumChatFormatting.RESET + " ";

    public static NewConfig config = new NewConfig();

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Vigilance.initialize();
        config.preload();


        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new PartyFinder());
        MinecraftForge.EVENT_BUS.register(new CustomChat());
        MinecraftForge.EVENT_BUS.register(new TrevorChatCleanup());
        MinecraftForge.EVENT_BUS.register(new BazaarChatCleanup());
        MinecraftForge.EVENT_BUS.register(new TrevorCooldown());
        MinecraftForge.EVENT_BUS.register(new FragRunTimer());
        MinecraftForge.EVENT_BUS.register(new OverlayRenderer());
        MinecraftForge.EVENT_BUS.register(new ChatListener());
        ClientCommandHandler.instance.registerCommand(new Owa());
        ClientCommandHandler.instance.registerCommand(new HyperionPrice());
        ClientCommandHandler.instance.registerCommand(new FragRunCalc());

    }
}

