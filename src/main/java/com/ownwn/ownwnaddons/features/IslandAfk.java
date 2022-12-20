package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.events.event.LocrawEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.commands.IslandAfkTimer;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class IslandAfk {
    public static String mapName = null;
    @Subscribe
    public void onLocraw(LocrawEvent event) {
        if (!NewConfig.ISLAND_AFK_TRACKER) {
            return;
        }

        LocrawInfo.GameType gameType = event.info.getGameType();
        mapName = event.info.getMapName();

        if (gameType == LocrawInfo.GameType.LIMBO) {
            sendTime("Limbo");
        } else if (gameType == LocrawInfo.GameType.PROTOTYPE) {
            sendTime("Prototype");
        } else if (gameType == LocrawInfo.GameType.SKYBLOCK) {

            if (mapName.equals("Private Island")) {
                sendTime("Private Island");

            } else if (mapName.equals("Hub")) {
                sendTime("Skyblock Hub");
            } else {
                sendTime("Unknown Location");
            }

        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (!NewConfig.ISLAND_AFK_TRACKER) {
            return;
        }
        if (mapName != null && mapName.equals("Private Island")) {
            IslandAfkTimer.endAfk();
        }

    }

    public void sendTime(String location) {
        UChat.chat(OwnwnAddons.PREFIX + "&aEntered &b" + location + " &aat: &b" + java.time.LocalTime.now());
    }
}
