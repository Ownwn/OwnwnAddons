package com.ownwn.ownwnaddons.feature

import com.ownwn.ownwnaddons.Config
import com.ownwn.ownwnaddons.util.Utils
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.item.EntityArmorStand
import net.minecraft.util.ChatComponentText
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.client.event.RenderLivingEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


object SecretPrankStuff {

    private const val BAKER_MESSAGE = "§e[NPC] Baker§f: §rI've recently added a §dNew Year Cake Bag §rto my inventory. Sadly, it's not free! Click me again to open my shop!§r"
    private const val LIBRARIAN_MESSAGE = "§e[NPC] Librarian§f: §rGreetings! Welcome to the Library!§r"

    private val votedRegex = Regex("§r§eYou voted for §r§.(.+) §r§ein the §r§bYear \\d+ Elections§r§e!§r")
    private val voteProportionRegex = Regex("§r§.(.+) §r§enow has §r§..+% §r§eof votes with §r§..+ votes§r§e!§r")

    @SubscribeEvent(priority = EventPriority.LOW)
    fun onChat(event: ClientChatReceivedEvent) {
        if (!Config.secretPrankMessages) {
            return
        }


        val msg = event.message.formattedText
        var newMsg = ""


        if (Utils.isInIsland("Hub")) {

            when {
                msg == BAKER_MESSAGE -> newMsg = "§e[NPC] Baker§f: Let him cook."
                msg == LIBRARIAN_MESSAGE -> newMsg = "§e[NPC] Librarian§f: §rDon't google the scientific name for pig...§r"

                msg.contains("vote") -> {
                    val votedMatch = votedRegex.find(msg)
                    val proportionMatch = voteProportionRegex.find(msg)

                    // replace the candidates name with mine
                    if (votedMatch != null) {
                        newMsg = msg.replace(votedMatch.groupValues[1], "§6Ownwn")
                    } else if (proportionMatch != null) {
                        newMsg = msg.replace(proportionMatch.groupValues[1], "§6Ownwn")
                    }
                }

            }
        }

        if (newMsg.isEmpty()) {
            return
        }

        event.message = ChatComponentText(newMsg).setChatStyle(event.message.chatStyle)
        println("OwnwnAddons: Edited troll chat message")
    }


    @SubscribeEvent(priority = EventPriority.LOW)
    fun renderNametag(event: RenderLivingEvent.Specials.Pre<EntityLivingBase?>) {
        if (!Config.secretPrankMessages) {
            return
        }

        if (Utils.isInIsland("Garden")) {
            if (event.entity !is EntityArmorStand) {
                return
            }

            val name = event.entity.customNameTag ?: return

            when (name) {
                "§bSam" -> event.entity.customNameTag = "§cWaltuh"
                "§aVisitor's Logbook" -> event.entity.customNameTag = "§aChemistry Notebook"
            }
            return
        }

        if (Utils.isInIsland("Hub")) {
            if (event.entity !is EntityArmorStand) {
                return
            }

            val name = event.entity.customNameTag ?: return

            when (name) {
                "§6Bazaar" -> event.entity.customNameTag = "§6The Plug"
                "§cSecurity Sloth" -> event.entity.customNameTag = "§cAverage Ratter"
                "§bSalesman" -> event.entity.customNameTag = "§bScammer"
                "§aYour Island" -> event.entity.customNameTag = "§aThe Backrooms"
                "§5✦ §dElection Room Warp" -> event.entity.customNameTag = "§5✦ §dRigged Election Warp"
            }
        }
    }
}