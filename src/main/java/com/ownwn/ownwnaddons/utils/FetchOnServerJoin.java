package com.ownwn.ownwnaddons.utils;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.Multithreading;
import cc.polyfrost.oneconfig.utils.NetworkUtils;
import com.google.gson.JsonElement;
import com.ownwn.ownwnaddons.OwnwnAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class FetchOnServerJoin {
    public static long bumpRankTime = 0;
    public static long checkUpdateTime = 0;
    public static long fetchNameTime = 0;
    public static final String RAINBOW_NAMES_URL = "https://raw.githubusercontent.com/Ownwn/OwaData/main/rainbownames.json";
    public static final String RELEASES_URL = "https://github.com/Ownwn/OwnwnAddons/releases/latest";
    public static final String UPDATE_CHECK_URL = "https://api.github.com/repos/ownwn/ownwnaddons/releases/latest";
    public static List<String> nameList = new CopyOnWriteArrayList<>();
    // use copyonwritearraylist to avoid the game crashing if the array list is read whilst it's being written to


    @SubscribeEvent
    public void JoinServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {

        if (NewConfig.ONBOARDING_FIRST_TIME) {
            Multithreading.schedule(onboarding, 2, TimeUnit.SECONDS);
        }


        if (NewConfig.PLAYER_HYPIXEL_RANK == 0 && System.currentTimeMillis() - bumpRankTime > 10000) {
            bumpRankTime = System.currentTimeMillis();
            Multithreading.schedule(bumpHypixelRank, 3, TimeUnit.SECONDS);
        }

        if (NewConfig.CHECK_FOR_UPDATES && System.currentTimeMillis() - checkUpdateTime > 10000) {
            checkUpdateTime = System.currentTimeMillis();
            Multithreading.schedule(checkforUpdates, 1, TimeUnit.SECONDS);

        }

        if (NewConfig.SHARED_RAINBOW_NAMES && System.currentTimeMillis() - fetchNameTime > 10000) {
            fetchNameTime = System.currentTimeMillis();
            Multithreading.schedule(fetchotherNames, 2, TimeUnit.SECONDS);

        }

    }

    public static Runnable bumpHypixelRank = () -> {
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        UChat.chat(OwnwnAddons.PREFIX + "&aYour Hypixel rank isn't set! The custom rank feature will not work without it. &aSet it in &b/owa");
    };

    public static Runnable checkforUpdates = () -> {
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        String latestVersion;

        try {
            latestVersion = NetworkUtils.getJsonElement(UPDATE_CHECK_URL).getAsJsonObject().get("tag_name").getAsString().substring(1);
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
                        .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, RELEASES_URL)));

        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(OwnwnAddons.PREFIX + "§aNew version of §bOwn§bwnAddons §aavailable!" +
                "\n §aYou are using §b" + currentVersion + " §aand the latest version is §b" + latestVersion +
                "\n §aDisable this message in §b/owa").appendSibling(githubLink));
        if (NewConfig.FUNNY_STUFF_SECRET) {
            Minecraft.getMinecraft().thePlayer.playSound("ownwnaddons:bidenupdate", 1f, 1f);
            System.out.println("OwnwnAddons: Playing update reminder");
        }
    };

    public static Runnable fetchotherNames = () -> {
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }


        nameList.clear();
        nameList.add("OwnwnAddons");
        try {
            for (JsonElement element : NetworkUtils.getJsonElement(RAINBOW_NAMES_URL).getAsJsonObject().get("usernames").getAsJsonArray()) {
                nameList.add(element.getAsString());
            }

            if (nameList.size() == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            UChat.chat(OwnwnAddons.PREFIX + "&cSomething went wrong fetching rainbow names! Please check your logs.");
            e.printStackTrace();
            return;
        }

        if (!NewConfig.VERBOSE_CODE_SWITCH) {
            return;
        }
        System.out.println("OwnwnAddons: Successfully fetched rainbow names.");

    };

    public static Runnable onboarding = () -> {
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        UChat.chat(OwnwnAddons.PREFIX + "&aThanks for downloading &bOw&bnwnAddons!" +
                "\n &aYou can access the config with &b/owa" +
                "\n &aIf you want your name to appear chroma" +
                "\n &ato other users, message me on Hypixel" +
                "\n &aor on Discord at &b@own&bwn");
        NewConfig.ONBOARDING_FIRST_TIME = false;
    };

}
