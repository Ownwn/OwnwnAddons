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
public class OwnwnAddons // todo move kotlin files to kotlin namespace
{
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    public static final String PREFIX =
            EnumChatFormatting.DARK_PURPLE + "<" + EnumChatFormatting.LIGHT_PURPLE + "OWA"
                    + EnumChatFormatting.DARK_PURPLE + ">" + EnumChatFormatting.RESET + " ";

    public static final String HELP = "&9&l➜ OwnwnAddons Help\n"
            + "&9/owa ➡ &bOpens the GUI\n"
            + "&9/owa lbin <item> ➡ &bFind the lowest bin for any item (uses moulberry.codes)\n"
            + "&9/owa preview <message> ➡ &bDisplays any message in chat, supports formatting codes"
            + "&9/owa fetchnames ➡ &bRefreshes custom names without rejoining the server"
            + "&9/owa friends ➡ &bDisplays total number of friend requests";

    @net.minecraftforge.fml.common.Mod.Instance(MODID)
    public static OwnwnAddons INSTANCE;
    public NewConfig config;
    public static final Util utils = Util.INSTANCE;
    public static final CustomName customName = CustomName.INSTANCE; // call instances once to prevent duplicate objects

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
        registerForgeEventBus(new UpdateChecker());
        registerForgeEventBus(new DungeonsTerminalDisplay());
        registerForgeEventBus(new MinigameClickFriend());

        registerForgeEventBus(new ThirdPersonFOV());
        registerForgeEventBus(new SecretStuff());
        registerForgeEventBus(new GardenMilestoneMsg());
        registerForgeEventBus(new TickUtils());
        registerForgeEventBus(new CheckSlot());
        registerForgeEventBus(new SinSeekerCooldown());
        registerForgeEventBus(new PartyFinderHighlight());

        registerForgeEventBus(CustomName.INSTANCE);
        registerForgeEventBus(new OnScreenTimer());
        registerForgeEventBus(new HarpFailWarning());
        registerForgeEventBus(new CellsAlignedDisplay());
        registerForgeEventBus(new SecretClickSounds());
        registerForgeEventBus(new TerminalOverview());
        registerForgeEventBus(new OnJoinServer());

        


        registerCommand(new Owa());
        registerCommand(new HyperionPrice());
        registerCommand(new FragRunCalc());
        
        
        registerOneconfigEventBus(new MinigameClickFriend());

        postEvent(new ServerJoinEvent());

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

    public static void postEvent(Event event) {
        MinecraftForge.EVENT_BUS.post(event);
    }

}

