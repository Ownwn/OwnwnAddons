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
            name = "Custom Name",
            description = "Allows you to customize your name. Leave blank for default name. Use \"&&\" for colour codes, Credit to NEU for the idea.",
            category = "Features"
    )
    public String CUSTOM_NAME_EDITOR = "";


    public NewConfig() {
        super(new File("./config/OwnwnAddons.toml"));
        initialize();
    }
}

