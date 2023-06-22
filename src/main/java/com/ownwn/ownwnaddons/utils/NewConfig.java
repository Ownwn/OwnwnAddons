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
import com.ownwn.ownwnaddons.features.OnScreenTimer;
import com.ownwn.ownwnaddons.features.TrevorCooldown;
import com.ownwn.ownwnaddons.features.TrevorLootTracker;
import com.ownwn.ownwnaddons.features.dungeons.DungeonsTerminalDisplay;
import com.ownwn.ownwnaddons.features.dungeons.SinSeekerHUD;

public class NewConfig extends Config {

    @Number(
            name = "Average Ping (ms)",
            description = "Can be obtained with /skytils ping. Used for features such as the wither blade sounds.",
            min = 10, max = 1000,
            step = 10
    )
    public static int AVERAGE_PING_NUM = 100;

    @Dropdown(
            name = "Hypixel Rank",
            options = {"Default", "VIP", "VIP+", "MVP", "MVP+", "MVP++"},
            description = "Your hypixel rank."
    )
    public static int PLAYER_HYPIXEL_RANK = 0;

    @Switch(
            name = "Check for updates",
            description = "Should the mod check for updates when you join Hypixel?"
    )
    public static boolean CHECK_FOR_UPDATES = true;

    @Text(
            name = "Party-Finder Highlight (Warn ⚠)",
            description = "Highlights specific names in Mort's Party Finder. Seperate names with \", \"",
            category = "Features",
            subcategory = "Dungeons",
            multiline = true
    )
    public static String PF_NAME_HIGHLIGHT = "";

    @Text(
            name = "Party-Finder Highlight (Good ★)",
            description = "Highlights specific names in Mort's Party Finder. Seperate names with \", \"",
            category = "Features",
            subcategory = "Dungeons",
            multiline = true
    )
    public static String PF_NAME_GOOD = "";

    @Switch(
            name = "Debug Mode",
            description = "Should the mod send helpful messages to the log? Also allows onboarding toggle"
            // include this in the config in case the user wants to see the message again
    )
    public static boolean VERBOSE_CODE_SWITCH = false;

    @Switch(
            name = "Funny Stuff",
            description = "Changes some text and adds a couple of jokes to the mod (nothing malicious)"
    )
    public static boolean FUNNY_STUFF_SECRET = false;

    @Switch(
            name = "Onboarding"
    )
    public static boolean ONBOARDING_FIRST_TIME = true;

   @Switch(
           name = "Custom Name Toggle",
           category = "Custom Name"
   )
   public static boolean CUSTOM_NAME_TOGGLE = false;

    @Text(
            name = "Custom Name",
            description = "Allows you to customize your name. Leave blank for default name.",
            category = "Custom Name"
    )
    public static String CUSTOM_NAME_EDITOR = "&&cJoe";

    @Switch(
            name = "Custom Rank Toggle",
            category = "Custom Name"
    )
    public static boolean CUSTOM_RANK_TOGGLE = false;

    @Text(
            name = "Custom Rank",
            description = "Allows you to customize your rank. Leave blank for default name.",
            category = "Custom Name"
    )
    public static String CUSTOM_RANK_EDITOR = "&&9[Rank]";


    @Info(
            text = "Use \"&&\" for colour codes. Also try out &&x",
            type = InfoType.INFO,
            category = "Custom Name",
            size = 2
    )
    public static boolean ignored2;

    @Slider(
            name = "Scuffed Chroma Delay (ms)",
            description = "Change the speed of the scuffed chroma.",
            min = 0,
            max = 500,
            step = 50

    )
    public static int SCUFFED_CHROMA_SPEED = 100;

    @Switch(
            name = "Turn other user's names rainbow",
            description = "Makes the names of other mod users rainbow.",
            category = "Custom Name"
    )
    public static boolean SHARED_RAINBOW_NAMES = true;

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
    public static String CHROMA_TEXT_REPLACE = "Bits:, Piggy:";

    @DualOption(
            name = "Chroma Type",
            description = "Change the type of chroma that the mod uses.",
            left = "SBA Chroma",
            right = "Scuffed Chroma"
    )
    public static boolean CHROMA_TYPE = false;

    @Switch(
            name = "Always Render SBA Chroma",
            description = "Makes SBA chroma render everywhere, not just on Skyblock"
    )
    public static boolean FORCE_SBA_CHROMA = false;

    @Info(
            text = "Seperate the text to be replaced with commas. Use && for colour codes.",
            type = InfoType.INFO,
            category = "Chat Replacers",
            size = 2

    )
    public static boolean ignored;

