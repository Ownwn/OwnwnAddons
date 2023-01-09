package com.ownwn.ownwnaddons.features.chat;

import cc.polyfrost.oneconfig.events.event.LocrawEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import cc.polyfrost.oneconfig.libs.universal.UChat;
import com.ownwn.ownwnaddons.utils.NewConfig;

public class FragRunLocraw {
    public long dungeonEnterTime = 0;

    @Subscribe
    public void onLocraw(LocrawEvent event) {

        if (!NewConfig.fragRunTimer.isEnabled()) {
            return;
        }

        if (event.info.getGameMode().equals("dungeon") && System.currentTimeMillis() - dungeonEnterTime < 600000) {
            UChat.chat("&bThat run took: " + (System.currentTimeMillis() - dungeonEnterTime) / 1000);
            dungeonEnterTime = System.currentTimeMillis();
        }
    }
}
