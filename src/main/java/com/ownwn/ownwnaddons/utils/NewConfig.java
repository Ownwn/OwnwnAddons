package com.ownwn.ownwnaddons.utils;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

import java.io.File;

public class NewConfig extends Vigilant {

    @Property(
            type = PropertyType.TEXT,
            name = "API Key",
            description = "Used for things such as the party finder display",
            category = "Stuff"
    )
    public String API_KEY_TEXT = "";

    @Property(
            type = PropertyType.SWITCH,
            name = "Party Finder",
            description = "Toggles the party finder stats display feature",
            category = "Features"
    )
    public boolean PARTY_FINDER_SWITCH = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Verbose Code",
            description = "Should the mod write things like HTTP responses to the log?",
            category = "Stuff"
    )
    public boolean VERBOSE_CODE_SWITCH = false;

    @Property(
            type = PropertyType.TEXT,
            name = "Custom Chat Colour",
            description = "Customize the colour of your chat messages. Leave blank for default colour. Use \"&&\" for colour codes, Credit to NEU for the idea.",
            category = "Chat Replacers",
            subcategory = "Custom Chat"
    )
    public String CUSTOM_CHAT_COLOUR = "";
    @Property(
            type = PropertyType.TEXT,
            name = "Custom Name",
            description = "Allows you to customize your name. Leave blank for default name. Use \"&&\" for colour codes, Credit to NEU for the idea.",
            category = "Chat Replacers",
            subcategory = "Custom Chat"
    )
    public String CUSTOM_NAME_EDITOR = "";

    @Property(
            type = PropertyType.SWITCH,
            name = "Replace Rank",
            description = "Should your rank be replaced with the custom name?",
            category = "Chat Replacers",
            subcategory = "Custom Chat"
    )
    public boolean NAME_REPLACE_RANK = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Trevor Chat Cleanup",
            description = "Cleans up the chat messages sent by Trevor the Trapper",
            category = "Chat Replacers"
    )
    public boolean TREVOR_CHAT_CLEANUP = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Bazaar Chat Cleanup",
            description = "Cleans up the chat messages sent by the Bazaar",
            category = "Chat Replacers"
    )
    public boolean BAZAAR_CHAT_CLEANUP = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Trevor Cooldown",
            description = "Displays a cooldown for Trevor the Trapper",
            category = "Features",
            subcategory = "Trevor Cooldown"
    )
    public boolean TREVOR_COOLDOWN_GOOD = false;

//    @Property(
//            type = PropertyType.SLIDER,
//            name = "Verticle Location",
//            description = "Changes the verticle location of the trapper cooldown",
//            category = "Features",
//            subcategory = "Trevor Cooldown",
//            max = 500
//    )
//    public int TREVOR_COOLDOWN_Y = 0;
//
//    @Property(
//            type = PropertyType.SLIDER,
//            name = "Horizontal Location",
//            description = "Changes the horizontal location of the trapper cooldown",
//            category = "Features",
//            subcategory = "Trevor Cooldown",
//            max = 600
//    )
//    public int TREVOR_COOLDOWN_X = 0;



    public NewConfig() {
        super(new File("./config/OwnwnAddons.toml"));
        initialize();
    }
}