    @Text(
            name = "Custom Sidebar URL",
            description = "Replaces the hypixel URL with something else",
            category = "Chat Replacers"
    )
    public static String CUSTOM_SIDEBAR_URL = "";

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

    @KeyBind(
            name = "Toggle Timer",
            description = "Toggles an on-screen timer. Make sure the HUD is enabled",
            category = "Features",
            subcategory = "Misc"
    )
    public static OneKeyBind START_TIMER_KEY = new OneKeyBind(UKeyboard.KEY_NONE);

    @Button(
            name = "Reset Timer",
            text = "Reset",
            category = "Features",
            subcategory = "Misc"
    )
    Runnable resetTimer = OnScreenTimer::resetTimer;

    @HUD(
            name = "F7 Terminal Display",
            category = "HUDS",
            subcategory = "Dungeons"
    )
    public static DungeonsTerminalDisplay dungeonsTerminalDisplay = new DungeonsTerminalDisplay();

    @HUD(
            name = "SinSeeker Cooldown",
            category = "HUDS",
            subcategory = "Dungeons"
    )
    public static SinSeekerHUD sinSeekerHUD = new SinSeekerHUD();

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

    @HUD(
            name = "On Screen Timer",
            category = "HUDS"
    )
    public static OnScreenTimer onScreenTimer = new OnScreenTimer();

    @Text(
            name = "Wither Shield Sound",
            description = "Plays a sound when wither shield activates. It's recommended to set \"Mob Zombie Remedy\" in Patcher sounds to 0.",
            category = "Sound",
            subcategory = "Wither Blades"
    )
    public static String WITHER_SHIELD_SOUND = "note.pling";

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
    public static String WITHER_IMPLODE_SOUND = "random.break";

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
            name = "Harp Misclick Warning",
            description = "Warns you when you misclick in the harp",
            category = "Sound"
    )
    public static boolean HARP_MISCLICK_WARNING = false;

    @Info(
            text = "The Alternate Hub feature is very broken, may cause visual issues. Try refreshing your chunks",
            type = InfoType.WARNING,
            category = "Features",
            subcategory = "Misc",
            size = 2

    )
    public static boolean ignored3;

    @Switch(
            name = "Alternate Hub (W.I.P)",
            description = "Changes some blocks in the Hub island.",
            category = "Features",
            subcategory = "Misc"
    )
    public static boolean WORLD_RESKIN_HUB = false;

    @Switch(
            name = "Alternate NEU Lowest BIN",
            description = "Changes the look of NEU's lowest BIN tooltip.",
            category = "Features",
            subcategory = "Misc"
    )
    public static boolean CHANGE_NEU_TOOLTIP = false;


    @Switch(
            name = "Click Join Messages to Add Friend",
            description = "Makes Skywars/Bedwars player join messages clickable to add them as friends",
            category = "Non-Skyblock"
    )
    public static boolean CLICK_MINIGAME_FRIENDS = false;



    public static int totalFriendRequests = 0;
    // register this here, so it can be stored across game restarts



    public NewConfig() {
        super(new Mod(OwnwnAddons.NAME, ModType.UTIL_QOL, "/ownwn.png"), OwnwnAddons.MODID + ".json");

        initialize();



        addDependency("CUSTOM_NAME_EDITOR", "CUSTOM_NAME_TOGGLE");
        addDependency("CUSTOM_RANK_EDITOR", "CUSTOM_RANK_TOGGLE");
        addDependency("CUSTOM_RANK_TOGGLE", "CUSTOM_NAME_TOGGLE");
        addDependency("CUSTOM_RANK_TOGGLE", "PLAYER_HYPIXEL_RANK", () -> PLAYER_HYPIXEL_RANK != 0);

        addDependency("STANDARD_VIEW_MODIFIER", "THIRD_PERSON_FOV");
        addDependency("THIRD_PERSON_MODIFIER", "THIRD_PERSON_FOV");


        addDependency("WITHER_IMPLODE_VOLUME", "WITHER_IMPLODE_SOUND", () -> !WITHER_IMPLODE_SOUND.equals(""));
        addDependency("WITHER_IMPLODE_PITCH", "WITHER_IMPLODE_SOUND", () -> !WITHER_IMPLODE_SOUND.equals(""));


        addDependency("WITHER_SHIELD_VOLUME", "WITHER_SHIELD_SOUND", () -> !WITHER_SHIELD_SOUND.equals(""));
        addDependency("WITHER_SHIELD_PITCH", "WITHER_SHIELD_SOUND", () -> !WITHER_SHIELD_SOUND.equals(""));

        addDependency("ONBOARDING_FIRST_TIME", "VERBOSE_CODE_SWITCH");

        registerKeyBind(START_TIMER_KEY, OnScreenTimer::toggleTimer);

    }
}

