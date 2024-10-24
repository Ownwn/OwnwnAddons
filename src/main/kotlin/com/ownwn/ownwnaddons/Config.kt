package com.ownwn.ownwnaddons

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.*
import cc.polyfrost.oneconfig.config.core.OneKeyBind
import cc.polyfrost.oneconfig.config.data.InfoType
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
import cc.polyfrost.oneconfig.libs.universal.UKeyboard
import com.ownwn.ownwnaddons.feature.CustomName
import com.ownwn.ownwnaddons.feature.OnScreenTimer
import com.ownwn.ownwnaddons.feature.dungeons.terminal.CallTerminalsDisplay
import com.ownwn.ownwnaddons.feature.dungeons.terminal.TerminalHud


object Config : Config(
    Mod(OwnwnAddons.NAME, ModType.SKYBLOCK, "/ownwn.png"),
    "ownwnaddons.json",
    true,
    false) {


    @Dropdown(
        name = "Hypixel Rank",
        options = ["Default", "VIP", "VIP+", "MVP", "MVP+", "MVP++"],
        description = "Your hypixel rank."
    )
    var playerHypixelRank: Int = 0

    @Switch(name = "Check for updates",
        description = "Should the mod check for updates when you join Hypixel?"
    )
    var checkForUpdates: Boolean = true

    @Text(
        name = "Dungeons Secret Click Sound",
        description = "Customize the sound that plays when you click a secret in dungeons e.g. random.successful_hit. Leave blank for no sound",
        category = "Features",
        subcategory = "Dungeons"
    )
    var secretClickSound: String = "random.break"

    @Slider(
        name = "Click Sound Volume",
        description = "Change the volume of the dungeons secret click sound",
        category = "Features",
        subcategory = "Dungeons",
        min = 1f,
        max = 10.01f,
        step = 1
    )
    var secretClickVolume: Float = 5f

    @Slider(
        name = "Click Sound Pitch",
        description = "Change the pitch of the dungeons secret click sound",
        category = "Features",
        subcategory = "Dungeons",
        min = 5f,
        max = 20f,
        step = 1
    )
    var secretClickPitch: Float = 10f

    @Switch(
        name = "F7 Terminal Overview",
        description = "Displays how many terminals each player did in P3. Idea by Soopy",
        category = "Features",
        subcategory = "Dungeons"
    )
    var f7TerminalOverview: Boolean = true

    @Dropdown(
        name = "Terminal Overview Style",
        options = ["Old Soopy", "Improved Soopy", "Experimental"],
        category = "Features",
        subcategory = "Dungeons"
    )
    var terminalOverviewStyle: Int = 1

    @Switch(
        name = "Funny Stuff",
        description = "Changes some text and adds a couple of jokes to the mod (nothing malicious)"
    )
    var secretPrankMessages: Boolean = false

    @Switch(
        name = "Onboarding"
    )
    var onboardingFirstTime: Boolean = true

    @Switch(
        name = "Custom Name Toggle",
        category = "Custom Name"
    )
    var customNameToggle: Boolean = false

    @Text(
        name = "Custom Name",
        description = "Allows you to customize your name. Leave blank for default name.",
        category = "Custom Name"
    )
    var customNameEditor: String = "&&cJoe"

    @Switch(
        name = "Custom Rank Toggle",
        category = "Custom Name"
    )
    var customRankToggle: Boolean = false

    @Text(
        name = "Custom Rank",
        description = "Allows you to customize your rank. Leave blank for default rank.",
        category = "Custom Name"
    )
    var customRankEditor: String = "&&9[Rank]"

    @Switch(
        name = "Custom Skyblock Level",
        category = "Custom Name"
    )
    var customLevelToggle: Boolean = false

    @Text(
        name = "Custom Skyblock Level",
        description = "Allows you to customize your level. Leave blank for default level.",
        category = "Custom Name"
    )
    var customLevelEditor: String = "&&44&&c2&&e0"


    @Info(
        text = "Use \"&&\" for colour codes. Also try out &&x",
        type = InfoType.INFO,
        category = "Custom Name",
        size = 2
    )
    var ignored2: Boolean = false

    @Slider(
        name = "Scuffed Chroma Delay (ms)",
        description = "Change the speed of the scuffed chroma.",
        min = 0f,
        max = 500f,
        step = 50
    )
    var scuffedChromaSpeed: Int = 100

    @Switch(
        name = "Turn other user's names rainbow",
        description = "Makes the names of other mod users rainbow.",
        category = "Custom Name"
    )
    var sharedRainbowNames: Boolean = true

    @Switch(
        name = "Trevor Chat Cleanup",
        description = "Cleans up the chat messages sent by Trevor the Trapper",
        category = "Chat Replacers",
        subcategory = "Chat Cleanup"
    )
    var trevorChatFormatter: Boolean = false

    @Switch(
        name = "Bazaar Chat Cleanup",
        description = "Cleans up the chat messages sent by the Bazaar",
        category = "Chat Replacers",
        subcategory = "Chat Cleanup"
    )
    var bazaarSpamFilter: Boolean = false

    @Text(
        name = "Chroma Text",
        description = "Allows you to colour specific text with chroma.",
        category = "Chat Replacers",
        multiline = true
    )
    var chromaTextReplace: String = "Bits:, Piggy:"

    @DualOption(
        name = "Chroma Type",
        description = "Change the type of chroma that the mod uses.",
        left = "SBA Chroma",
        right = "Scuffed Chroma"
    )
    var chromaType: Boolean = false

    @Switch(
        name = "Always Render SBA Chroma",
        description = "Makes SBA chroma render everywhere, not just on Skyblock"
    )
    var forceSbaChroma: Boolean = false

    @Info(
        text = "Seperate the text to be replaced with commas. Use && for colour codes.",
        type = InfoType.INFO,
        category = "Chat Replacers",
        size = 2
    )
    var ignored: Boolean = false

    @Text(
        name = "Custom Sidebar URL",
        description = "Replaces the hypixel URL with something else",
        category = "Chat Replacers"
    )
    var customSidebarUrl: String = ""

    @Switch(
        name = "Dungeons Chat Cleanup",
        description = "Hides some useless/spammy messages in dungeons",
        category = "Chat Replacers",
        subcategory = "Chat Cleanup"
    )
    var dungeonSpamFilter: Boolean = false

    @Switch(
        name = "Disable reverse third person in dungeons",
        description = "Stops you switching to reverse third person in dungeons",
        category = "Features",
        subcategory = "Perspective"
    )
    var dungeonsThirdPerson: Boolean = false

    @Switch(
        name = "Third Person FOV changer",
        description = "Changes your FOV when you enter third person",
        category = "Features",
        subcategory = "Perspective"
    )
    var thirdPersonFOV: Boolean = false

    @Slider(
        name = "Third Person FOV modifier",
        category = "Features",
        subcategory = "Perspective",
        min = 30f,
        max = 140f,
        step = 1
    )
    var fovModifier: Float = 100f

    @Switch(
        name = "Prevent Deleting Souls from Necro-Sword",
        description = "Stops you removing souls from the necromancer sword",
        category = "Features",
        subcategory = "Misc"
    )
    var stopNecromancerDelete: Boolean = false

    @KeyBind(
        name = "Toggle Timer",
        description = "Toggles an on-screen timer. Make sure the HUD is enabled",
        category = "Features",
        subcategory = "Misc"
    )
    var startTimerKey: OneKeyBind = OneKeyBind(UKeyboard.KEY_NONE)

    @Button(
        name = "Reset Timer",
        text = "Reset",
        category = "Features",
        subcategory = "Misc"
    )
    var resetTimer: Runnable = Runnable { onScreenTimer.resetTimer() }

    @HUD(
        name = "F7 Terminal Display",
        category = "HUDS",
        subcategory = "Dungeons"
    )
    var terminalHud: TerminalHud = TerminalHud()

    @HUD(
        name = "F7 terms calls",
        category = "HUDS",
        subcategory = "Dungeons"
    )
    var callTermsDisplay: CallTerminalsDisplay = CallTerminalsDisplay()

    @HUD(
        name = "On Screen Timer",
        category = "HUDS"
    )
    var onScreenTimer: OnScreenTimer = OnScreenTimer()

    @Info(
        text = "The Alternate Island feature is very broken, may cause visual issues. Try refreshing your chunks",
        type = InfoType.WARNING,
        category = "Features",
        subcategory = "Misc",
        size = 2
    )
    var ignored3: Boolean = false

    @Switch(
        name = "Alternate Hub (W.I.P)",
        description = "Changes some blocks in the Hub island.",
        category = "Features",
        subcategory = "Misc"
    )
    var worldReskinHub: Boolean = false

    @Switch(
        name = "Alternate NEU Lowest BIN",
        description = "Changes the look of NEU's lowest BIN tooltip.",
        category = "Features",
        subcategory = "Misc"
    )
    var changeNeuTooltip: Boolean = false

    @Switch(
        name = "Click Join Messages to Add Friend",
        description = "Makes Skywars/Bedwars player join messages clickable to add them as friends",
        category = "Non-Skyblock"
    )
    var minigameClickFriend: Boolean = false

    init {
        initialize()

        listOf("customNameEditor", "customRankEditor", "customLevelEditor").forEach {
            addListener(it, { CustomName.resetCache() })
        }

        addDependency("customNameEditor", "customNameToggle")
        addDependency("customRankEditor", "customRankToggle")
        addDependency("customRankToggle", "customNameToggle")
        addDependency("customRankToggle", "playerHypixelRank") { playerHypixelRank != 0 }

        addDependency("customLevelEditor", "customLevelToggle")

        addDependency("fovModifier", "thirdPersonFOV")

        registerKeyBind(startTimerKey) { onScreenTimer.toggleTimer() }

    }

}