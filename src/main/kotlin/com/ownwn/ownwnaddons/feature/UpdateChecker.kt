package com.ownwn.ownwnaddons.feature

import cc.polyfrost.oneconfig.libs.eventbus.Subscribe
import cc.polyfrost.oneconfig.libs.universal.UChat
import cc.polyfrost.oneconfig.utils.Multithreading
import cc.polyfrost.oneconfig.utils.NetworkUtils
import com.ownwn.ownwnaddons.Config
import com.ownwn.ownwnaddons.OwnwnAddons
import com.ownwn.ownwnaddons.util.Game
import com.ownwn.ownwnaddons.util.ServerJoinEvent
import net.minecraft.client.Minecraft
import net.minecraft.event.ClickEvent
import net.minecraft.event.HoverEvent
import net.minecraft.util.ChatComponentText
import net.minecraft.util.ChatStyle
import java.util.concurrent.TimeUnit

object UpdateChecker {
    private var checkUpdateTime = 0L

    private const val RELEASE_URL: String = "https://github.com/Ownwn/OwnwnAddons/releases/latest"
    private const val UPDATE_CHECK_URL: String = "https://api.github.com/repos/ownwn/ownwnaddons/releases/latest"


    @Subscribe
    fun onJoinServer(event: ServerJoinEvent) {
        if (Config.onboardingFirstTime) {
            UChat.chat(
                "${OwnwnAddons.PREFIX}&aThanks for downloading &bOw&bnwnAddons!" +
                        "\n &aYou can access the config with &b/owa" +
                        "\n &aIf you want your name to appear chroma" +
                        "\n &ato other users, message me on Hypixel" +
                        "\n &aor on Discord at &b@own&bwn"

            )

            Config.onboardingFirstTime = false
        }

        if (!Config.checkForUpdates) return
        if (System.currentTimeMillis() - checkUpdateTime < 10000) return

        checkUpdateTime = System.currentTimeMillis()
        Multithreading.schedule(checkForUpdates, 0, TimeUnit.SECONDS)

    }


    private var checkForUpdates = Runnable {

        val versionTag = NetworkUtils.getJsonElement(UPDATE_CHECK_URL)?.asJsonObject?.get("tag_name")?.asString
        if (versionTag == null) {
            UChat.chat(OwnwnAddons.PREFIX + "&cError checking for updates.")
            return@Runnable
        }

        val latestVersion = versionTag.substring(1)
        val currentVersion = OwnwnAddons.VERSION

        if (latestVersion == currentVersion) return@Runnable

        val githubLink = ChatComponentText("§b§l[GITHUB]")
            .setChatStyle(
                ChatStyle().setChatHoverEvent(
                    HoverEvent(
                        HoverEvent.Action.SHOW_TEXT,
                        ChatComponentText("§aOpen the §bOwnwn§bAddons §aGithub")
                    )
                )
                    .setChatClickEvent(
                        ClickEvent(
                            ClickEvent.Action.OPEN_URL,
                            RELEASE_URL
                        )
                    )
            )


        Game.player?.addChatMessage(
            ChatComponentText(
            "${OwnwnAddons.PREFIX}§aNew version of §bOwn§bwnAddons §aavailable!: §b$latestVersion" +
                    "\n §aDisable this message in §b/owa ").appendSibling(githubLink))


        if (Config.secretPrankMessages) {
            Game.player?.playSound("ownwnaddons:bidenupdate", 1f, 1f)
            println("OwnwnAddons: Playing update reminder")
        }
    }
}