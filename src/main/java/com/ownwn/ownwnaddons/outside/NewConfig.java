package com.ownwn.ownwnaddons.outside;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

import java.io.File;

public class NewConfig extends Vigilant {

    @Property(
            type = PropertyType.SWITCH,
            name = "Repelling Candle",
            description = "Toggles whether the (N.Y.I) repelling candle feature should activate",
            category = "Events"
    )
    public boolean REPELLING_CANDLE_SWITCH = false;

    @Property(
            type = PropertyType.TEXT,
            name = "API Key",
            description = "What is your API Key?",
            category = "Stuff"
    )
    public String API_KEY_TEXT = "";

    @Property(
            type = PropertyType.SWITCH,
            name = "Party Finder",
            description = "Toggles the party finder stats display feature",
            category = "Events"
    )
    public boolean PARTY_FINDER_SWITCH = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Verbose Code",
            description = "Should things like HTTP requests print responses?",
            category = "Stuff"
    )
    public boolean VERBOSE_CODE_SWITCH = false;

    public NewConfig() {
        super(new File("./config/OwnwnAddons/OwnwnAddons.toml"));
        initialize();
    }

}

