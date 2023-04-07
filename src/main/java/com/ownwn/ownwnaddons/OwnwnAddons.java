package com.ownwn.ownwnaddons;

import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import com.ownwn.ownwnaddons.commands.*;
import com.ownwn.ownwnaddons.features.*;
import com.ownwn.ownwnaddons.features.chat.*;
import com.ownwn.ownwnaddons.features.dungeons.DungeonsMimicAlert;
import com.ownwn.ownwnaddons.features.dungeons.DungeonsTerminalDisplay;
import com.ownwn.ownwnaddons.features.dungeons.SecretClickSounds;
import com.ownwn.ownwnaddons.utils.FetchOnServerJoin;
import com.ownwn.ownwnaddons.utils.NewConfig;
import com.ownwn.ownwnaddons.utils.TickUtils;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;

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

            + "&9/owa preview <message> \u27A1 &bDisplays any message in chat, supports formatting codes";

    @net.minecraftforge.fml.common.Mod.Instance(MODID)
    public static OwnwnAddons INSTANCE;
    public NewConfig config;

    @net.minecraftforge.fml.common.Mod.EventHandler
    public void onFMLInitialization(net.minecraftforge.fml.common.event.FMLInitializationEvent event) {
        config = new NewConfig();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new CustomName());
        MinecraftForge.EVENT_BUS.register(new TrevorChatCleanup());
        MinecraftForge.EVENT_BUS.register(new BazaarChatCleanup());
        MinecraftForge.EVENT_BUS.register(new TrevorCooldown());
        MinecraftForge.EVENT_BUS.register(new BankChatCleanup());
        MinecraftForge.EVENT_BUS.register(new SBAChromaReplacement());
        MinecraftForge.EVENT_BUS.register(new SylveoonDiscordChat());
        MinecraftForge.EVENT_BUS.register(new DungeonsChatCleanup());
        MinecraftForge.EVENT_BUS.register(new SecretClickSounds());
        MinecraftForge.EVENT_BUS.register(new WitherShieldSound());
        MinecraftForge.EVENT_BUS.register(new FetchOnServerJoin());
        MinecraftForge.EVENT_BUS.register(new DungeonsTerminalDisplay());
        MinecraftForge.EVENT_BUS.register(new SkywarsClickFriend());
        MinecraftForge.EVENT_BUS.register(new DungeonsMimicAlert());
        MinecraftForge.EVENT_BUS.register(new OwaDevMode());
        MinecraftForge.EVENT_BUS.register(new ThirdPersonFOV());
        //MinecraftForge.EVENT_BUS.register(new LoreCustomiser());
        MinecraftForge.EVENT_BUS.register(new SecretStuff());
        MinecraftForge.EVENT_BUS.register(new GardenMilestoneMsg());
        MinecraftForge.EVENT_BUS.register(new TickUtils());


        CommandManager.INSTANCE.registerCommand(new Owa());
        CommandManager.INSTANCE.registerCommand(new HyperionPrice());
        CommandManager.INSTANCE.registerCommand(new FragRunCalc());
        CommandManager.INSTANCE.registerCommand(new CamBridge());
        EventManager.INSTANCE.register(new SkywarsClickFriend());



    }
}

