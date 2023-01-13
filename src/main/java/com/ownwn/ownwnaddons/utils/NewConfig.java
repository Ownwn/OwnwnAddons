package com.ownwn.ownwnaddons.utils;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.HUD;
import cc.polyfrost.oneconfig.config.annotations.Slider;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.annotations.Text;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.features.TrevorCooldown;
import com.ownwn.ownwnaddons.features.chat.TrevorLootTracker;

public class NewConfig extends Config {

//    @Switch(
//            name = "Island AFK Tracker (W.I.P)",
//            description = "Tracks when you leave and enter your private island",
//            category = "Features"
//    )
//    public static boolean ISLAND_AFK_TRACKER = false;

    @Text(
            name = "Dungeons Secret Click Sound",
            description = "Customize the sound that plays when you click a secret in dungeons e.g. random.successful_hit. Leave blank for no sound, credit to AtonAddons for the idea",
            category = "Features"
    )
    public static String SECRET_CLICK_SOUND = "random.successful_hit";

    @Slider(
            name = "Click Sound Volume",
            description = "Change the volume of the dungeons secret click sound",
            category = "Features",
            min = 1f, max = 10f,
            step = 1
    )
    public static float SECRET_CLICK_VOLUME = 5f;

    @Slider(
            name = "Click Sound Pitch",
            description = "Change the pitch of the dungeons secret click sound",
            category = "Features",
            min = 5f, max = 20f,
            step = 1
    )
    public static float SECRET_CLICK_PITCH = 10f;

    @Switch(
            name = "Verbose Code",
            description = "Should the mod write things like HTTP responses to the log?",
            category = "Stuff"
    )
    public static boolean VERBOSE_CODE_SWITCH = false;

    @Text(
            name = "Custom Chat Colour",
            description = "Customize the colour of your chat messages. Leave blank for default colour. Use \"&&\" for colour codes, Credit to NEU for the idea.",
            category = "Chat Replacers",
            subcategory = "Custom Chat"
    )
    public static String CUSTOM_CHAT_COLOUR = "";
    @Text(
            name = "Custom Name",
            description = "Allows you to customize your name. Leave blank for default name. Use \"&&\" for colour codes, Credit to NEU for the idea.",
            category = "Chat Replacers",
            subcategory = "Custom Chat"
    )
    public static String CUSTOM_NAME_EDITOR = "";

    @Switch(
            name = "Replace Rank",
            description = "Should your rank be replaced with the custom name?",
            category = "Chat Replacers",
            subcategory = "Custom Chat"
    )
    public static boolean NAME_REPLACE_RANK = false;

    @Switch(
            name = "Trevor Chat Cleanup",
            description = "Cleans up the chat messages sent by Trevor the Trapper",
            category = "Chat Replacers",
            subcategory = "Chat Cleanup"
    )
    public static boolean TREVOR_CHAT_CLEANUP = false;

    @Switch(
            name = "Bazaar Chat Cleanup",
            description = "Cleans up the chat messages sent by the Bazaar",
            category = "Chat Replacers",
            subcategory = "Chat Cleanup"
    )
    public static boolean BAZAAR_CHAT_CLEANUP = false;

    @Switch(
            name = "Bank Chat Cleanup",
            description = "Cleans up the chat messages sent by the Bank",
            category = "Chat Replacers",
            subcategory = "Chat Cleanup"
    )
    public static boolean BANK_CHAT_CLEANUP = false;

    @Switch(
            name = "Chroma Chat",
            description = "Colours certain chat messages with SBA chroma. This will not work without SkyblockAddons",
            category = "Chat Replacers"
    )
    public static boolean SBA_CHROMA_CHAT = false;

    @Switch(
            name = "Camoflaged Chat Shortener",
            description = "Shortens chat messages sent by Camoflaged's bot Sylveoon",
            category = "Chat Replacers"
    )
    public static boolean GUILD_MSG_PRETTY = false;

    @Switch(
            name = "Dungeons Chat Cleanup",
            description = "Hides some useless/spammy messages in dungeons",
            category = "Chat Replacers",
            subcategory = "Chat Cleanup"
    )
    public static boolean CATA_CHAT_CLEANUP = false;

    @HUD(
            name = "Trevor Cooldown (Requires Trevor Chat Cleanup)",
            category = "Features",
            subcategory = "Trevor"
    )
    public static TrevorCooldown trevorCooldown = new TrevorCooldown();

    @HUD(
            name = "Trevor Loot Tracker (Requires Trevor Chat Cleanup)",
            category = "Features",
            subcategory = "Trevor"
    )
    public static TrevorLootTracker trevorLootTracker = new TrevorLootTracker();


    public NewConfig() {
        super(new Mod(OwnwnAddons.NAME, ModType.UTIL_QOL), OwnwnAddons.MODID + ".json");
        initialize();
    }
}

