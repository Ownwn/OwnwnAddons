package com.ownwn.ownwnaddons.outside;

import gg.essential.universal.UChat;
import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

import java.awt.*;
import java.io.File;

public class NewConfig extends Vigilant {
    @Property(
            type = PropertyType.SWITCH,
            name = "Config Test",
            description = "Toggles the config test event",
            category = "Events"
    )
    public boolean CONFIG_TEST_SWITCH = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Repelling Candle",
            description = "Toggles whether the (N.Y.I) repelling candle feature should activate",
            category = "Events"
    )
    public boolean REPELLING_CANDLE_SWITCH = false;

    public NewConfig() {
        super(new File("./config/OwnwnAddons/OwnwnAddons.toml"));
        initialize();
    }

}

