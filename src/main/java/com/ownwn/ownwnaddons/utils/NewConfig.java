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
            name = "Secret Stats Display",
            description = "Adds secret, very important stats display",
            category = "Features",
            subcategory = "Party Finder"
    )
    public static boolean SECRET_PARTY_FINDER = false;

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

    @HUD(
            name = "Frag Run Tracker",
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

