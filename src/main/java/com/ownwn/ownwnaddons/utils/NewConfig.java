package com.ownwn.ownwnaddons.utils;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Number;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.data.InfoType;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.features.TrevorCooldown;
import com.ownwn.ownwnaddons.features.TrevorLootTracker;
import com.ownwn.ownwnaddons.features.dungeons.DungeonsTerminalDisplay;

public class NewConfig extends Config {

    @Number(
            name = "Maximum average ping (ms)",
            description = "Can be obtained with /skytils ping. Set this to the highest that your ping will reasonably reach. Used for features such as the wither blade sounds.",
            min = 10, max = 1000,
            step = 10
    )
    public static int MAX_PING_NUM = 100;

    @Dropdown(
            name = "Hypixel Rank",
            options = {"Select your rank!", "Default", "VIP", "VIP+", "MVP", "MVP+", "MVP++"},
            description = "Your hypixel rank. Automatically set by running /owa getrank"
    )
    public static int PLAYER_HYPIXEL_RANK = 0;

    @Switch(
            name = "Check for updates",
            description = "Should the mod check for updates when you join Hypixel?"
    )
    public static boolean CHECK_FOR_UPDATES = true;

    @Switch(
            name = "Mimic Alert",
            description = "Alerts you when a mimic spawns in dungeons.",
            category = "Features",
            subcategory = "Dungeons"
    )
    public static boolean MIMIC_SPAWN_ALERT = false;

    @Text(
            name = "Dungeons Secret Click Sound",
            description = "Customize the sound that plays when you click a secret in dungeons e.g. random.successful_hit. Leave blank for no sound, credit to AtonAddons for the idea",
            category = "Features",
            subcategory = "Dungeons"
    )
    public static String SECRET_CLICK_SOUND = "random.break";

    @Slider(
            name = "Click Sound Volume",
            description = "Change the volume of the dungeons secret click sound",
            category = "Features",
            subcategory = "Dungeons",
            min = 1f, max = 10.01f,
            step = 1
    )
    public static float SECRET_CLICK_VOLUME = 5f;

    @Slider(
            name = "Click Sound Pitch",
            description = "Change the pitch of the dungeons secret click sound",
            category = "Features",
            subcategory = "Dungeons",
            min = 5f, max = 20f,
            step = 1
    )
    public static float SECRET_CLICK_PITCH = 10f;

    @Switch(
            name = "Debug Mode",
            description = "Should the mod write things like HTTP responses to the log?"
    )
    public static boolean VERBOSE_CODE_SWITCH = false;

    @Switch(
            name = "Funny Stuff"
    )
    public static boolean FUNNY_STUFF_SECRET = true;

    @Dropdown(
            name = "Custom Name Mode",
            description = "Select which custom name mode to use.",
            category = "Custom Name",
            options = {"Custom", "SBA Chroma", "Scuffed Chroma"}
    )
    public static int CUSTOM_NAME_MODE = 2;

    @Text(
            name = "Custom Name",
            description = "Allows you to customize your name. Leave blank for default name. Use \"&&\" for colour codes.",
            category = "Custom Name"
    )
    public static String CUSTOM_NAME_EDITOR = "";

    @Text(
            name = "Custom Rank",
            description = "Allows you to customize your rank. Leave blank for default name. Use \"&&\" for colour codes.",
            category = "Custom Name"
    )
    public static String CUSTOM_RANK_EDITOR = "";

    @Switch(
            name = "Replace Rank",
            description = "Should your rank be replaced with the custom name?",
            category = "Custom Name"
    )
    public static boolean NAME_REPLACE_RANK = false;

    @Info(
            text = "\"Custom\" lets you edit your name, while the other two options only change the colour",
            type = InfoType.INFO,
            category = "Custom Name",
            size = 2
    )
    public static boolean ignored2;

    @Slider(
            name = "Scuffed Chroma Speed (ms)",
            description = "Change the speed of the scuffed chroma.",
            min = 0,
            max = 500,
            step = 50,
            category = "Custom Name"

    )
    public static int SCUFFED_CHROMA_SPEED = 100;

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

    @Text(
            name = "Chroma Text",
            description = "Allows you to colour specific text with chroma.",
            category = "Chat Replacers",
            multiline = true
    )
    public static String CHROMA_TEXT_REPLACE = "";

