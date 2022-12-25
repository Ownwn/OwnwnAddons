package com.ownwn.ownwnaddons.utils;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.HUD;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.annotations.Text;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.features.FragRunTimer;
import com.ownwn.ownwnaddons.features.TrevorCooldown;

public class NewConfig extends Config {


    @Switch(
            name = "Party Finder",
            description = "Toggles the party finder stats display feature",
            category = "Features",
            subcategory = "Party Finder"
    )
    public static boolean PARTY_FINDER_SWITCH = false;

    @Switch(
            name = "Skills",
            description = "Toggles the skills display",
            category = "Features",
            subcategory = "Party Finder"
    )
    public static boolean PARTY_SKILLS_SWITCH = false;

    @Switch(
            name = "Weight & Networth",
            description = "Toggles the weight and networth display",
            category = "Features",
            subcategory = "Party Finder"
    )
    public static boolean PARTY_WEIGHT_SWITCH = false;

    @Switch(
            name = "Secrets",
            description = "Toggles the dungeon secrets display",
            category = "Features",
            subcategory = "Party Finder"
    )
    public static boolean PARTY_SECRETS_SWITCH = false;

    @Switch(
            name = "Blood Mob Kills",
            description = "Toggles the blood kills display",
            category = "Features",
            subcategory = "Party Finder"
    )
    public static boolean PARTY_BLOOD_SWITCH = false;

    @Switch(
            name = "Notable Items",
            description = "Toggles the notable items display",
            category = "Features",
            subcategory = "Party Finder"
    )
    public static boolean PARTY_ITEMS_SWITCH = false;

    @Switch(
            name = "Kick Button",
            description = "Adds a kick button to the stats display",
            category = "Features",
            subcategory = "Party Finder"
    )
    public static boolean PARTY_KICK_SWITCH = false;

    @Switch(
            name = "Super Secret Stats Display",
            description = "Adds cool, very important stats display",
            category = "Features",
            subcategory = "Party Finder"
    )
    public static boolean SECRET_PARTY_FINDER = false;

    @Switch(
            name = "Island AFK Tracker (W.I.P)",
            description = "Tracks when you leave and enter your private island",
            category = "Features"
    )
    public static boolean ISLAND_AFK_TRACKER = false;

    @Switch(
            name = "Bazaar Order Tracker (N.Y.I)",
            description = "Tracks your bazaar orders and their prices",
            category = "Features"
    )
    public static boolean BAZAAR_ORDER_TRACKER = false;

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
            category = "Chat Replacers"
    )
    public static boolean TREVOR_CHAT_CLEANUP = false;

    @Switch(
            name = "Bazaar Chat Cleanup",
            description = "Cleans up the chat messages sent by the Bazaar",
            category = "Chat Replacers"
    )
    public static boolean BAZAAR_CHAT_CLEANUP = false;

    @Switch(
            name = "Bank Chat Cleanup",
            description = "Cleans up the chat messages sent by the Bank",
            category = "Chat Replacers"
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

    @HUD(
            name = "Frag Run Tracker (W.I.P)",
            category = "Features"
    )
    public static FragRunTimer fragRunTimer = new FragRunTimer();
    @HUD(
            name = "Trevor Cooldown",
            category = "Features",
            subcategory = "Trevor Cooldown"
    )
    public static TrevorCooldown trevorCooldown = new TrevorCooldown();


    public NewConfig() {
        super(new Mod(OwnwnAddons.NAME, ModType.UTIL_QOL), OwnwnAddons.MODID + ".json");
        initialize();
    }
}

