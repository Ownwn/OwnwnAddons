package com.ownwn.ownwnaddons.feature.chat

import com.ownwn.ownwnaddons.Config
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object DungeonSpamFilter {
    private val spamMessages = arrayOf(
        "§r§5A shiver runs down your spine...§r",
        "This key can only be used to open §r§a1§r§7 door!§r",
        "§r§aYour active Potion Effects have been paused and stored. They will be restored when you leave Dungeons!",
        "found a §r§dWither Essence§r§f! Everyone gains an extra essence!§r",
        "§r§eYour bone plating reduced the damage you took by",
        "§r§aRefreshing...§r",
        "§r§cYou hear the sound of something opening...§r",
        "§r§c⚠ Storm is enraged! ⚠§r",
        "§r§7A Crypt Wither Skull exploded, hitting you for",
        "§r§cThis chest has already been searched!",
        "§r§cYou do not have the key for this door!§r",
        "§r§cLost Adventurer §r§aused §r§6Dragon's Breath §r§aon you!§r",
        "§r§aAttempting to add you to the party...§r"
    )

    @SubscribeEvent(priority = EventPriority.LOW)
    fun onChat(event: ClientChatReceivedEvent) {
        if (!Config.dungeonSpamFilter) {
            return
        }

        val msg = event.message.formattedText
        if (msg in spamMessages) {
            event.isCanceled = true
        }
    }
}