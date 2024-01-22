package com.ownwn.ownwnaddons.utils


import cc.polyfrost.oneconfig.libs.universal.UChat
import cc.polyfrost.oneconfig.utils.Multithreading
import cc.polyfrost.oneconfig.utils.NetworkUtils
import com.ownwn.ownwnaddons.OwnwnAddons
import net.minecraft.client.Minecraft
import net.minecraft.event.ClickEvent
import net.minecraft.event.HoverEvent
import net.minecraft.util.ChatComponentText
import net.minecraft.util.ChatStyle
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.util.concurrent.TimeUnit

class UpdateChecker {
    private var checkUpdateTime = 0L

    private val releasesURL: String = "https://github.com/Ownwn/OwnwnAddons/releases/latest"
    private val updateCheckerUrl: String = "https://api.github.com/repos/ownwn/ownwnaddons/releases/latest"


    @SubscribeEvent
    fun onJoinServer(event: ServerJoinEvent) {
        if (NewConfig.ONBOARDING_FIRST_TIME) {
            UChat.chat(
                "${OwnwnAddons.PREFIX}&aThanks for downloading &bOw&bnwnAddons!" +
                        "\n &aYou can access the config with &b/owa" +
                        "\n &aIf you want your name to appear chroma" +
                        "\n &ato other users, message me on Hypixel" +
                        "\n &aor on Discord at &b@own&bwn"

            )

            NewConfig.ONBOARDING_FIRST_TIME = false
        }

        if (!NewConfig.CHECK_FOR_UPDATES) return
        if (System.currentTimeMillis() - checkUpdateTime < 10000) return

        checkUpdateTime = System.currentTimeMillis()
        Multithreading.schedule(checkForUpdates, 0, TimeUnit.SECONDS)

    }


    private var checkForUpdates = Runnable {

        val versionTag = NetworkUtils.getJsonElement(updateCheckerUrl)?.asJsonObject?.get("tag_name")?.asString
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
                            releasesURL
                        )
                    )
            )

        val player = Minecraft.getMinecraft().thePlayer

        player.addChatMessage(ChatComponentText(
            "${OwnwnAddons.PREFIX}§aNew version of §bOwn§bwnAddons §aavailable!: §b$latestVersion" +
                    "\n §aDisable this message in §b/owa ").appendSibling(githubLink))

        if (!NewConfig.FUNNY_STUFF_SECRET) return@Runnable

        Minecraft.getMinecraft().thePlayer.playSound("ownwnaddons:bidenupdate", 1f, 1f)
        println("OwnwnAddons: Playing update reminder")

    }
}