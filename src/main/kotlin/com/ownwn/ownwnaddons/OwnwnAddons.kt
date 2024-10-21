package com.ownwn.ownwnaddons

import cc.polyfrost.oneconfig.events.EventManager
import cc.polyfrost.oneconfig.utils.commands.CommandManager
import com.ownwn.ownwnaddons.command.MainCommand
import com.ownwn.ownwnaddons.feature.CustomName
import com.ownwn.ownwnaddons.feature.SecretPrankStuff
import com.ownwn.ownwnaddons.feature.ThirdPersonFOV
import com.ownwn.ownwnaddons.feature.UpdateChecker
import com.ownwn.ownwnaddons.feature.chat.BazaarChatFormatter
import com.ownwn.ownwnaddons.feature.chat.DungeonSpamFilter
import com.ownwn.ownwnaddons.feature.chat.MinigameClickFriend
import com.ownwn.ownwnaddons.feature.chat.TrevorChatFormatter
import com.ownwn.ownwnaddons.feature.dungeons.SecretClickSounds
import com.ownwn.ownwnaddons.feature.dungeons.terminal.TerminalUtil
import com.ownwn.ownwnaddons.util.*
import net.minecraft.client.Minecraft
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.Event


@Mod(modid = OwnwnAddons.MODID, name = OwnwnAddons.NAME, version = OwnwnAddons.VERSION, modLanguageAdapter = "cc.polyfrost.oneconfig.utils.KotlinLanguageAdapter")
object OwnwnAddons {

    const val MODID = "@ID@"
    const val NAME = "@NAME@"
    const val VERSION = "@VER@"

    const val PREFIX = "§5<§dOWA§5>§r "


    const val HELP = ("&9&l➜ OwnwnAddons Help\n"
            + "&9/owa ➡ &bOpens the GUI\n"
            + "&9/owa preview <message> ➡ &bDisplays any message in chat, supports formatting codes"
            + "&9/owa fetchnames ➡ &bRefreshes custom names without rejoining the server"
            + "&9/owa friends ➡ &bDisplays total number of friend requests")


    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent?) {

        Game.mc = Minecraft.getMinecraft()


        registerOneconfigEventBus(Game)
        registerOneconfigEventBus(CustomName)
        registerOneconfigEventBus(UpdateChecker)
        registerOneconfigEventBus(ColourUtils)
        registerOneconfigEventBus(ThirdPersonFOV)
        registerOneconfigEventBus(Config.callTermsDisplay)
        registerOneconfigEventBus(TrevorChatFormatter)
        registerOneconfigEventBus(MinigameClickFriend)
        registerOneconfigEventBus(DungeonSpamFilter)
        registerOneconfigEventBus(BazaarChatFormatter)


        registerForgeEventBus(OnJoinServer)
        registerForgeEventBus(CheckSlot)
        registerForgeEventBus(SecretPrankStuff)
        registerForgeEventBus(SecretClickSounds)
        registerForgeEventBus(TerminalUtil)

        registerCommand(MainCommand)
    }

    fun registerForgeEventBus(obj: Any?) {
        MinecraftForge.EVENT_BUS.register(obj)
    }

    fun registerOneconfigEventBus(obj: Any?) {
        EventManager.INSTANCE.register(obj)
    }

    fun registerCommand(obj: Any?) {
        CommandManager.INSTANCE.registerCommand(obj)
    }

    fun postEvent(event: Event?) {
        EventManager.INSTANCE.post(event)
    }



}