    @DualOption(
            name = "Chroma Type",
            description = "Change the type of chroma that the mod uses.",
            category = "Chat Replacers",
            left = "SBA Chroma",
            right = "Scuffed Chroma"
    )
    public static boolean CHROMA_TYPE = false;

    @Info(
            text = "Seperate the text to be replaced with commas. Use && for colour.",
            type = InfoType.INFO,
            category = "Chat Replacers",
            size = 1

    )
    public static boolean ignored;
    @Switch(
            name = "Dungeons Chat Cleanup",
            description = "Hides some useless/spammy messages in dungeons",
            category = "Chat Replacers",
            subcategory = "Chat Cleanup"
    )
    public static boolean CATA_CHAT_CLEANUP = false;

    @Switch(
            name = "Third Person FOV changer",
            description = "Changes your FOV when you enter third person",
            category = "Features",
            subcategory = "Perspective"
    )
    public static boolean THIRD_PERSON_FOV = false;

    @Slider(
            name = "Third Person FOV modifier",
            category = "Features",
            subcategory = "Perspective",
            min = 30,
            max = 140,
            step = 1
    )
    public static int THIRD_PERSON_MODIFIER = 70;

    @Slider(
            name = "Normal FOV",
            description = "Set this to the standard FOV that you use",
            category = "Features",
            subcategory = "Perspective",
            min = 30,
            max = 140,
            step = 1
    )
    public static int STANDARD_VIEW_MODIFIER = 70;

    @Switch(
            name = "Send Garden Milestones to Guild Chat",
            description = "When you achieve a new milestone, a button will appear to send to guild chat",
            category = "Features",
            subcategory = "Garden"
    )
    public static boolean GARDEN_MILESTONE_MSG = false;

    @Switch(
            name = "Prevent Insta-selling Kismets to Bazaar",
            description = "Stops you clicking the insta-sell button when you have kismets in your inventory",
            category = "Features",
            subcategory = "Misc"
    )
    public static boolean STOP_INSTA_SELL = false;

    @Switch(
            name = "Prevent Deleting Souls from Necro-Sword",
            description = "Stops you removing souls from the necromancer sword",
            category = "Features",
            subcategory = "Misc"
    )
    public static boolean STOP_NECROMANCER_REMOVE = false;

    @HUD(
            name = "F7 Terminal Display",
            category = "HUDS",
            subcategory = "Dungeons"
    )
    public static DungeonsTerminalDisplay dungeonsTerminalDisplay = new DungeonsTerminalDisplay();

    @HUD(
            name = "Trevor Cooldown (Requires Trevor Chat Cleanup)",
            category = "HUDS",
            subcategory = "Trevor"
    )
    public static TrevorCooldown trevorCooldown = new TrevorCooldown();

    @HUD(
            name = "Trevor Loot Tracker (Requires Trevor Chat Cleanup)",
            category = "HUDS",
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

    @Switch(
            name = "Click Join Messages to Add Friend",
            description = "Makes Skywars/Bedwars player join messages clickable to add them as friends",
            category = "Non-Skyblock"
    )
    public static boolean CLICK_SKYWARS_FRIENDS = false;


    public NewConfig() {
        super(new Mod(OwnwnAddons.NAME, ModType.UTIL_QOL), OwnwnAddons.MODID + ".json");

        initialize();

        addDependency("CUSTOM_RANK_EDITOR", "CUSTOM_NAME_MODE", () -> CUSTOM_NAME_MODE == 0);
        addDependency("CUSTOM_NAME_EDITOR", "CUSTOM_NAME_MODE", () -> CUSTOM_NAME_MODE == 0);
        addDependency("NAME_REPLACE_RANK", "CUSTOM_NAME_MODE", () -> CUSTOM_NAME_MODE == 0);
        addDependency("SCUFFED_CHROMA_SPEED", "CUSTOM_NAME_MODE", () -> CUSTOM_NAME_MODE == 2);

//        addDependency("CUSTOM_NAME_NAMETAG, CUSTOM_RANK_EDITOR, NAME_REPLACE_RANK, CUSTOM_NAME_TOOLTIPS", "CUSTOM_NAME_MODE == 0 || CUSTOM_NAME_MODE == 1");
//        addDependency("CHROMA_SPEED", "CUSTOM_NAME_MODE == 0 || CUSTOM_NAME_MODE == 1");

    }
}

