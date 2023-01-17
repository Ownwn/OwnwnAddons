package com.ownwn.ownwnaddons.utils;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.annotations.Number;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.features.TrevorCooldown;
import com.ownwn.ownwnaddons.features.chat.TrevorLootTracker;

public class NewConfig extends Config {

    @Number(
            name = "Maximum average ping (ms)",
            description = "Can be obtained with /skytils ping. Set this to the highest that your ping will reasonably reach. Used for features such as the wither blade sounds.",
            min = 10, max = 1000,
            step = 10
    )
    public static int MAX_PING_NUM = 100;

    @Text(
            name = "Hypixel API key",
            description = "Your hypixel api key, used for getting your rank.",
            secure = true
    )
    public static String HYPIXEL_API_KEY = "";

    @Dropdown(
            name = "Hypixel Rank",
            options = {"Set using /owa getrank", "Default", "VIP", "VIP+", "MVP", "MVP+", "MVP++"},
            description = "Your hypixel rank. Automatically set by running /owa getrank"
    )
    public static int PLAYER_HYPIXEL_RANK = 0;

    @Switch(
            name = "Check for updates",
            description = "Should the mod check for updates when you join Hypixel?"
    )
    public static boolean CHECK_FOR_UPDATES = true;

    @Text(
            name = "Dungeons Secret Click Sound",
            description = "Customize the sound that plays when you click a secret in dungeons e.g. random.successful_hit. Leave blank for no sound, credit to AtonAddons for the idea",
            category = "Features"
    )
    public static String SECRET_CLICK_SOUND = "random.break";

    @Slider(
            name = "Click Sound Volume",
            description = "Change the volume of the dungeons secret click sound",
            category = "Features",
            min = 1f, max = 10.01f,
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
            name = "Debug Mode",
            description = "Should the mod write things like HTTP responses to the log?"
    )
    public static boolean VERBOSE_CODE_SWITCH = false;

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

    @DualOption(
            name = "Custom name mode",
            description = "Aggressive mode will replace any text with your name, but will break often.",
            category = "Chat Replacers",
            subcategory = "Custom Chat",
            left = "Standard",
            right = "Aggressive"
    )
    public static boolean CUSTOM_NAME_MODE = false;


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

    @Text(
            name = "Wither Shield Sound",
            description = "Plays a sound when wither shield activates. It's recommended to set \"Mob Zombie Remedy\" in Patcher sounds to 0.",
            category = "Sound",
            subcategory = "Wither Blades"
    )
    public static String WITHER_SHIELD_SOUND = "";

    @Slider(
            name = "Wither Shield Volume",
            description = "Change the volume of the wither shield sound",
            category = "Sound",
            subcategory = "Wither Blades",
            min = 1f, max = 10f,
            step = 1
    )
    public static float WITHER_SHIELD_VOLUME = 5f;

    @Slider(
            name = "Wither Shield Pitch",
            description = "Change the pitch of the wither shield sound",
            category = "Sound",
            subcategory = "Wither Blades",
            min = 5f, max = 20f,
            step = 1
    )
    public static float WITHER_SHIELD_PITCH = 10f;

    @Text(
            name = "Implosion sound",
            description = "Plays a sound when you implode. It's recommended to set \"Random Explode\" in Patcher sounds to 0.",
            category = "Sound",
            subcategory = "Wither Blades"
    )
    public static String WITHER_IMPLODE_SOUND = "";

    @Slider(
            name = "Implosion Volume",
            description = "Change the volume of the implosion sound",
            category = "Sound",
            subcategory = "Wither Blades",
            min = 1f, max = 10f,
            step = 1
    )
    public static float WITHER_IMPLODE_VOLUME = 5f;

    @Slider(
            name = "Implosion Pitch",
            description = "Change the pitch of the implosion sound",
            category = "Sound",
            subcategory = "Wither Blades",
            min = 5f, max = 20f,
            step = 1
    )
    public static float WITHER_IMPLODE_PITCH = 10f;


    public NewConfig() {
        super(new Mod(OwnwnAddons.NAME, ModType.UTIL_QOL), OwnwnAddons.MODID + ".json");
        initialize();
    }
}

