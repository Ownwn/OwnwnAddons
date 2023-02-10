package com.ownwn.ownwnaddons.utils;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Number;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneKeyBind;
import cc.polyfrost.oneconfig.config.data.InfoType;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.libs.universal.UKeyboard;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.features.CreateGhostPick;
import com.ownwn.ownwnaddons.features.DungeonsTerminalDisplay;
import com.ownwn.ownwnaddons.features.TrevorCooldown;
import com.ownwn.ownwnaddons.features.TrevorLootTracker;

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

    @KeyBind(
            name = "Create Ghost Pickaxe",
            description = "Duplicates your current pickaxe client-side",
            category = "Features",
            subcategory = "Dungeons"
    )
    public static OneKeyBind GHOST_PICK_KEY = new OneKeyBind(UKeyboard.KEY_NONE);

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

    @Switch(
            name = "Aggressive Name Replacement",
            description = "Aggressive mode will replace any text with your name, but may break often.",
            category = "Custom Name"
    )
    public static boolean CUSTOM_NAME_AGGRO = false;

    @Switch(
            name = "Render in Tooltips",
            description = "Should your custom name be rendered in item tooltips?",
            category = "Custom Name"
    )
    public static boolean CUSTOM_NAME_TOOLTIPS = false;

    @Switch(
            name = "Render in Nametag",
            description = "Should your nametag be changed to your custom name?",
            category = "Custom Name"
    )
    public static boolean CUSTOM_NAME_NAMETAG = false;

    @Info(
            text = "Aggressive Name Replacement may break some chat messages",
            type = InfoType.WARNING,
            category = "Custom Name",
            size = 2
    )
    public static boolean ignored2;

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
            name = "Camoflaged Chat Shortener",
            description = "Customize chat messages sent by Camoflagued's bridge bot",
            category = "Chat Replacers"
    )
    public static String GUILD_MSG_PRETTY = "&&3&&lDiscord &&e> &&aNAME&&f: MSG";

    @Text(
            name = "Chat Shortener Name",
            description = "The name of the bridge bot",
            category = "Chat Replacers"
    )
    public static String GUILD_MSG_NAME = "Pikaaahhh";

    @Info(
            text = "Add \"NAME\" for the users name and \"MSG\" for the users message",
            type = InfoType.INFO,
            category = "Chat Replacers",
            size = 2

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

    @HUD(
            name = "F7 Terminal Display",
            category = "Features",
            subcategory = "Dungeons"
    )
    public static DungeonsTerminalDisplay dungeonsTerminalDisplay = new DungeonsTerminalDisplay();

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

    @Switch(
            name = "Click Join Messages to Add Friend",
            description = "Makes Skywars/Bedwars player join messages clickable to add them as friends",
            category = "Non-Skyblock"
    )
    public static boolean CLICK_SKYWARS_FRIENDS = false;


    public NewConfig() {
        super(new Mod(OwnwnAddons.NAME, ModType.UTIL_QOL), OwnwnAddons.MODID + ".json");

        initialize();
        registerKeyBind(GHOST_PICK_KEY, () -> CreateGhostPick.runnable.run());
    }
}

