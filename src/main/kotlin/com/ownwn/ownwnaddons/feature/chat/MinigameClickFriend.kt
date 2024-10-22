package com.ownwn.ownwnaddons.feature.chat

import cc.polyfrost.oneconfig.events.event.ChatSendEvent
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe
import cc.polyfrost.oneconfig.libs.universal.UChat
import com.ownwn.ownwnaddons.Config
import com.ownwn.ownwnaddons.OwnwnAddons
import com.ownwn.ownwnaddons.util.Game
import com.ownwn.ownwnaddons.util.Utils
import net.minecraft.event.ClickEvent
import net.minecraft.event.HoverEvent
import net.minecraft.util.ChatComponentText
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


object MinigameClickFriend {
    private var onCooldown: Boolean = false

    // The list of people you've friended resets when you restart your game, but that's fine IMO
    private var requestList: MutableList<String> = ArrayList()

    private val joinedRegex = Regex("^(\\w{1,16}) has joined \\(\\d+/\\d+\\)!")




    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if (!Config.minigameClickFriend) {
            return
        }

        val msg: String = Utils.stripFormatting(event.message.formattedText)

        when {
            msg == "You can only have 10 active requests simultaneously!" -> onCooldown = true
            msg.startsWith("Your friend request to") && msg.endsWith("has expired.") -> onCooldown = false
            msg.startsWith("You are now friends with ") -> onCooldown = false
        }

        if (!msg.endsWith(")!") || msg.contains(Game.name!!)) {
            return
        }

        val match = joinedRegex.find(msg) ?: run {
            return
        }

        val joinedName = match.groupValues[1]
        if (joinedName in requestList) {
            return
        }

        event.message.setChatStyle(
            event.message.chatStyle
                .setChatClickEvent(ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f add $joinedName"))
                .setChatHoverEvent(
                    HoverEvent(
                        HoverEvent.Action.SHOW_TEXT,
                        ChatComponentText("§r§aClick to add §b$joinedName §aas a friend!§r")
                    )
                )
        )
    }


    @Subscribe
    fun onSpeak(event: ChatSendEvent) {
        if (!Config.minigameClickFriend) {
            return
        }

        if (!event.message.startsWith("/f add ")) {
            return
        }

        if (onCooldown) {
            event.isCancelled = true
            UChat.chat(OwnwnAddons.PREFIX + "&cYou have reached the maximum of 10 requests!")
            return
        }

        val playerName = event.message.replace("/f add ", "")
        if (requestList.contains(playerName)) {
            event.isCancelled = true
            UChat.chat(OwnwnAddons.PREFIX + "&cYou have already friended that player!")
        }
        requestList.add(playerName)
    }
}