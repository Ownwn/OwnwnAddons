package com.ownwn.ownwnaddons.features


import cc.polyfrost.oneconfig.libs.universal.UChat
import cc.polyfrost.oneconfig.utils.Multithreading
import cc.polyfrost.oneconfig.utils.NetworkUtils
import com.ownwn.ownwnaddons.OwnwnAddons
import com.ownwn.ownwnaddons.utils.ColourUtils
import com.ownwn.ownwnaddons.utils.NewConfig
import com.ownwn.ownwnaddons.utils.ServerJoinEvent
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit


object CustomName {

    private val rankRegexList by lazy { // lazy list to work with the lateinit username
        listOf(
            "(§a\\[VIP]) $username",
            "(§a\\[VIP§6\\+§a]) $username",
            "(§b\\[MVP]) $username",
            "(§b\\[MVP(?:§.){0,3}\\+(?:§.){0,3}]) $username",
            "(§6\\[MVP(?:§.){0,3}\\+\\+(?:§.){0,3}]) $username"
        ).map(::Regex)
    }

    private val skyblockLevelPattern = Regex("§8\\[(?:§r)*§.(\\d{1,3})(?:§r)*§8]")
    private lateinit var username: String // init username late when the player exists
    var lastFetchedNames = 0L

    private fun String.replaceAmpersands() = replace("&&", "§")

    // use copyonwritearraylist to avoid crash when reading while writing in thread
    private var rainbowNamesList: MutableList<String> = CopyOnWriteArrayList()
    private const val RAINBOW_NAMES_URL: String = "https://raw.githubusercontent.com/Ownwn/OwaData/main/rainbownames.json"


    fun replaceAllText(text: String): String {
        if (text.isEmpty() || text == "§r") return text
        if (!this::username.isInitialized) return text

        val unformatted = OwnwnAddons.utils.stripFormatting(text)
        if (unformatted.isEmpty()) return text

        var newText = text

        if (unformatted.contains(username)) {
            newText = replaceNameAndRank(newText)
            newText = replaceLevelNumber(newText, unformatted)
        }

        newText = replaceOtherNames(newText, unformatted)
        newText = replaceChromaMessages(newText)
        newText = replaceHypixelUrl(newText)

        if (NewConfig.CHROMA_TYPE) newText = newText.replace("§x", "§" + ColourUtils.scuffedChroma())

        return newText
    }

    private fun replaceNameAndRank(text: String): String {
        if (!NewConfig.CUSTOM_NAME_TOGGLE || NewConfig.CUSTOM_NAME_EDITOR.isEmpty()) return text

        var newText = text

        if (NewConfig.CUSTOM_RANK_TOGGLE && NewConfig.CUSTOM_RANK_EDITOR.isNotEmpty()) newText = replacePlayerRank(newText)

        newText = replacePlayerName(newText)
        return newText
    }

    private fun replacePlayerName(text: String): String {
        return text.replace(username, NewConfig.CUSTOM_NAME_EDITOR.replaceAmpersands() + "§r")
    }

    private fun replacePlayerRank(text: String): String {
        val specificRankRegex = rankRegexList.getOrNull(NewConfig.PLAYER_HYPIXEL_RANK - 1) ?: return text
        val matchResult = specificRankRegex.find(text) ?: return text

        val newRank = NewConfig.CUSTOM_RANK_EDITOR.replaceAmpersands()
        val rankReplaced = text.replaceFirst(matchResult.groupValues[1], "$newRank§r")

        return rankReplaced
    }

    private fun replaceOtherNames(text: String, unformatted: String): String {
        if (!NewConfig.SHARED_RAINBOW_NAMES) return text

        if (rainbowNamesList.isEmpty()) return text

        var newText = text
        for (name in rainbowNamesList) {
            if (name == username) continue
            if (!unformatted.contains(name)) continue

            val newName = "§" + ColourUtils.chooseColour() + "$name§r"
            newText = newText.replace(name, newName)
        }

        return newText
    }

    private fun replaceChromaMessages(text: String): String {
        var newText = text
        if (NewConfig.CHROMA_TEXT_REPLACE.isEmpty()) return text

        val newColour = "§" + ColourUtils.chooseColour()
        val replacementList = NewConfig.CHROMA_TEXT_REPLACE.replaceAmpersands().split(", ")

        for (replacement in replacementList) {
            newText = newText.replace(replacement, "$newColour$replacement§r")
        }

        return newText
    }


    private fun replaceLevelNumber(text: String, unformatted: String): String {
        if (!NewConfig.CUSTOM_LEVEL_TOGGLE || NewConfig.CUSTOM_LEVEL_EDITOR.isEmpty()) return text

        if (!unformatted.contains("] $username")) return text // todo check if this actually helps performance

        val newLvl = NewConfig.CUSTOM_LEVEL_EDITOR.replaceAmpersands()
        return text.replaceFirst(skyblockLevelPattern, "§8[${newLvl}§8]§r")

    }

    private fun replaceHypixelUrl(text: String): String {
        if (NewConfig.CUSTOM_SIDEBAR_URL.isEmpty()) return text
        if (!text.startsWith("§ewww.hypixel")) return text

        // kinda dirty text replacement, would be better practice to edit the scoreboard itself. oh well
        return text.replaceFirst("§ewww.hypixel.ne\uD83C\uDF82§et", NewConfig.CUSTOM_SIDEBAR_URL.replaceAmpersands())
        // idk why the url has an invisible emoji in it
    }

    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (!this::username.isInitialized) {
            username = Minecraft.getMinecraft().thePlayer?.name ?: return
        } // grab the players username when we're sure they're not null
    }


    @SubscribeEvent
    fun onJoinServer(event: ServerJoinEvent) {
        if (NewConfig.PLAYER_HYPIXEL_RANK == 0) {
            UChat.chat(
                OwnwnAddons.PREFIX +
                        "&aYour Hypixel rank isn't set! The custom rank feature will not work without it. &aSet it in &b/owa\n"
            )
        }

        if (!NewConfig.SHARED_RAINBOW_NAMES) return
        if (System.currentTimeMillis() - lastFetchedNames <= 10000) return

        lastFetchedNames = System.currentTimeMillis()
        Multithreading.schedule(fetchOtherNames, 0, TimeUnit.SECONDS)
    }


    var fetchOtherNames = Runnable {
        rainbowNamesList.clear()
        rainbowNamesList.add("OwnwnAddons")

        val rainbowNames = NetworkUtils.getJsonElement(RAINBOW_NAMES_URL)?.asJsonObject?.get("usernames")?.asJsonArray
        if (rainbowNames == null || rainbowNames.size() == 0) {
            UChat.chat(OwnwnAddons.PREFIX + "&cError fetching rainbow usernames.")
            return@Runnable
        }

        for (username in rainbowNames) {
            rainbowNamesList.add(username.asString)
        }

    }


}