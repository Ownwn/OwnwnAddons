package com.ownwn.ownwnaddons;

import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import com.ownwn.ownwnaddons.commands.FragRunCalc;
import com.ownwn.ownwnaddons.commands.HyperionPrice;
import com.ownwn.ownwnaddons.commands.Owa;
import com.ownwn.ownwnaddons.features.*;
import com.ownwn.ownwnaddons.features.chat.*;
import com.ownwn.ownwnaddons.features.dungeons.*;
import com.ownwn.ownwnaddons.utils.*;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

@net.minecraftforge.fml.common.Mod(modid = OwnwnAddons.MODID, name = OwnwnAddons.NAME, version = OwnwnAddons.VERSION)
public class OwnwnAddons
{
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    public static final String PREFIX =
            EnumChatFormatting.DARK_PURPLE + "<" + EnumChatFormatting.LIGHT_PURPLE + "OWA"
                    + EnumChatFormatting.DARK_PURPLE + ">" + EnumChatFormatting.RESET + " ";

    public static final String HELP = "&9&l\u279C OwnwnAddons Help\n"

            + "&9/owa \u27A1 &bOpens the GUI\n"

            + "&9/owa lbin <item> \u27A1 &bFind the lowest bin for any item (uses moulberry.codes)\n"

            + "&9/owa preview <message> \u27A1 &bDisplays any message in chat, supports formatting codes"

            + "&9/owa fetchnames \u27A1 &bRefreshes custom names without rejoining the server"

            + "&9/owa friends \u27A1 &bDisplays total number of friend requests";

    @net.minecraftforge.fml.common.Mod.Instance(MODID)
    public static OwnwnAddons INSTANCE;
    public NewConfig config;

    @net.minecraftforge.fml.common.Mod.EventHandler
    public void onFMLInitialization(net.minecraftforge.fml.common.event.FMLInitializationEvent event) {
        config = new NewConfig();

        registerForgeEventBus(this);
        registerForgeEventBus(new TrevorChatCleanup());
        registerForgeEventBus(new BazaarChatCleanup());
        registerForgeEventBus(new TrevorCooldown());
        registerForgeEventBus(new BankChatCleanup());
        registerForgeEventBus(new SBAChromaReplacement());
        registerForgeEventBus(new DungeonsChatCleanup());
        registerForgeEventBus(new WitherShieldSound());
        registerForgeEventBus(new FetchOnServerJoin());
        registerForgeEventBus(new DungeonsTerminalDisplay());
        registerForgeEventBus(new MinigameClickFriend());

        registerForgeEventBus(new ThirdPersonFOV());
        registerForgeEventBus(new SecretStuff());
        registerForgeEventBus(new GardenMilestoneMsg());
        registerForgeEventBus(new TickUtils());
        registerForgeEventBus(new CheckSlot());
        registerForgeEventBus(new SinSeekerCooldown());
        registerForgeEventBus(new PartyFinderHighlight());

        registerForgeEventBus(new CustomNames());
        registerForgeEventBus(new OnScreenTimer());
        registerForgeEventBus(new HarpFailWarning());
        registerForgeEventBus(new CellsAlignedDisplay());
        registerForgeEventBus(new SecretClickSounds());
        
        


        registerCommand(new Owa());
        registerCommand(new HyperionPrice());
        registerCommand(new FragRunCalc());
        
        
        registerOneconfigEventBus(new MinigameClickFriend());

        postEvent(new GameLaunchEvent());

    }
    
    public void registerForgeEventBus(Object object) {
        MinecraftForge.EVENT_BUS.register(object);
    }

    public void registerOneconfigEventBus(Object object) {
        EventManager.INSTANCE.register(object);
    }

    public void registerCommand(Object object) {
        CommandManager.INSTANCE.registerCommand(object);
    }

    public void postEvent(Event event) {
        MinecraftForge.EVENT_BUS.post(event);
    }
}

