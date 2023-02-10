package com.ownwn.ownwnaddons.utils;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.NetworkUtils;
import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class FetchOnServerJoin {
    long getRankTime = 0;
    long checkUpdateTime = 0;

    @SubscribeEvent
    public void JoinServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {

        if (NewConfig.PLAYER_HYPIXEL_RANK == 0 && System.currentTimeMillis() - getRankTime > 10000) {
            getRankTime = System.currentTimeMillis();
        }

        if (NewConfig.CHECK_FOR_UPDATES && System.currentTimeMillis() - checkUpdateTime > 10000) {
            checkUpdateTime = System.currentTimeMillis();
        }






    }
    @SubscribeEvent
    public void getHypixelRank(TickEvent.ClientTickEvent event) {
        if (getRankTime == 0) {
            return;
        }
        if (System.currentTimeMillis() < (getRankTime + 6000)) {
            return;
        }
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        UChat.chat(OwnwnAddons.PREFIX + "&aYour Hypixel rank isn't set! The custom name thingo will not work without it. &aSet it in &b/owa");
        getRankTime = 0;
    }

    @SubscribeEvent
    public void checkForUpdates(TickEvent.ClientTickEvent event) {
        if (checkUpdateTime == 0) {
            return;
        }
        if (System.currentTimeMillis() < (checkUpdateTime + 5000)) {
            return;
        }
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        Thread T = new Thread(() -> {
            String latestVersion;

            try {
                latestVersion = NetworkUtils.getJsonElement("https://api.github.com/repos/ownwn/ownwnaddons/releases/latest").getAsJsonObject().get("tag_name").getAsString().substring(1);
            } catch (Exception e) {
                UChat.chat(OwnwnAddons.PREFIX + "&cError checking for updates! Please check the logs for more information");
                e.printStackTrace();
                return;
            }
            String currentVersion = OwnwnAddons.VERSION;
            if (latestVersion.equals(currentVersion)) {
                return;
            }

            IChatComponent githubLink = new ChatComponentText("\n§b§l[GITHUB]")
                    .setChatStyle(new ChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("§aOpen the §bOwnwnAddons §aGithub")))
                            .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Ownwn/OwnwnAddons/releases/latest")));

            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(OwnwnAddons.PREFIX + "§aNew version of §bOwnwnAddons §aavailable!" +
                    "\n §aYou are using §b" + currentVersion + " §aand the latest version is §b" + latestVersion +
                    "\n §aDisable this message in §b/owa").appendSibling(githubLink));
        });
        T.start();
        checkUpdateTime = 0;
    }
}
