package com.ownwn.ownwnaddons.feature

import cc.polyfrost.oneconfig.libs.eventbus.Subscribe
import cc.polyfrost.oneconfig.libs.universal.UChat
import cc.polyfrost.oneconfig.utils.Multithreading
import cc.polyfrost.oneconfig.utils.NetworkUtils
import com.ownwn.ownwnaddons.Config
import com.ownwn.ownwnaddons.OwnwnAddons
import com.ownwn.ownwnaddons.util.ColourUtils
import com.ownwn.ownwnaddons.util.Game
import com.ownwn.ownwnaddons.util.ServerJoinEvent
import com.ownwn.ownwnaddons.util.Utils
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit

object CustomName {
    private val rankRegexList by lazy { // lazy list since username is null until we login
        val name = Game.name
        listOf(
            "(§a\\[VIP]) $name",
            "(§a\\[VIP§6\\+§a]) $name",
            "(§b\\[MVP]) $name",
            "(§b\\[MVP(?:§.){0,3}\\+(?:§.){0,3}]) $name",
            "(§6\\[MVP(?:§.){0,3}\\+\\+(?:§.){0,3}]) $name"
        ).map(::Regex)
    }

    private val skyblockLevelPattern = Regex("§8\\[(?:§r)*§.(\\d{1,3})(?:§r)*§8]")
    var lastFetchedNames = 0L

    private fun String.replaceAmpersands() = replace("&&", "§")

    // use copyonwritearraylist to avoid crash when reading while writing in thread
    private var rainbowNamesList: MutableList<String> = CopyOnWriteArrayList()
    private const val RAINBOW_NAMES_URL: String = "https://raw.githubusercontent.com/Ownwn/OwaData/main/rainbownames.json"

    // store replacements in a hashmap for fast lookup
    private val textReplacementCache: HashMap<String?, String?> = hashMapOf(Pair(null, null))

    fun resetCache() {
        textReplacementCache.clear()
        textReplacementCache[null] = null
    }


    fun replaceAllText(text: String?): String? {

        if (Game.name == null || Game.player == null) return text
        
        textReplacementCache[text]?.let { return it }



        val unformatted = Utils.stripFormatting(text!!)
        var newText = text

        if (unformatted.contains(Game.name!!)) {
            newText = replaceNameAndRank(newText)
            newText = replaceLevelNumber(newText, unformatted)
        }

        newText = replaceOtherNames(newText, unformatted)
        newText = replaceChromaMessages(newText)
        newText = replaceHypixelUrl(newText)

        if (Config.chromaType) newText = newText.replace("§x", "§" + ColourUtils.scuffedChroma())

        textReplacementCache[text] = newText
        return newText
    }

    private fun replaceNameAndRank(text: String): String {
        if (!Config.customNameToggle || Config.customNameEditor.isEmpty()) return text

        var newText = text

        if (Config.customRankToggle && Config.customRankEditor.isNotEmpty()) newText = replacePlayerRank(newText)

        newText = replacePlayerName(newText)
        return newText
    }

    private fun replacePlayerName(text: String): String {
        return text.replace(Game.name!!, Config.customNameEditor.replaceAmpersands() + "§r")
    }

    private fun replacePlayerRank(text: String): String {
        val specificRankRegex = rankRegexList.getOrNull(Config.playerHypixelRank - 1) ?: return text
        val matchResult = specificRankRegex.find(text) ?: return text

        val newRank = Config.customRankEditor.replaceAmpersands()
        val rankReplaced = text.replaceFirst(matchResult.value, "$newRank ${Game.name}")

        return rankReplaced
    }

    private fun replaceOtherNames(text: String, unformatted: String): String {
        if (!Config.sharedRainbowNames) return text

        if (rainbowNamesList.isEmpty()) return text

        var newText = text
        for (name in rainbowNamesList) {
            if (name == Game.name) continue
            if (!unformatted.contains(name)) continue

            val newName = "§" + ColourUtils.chooseColour() + "$name§r"
            newText = newText.replace(name, newName)
        }

        return newText
    }

    private fun replaceChromaMessages(text: String): String {
        var newText = text
        if (Config.chromaTextReplace.isEmpty()) return text

        val newColour = "§" + ColourUtils.chooseColour()
        val replacementList = Config.chromaTextReplace.replaceAmpersands().split(", ")

        for (replacement in replacementList) {
            newText = newText.replace(replacement, "$newColour$replacement§r")
        }

        return newText
    }


    private fun replaceLevelNumber(text: String, unformatted: String): String {
        if (!Config.customLevelToggle || Config.customLevelEditor.isEmpty()) return text

        if (!unformatted.contains("] ${Game.name}")) return text

        val newLvl = Config.customLevelEditor.replaceAmpersands()
        return text.replaceFirst(skyblockLevelPattern, "§8[${newLvl}§8]§r")

    }

    private fun replaceHypixelUrl(text: String): String {
        if (Config.customSidebarUrl.isEmpty()) return text
        if (!text.startsWith("§ewww.hypixel")) return text

        // kinda dirty text replacement, would be better practice to edit the scoreboard itself. oh well
        return text.replaceFirst("§ewww.hypixel.ne\uD83C\uDF82§et", Config.customSidebarUrl.replaceAmpersands())
        // idk why the url has an invisible emoji in it
    }


    @Subscribe
    fun onJoinServer(event: ServerJoinEvent) {

        resetCache()

        if (Config.playerHypixelRank == 0) {
            UChat.chat(
                OwnwnAddons.PREFIX +
                        "&aYour Hypixel rank isn't set! The custom rank feature will not work without it. &aSet it in &b/owa\n"
            )
        }

        if (!Config.sharedRainbowNames) return
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