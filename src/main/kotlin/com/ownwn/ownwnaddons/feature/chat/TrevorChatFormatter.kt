package com.ownwn.ownwnaddons.feature.chat

import com.ownwn.ownwnaddons.Config
import com.ownwn.ownwnaddons.util.Utils
import net.minecraft.event.ClickEvent
import net.minecraft.util.ChatComponentText
import net.minecraft.util.ChatStyle
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


object TrevorChatFormatter { // todo test

    private val peltRewardPattern = Regex("§r§aKilling the animal rewarded you §r§5(\\d+) pelts§r§a.§r|§r§aYour mob died randomly, you are rewarded §r§5(\\d+) pelts§r§a.§r")
    private val animalLocationPattern = Regex("§e\\[NPC] Trevor The Trapper§f: §rYou can find your (.+) §fanimal near the (.+).§r")

    private val spamMessages = setOf("§e[NPC] Trevor The Trapper§f: §rCome back soon!§r",
        "§e[NPC] Trevor The Trapper§f: §rAny longer than that and the animal will run away!§r",
        "§r§aReturn to the Trapper soon to get a new animal to hunt!§r")

    private val relatedMessages = setOf("pelt", "trevor", "trapper")

    @SubscribeEvent(priority = EventPriority.LOW)
    fun onChat(event: ClientChatReceivedEvent) {
        if (!Config.trevorChatFormatter) {
            return
        }

        if (!Utils.isInIsland("The Farming Islands")) {
            return
        }

        val msg = event.message.formattedText
        var newMsg = ""



        // check if we care about the message
        if (relatedMessages.none { it in msg.lowercase() }) {
            return
        }


        val peltRewardMatch = peltRewardPattern.find(msg)
        val animalLocationMatch = animalLocationPattern.find(msg)

        if (peltRewardMatch != null) {
            val peltNum = peltRewardMatch.groupValues.getOrNull(1) ?: peltRewardMatch.groupValues[2]

            newMsg = "§a+§5$peltNum§a pelts. §b§l[WARP TO TRAPPER]"

            event.message.setChatStyle(
                ChatStyle().setChatClickEvent(
                    ClickEvent(
                        ClickEvent.Action.RUN_COMMAND,
                        "/warp trapper"
                    )
                )
            )


        } else if (animalLocationMatch != null) {
            newMsg = "§e[Trevor]§f: ${animalLocationMatch.groupValues[1]}§f -> ${animalLocationMatch.groupValues[2]}§f.§r"


            if (msg.contains("§9Desert Settlement") || msg.contains("§9Oasis")) {
                newMsg += " §b§l[WARP]"
                event.message.setChatStyle(
                    ChatStyle().setChatClickEvent(
                        ClickEvent(
                            ClickEvent.Action.RUN_COMMAND,
                            "/warp desert"
                        )
                    )
                )
            }
        }
        if (msg.startsWith("§b§lAccept the trapper's task to hunt the animal?")) {
            newMsg = "§e[Trevor]§f: §a§l[START]"
        }

        if (msg in spamMessages) {
            event.isCanceled = true
            return
        }

        when (msg) {
            "§e[NPC] Trevor The Trapper§f: §rSorry, I don't have any animals for you to hunt.§r" ->
                newMsg = "§e[Trevor]§f: §cStill on cooldown!§r"

            "§e[NPC] Trevor The Trapper§f: §rYou will have 10 minutes to find the mob from when you accept the task.§r" ->
                newMsg = "§e[Trevor]§f: §rLoading...§r"

            "§e[NPC] Trevor The Trapper§f: §rI couldn't locate any animals. Come back in a little bit!§r" ->
                newMsg = "§e[Trevor]§f: §cAn error occurred, try again.§r"

        }

        if (newMsg.isEmpty()) { // avoid screwing with other messages e.g. removing click prompts
            return
        }

        event.message = ChatComponentText(newMsg).setChatStyle(event.message.chatStyle) // replace msg
    }
}