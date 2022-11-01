package com.ownwn.ownwnaddons.outside;

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
            type = PropertyType.SWITCH,
            name = "Custom Name Colour",
            description = "Change the colour of your name to anything",
            category = "Features",
            subcategory = "Name Colour"
    )
    public boolean NAME_COLOUR_SWITCH = false;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Name Colour",
            description = "Select the name colour you want",
            category = "Features",
            subcategory = "Name Colour",
            options = {"Black", "Dark Blue", "Green", "Dark Aqua", "Dark Red", "Purple", "Gold", "Grey", "Dark Grey", "Light Blue"}
    )
    public int NAME_COLOUR_SELECT = 0;


    public NewConfig() {
        super(new File("./config/OwnwnAddons.toml"));
        initialize();
    }

}

