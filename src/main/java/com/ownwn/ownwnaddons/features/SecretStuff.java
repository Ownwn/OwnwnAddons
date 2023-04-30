package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecretStuff {

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChat(ClientChatReceivedEvent event) {
        if (!NewConfig.FUNNY_STUFF_SECRET) {
            return;
        }


        String msg = event.message.getFormattedText();
        String newMsg = "";

        String location = "";
        try {
            location = LocrawUtil.INSTANCE.getLocrawInfo().getGameMode();
        } catch (Exception ignored) {
            return;
        }

        if (location.equals("hub")) {


            if (msg.equals("§e[NPC] Baker§f: §rI've recently added a §dNew Year Cake Bag §rto my inventory. Sadly, it's not free! Click me again to open my shop!§r")) {
                newMsg = "§e[NPC] Baker§f: Let him cook.";

            } else if (msg.contains("vote")) {
                Matcher voteMatcher = Pattern.compile("§r§.(.+) §r§enow has §r§..+% §r§eof votes with §r§..+ votes§r§e!§r").matcher(msg);
                Matcher secondVoteMatcher = Pattern.compile("§r§eYou voted for §r§.(.+) §r§ein the §r§bYear \\d+ Elections§r§e!§r").matcher(msg);
                if (voteMatcher.find()) {
                    newMsg = msg.replace(voteMatcher.group(1), "§6Ownwn");
                } else if (secondVoteMatcher.find()) {
                    newMsg = msg.replace(secondVoteMatcher.group(1), "§6Ownwn");
                }

            } else if (msg.equals("§e[NPC] Librarian§f: §rGreetings! Welcome to the Library!§r")) {
                newMsg = "§e[NPC] Librarian§f: §rDon't google the scientific name for pig...§r";
            }
        }

        if (newMsg.equals("")) {
            return;
        }
        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle());

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void renderNametag(RenderLivingEvent.Specials.Pre<EntityLivingBase> event) {
        if (!NewConfig.FUNNY_STUFF_SECRET) {
            return;
        }

        String location = "";
        try {
            location = LocrawUtil.INSTANCE.getLocrawInfo().getGameMode();
        } catch (Exception ignored) {
            return;
        }

        if (location.equals("garden")) {
            if (!(event.entity instanceof EntityArmorStand)) {
                return;
            }
            if (event.entity.getCustomNameTag() == null) {
                return;
            }

            String name = event.entity.getCustomNameTag();

            switch (name) {
                case "§bSam":
                    event.entity.setCustomNameTag("§cWaltuh");
                    break;
                case "§aVisitor's Logbook":
                    event.entity.setCustomNameTag("§aChemistry Notebook");
                    break;
            }


        } else if (location.equals("hub")) {
            if (!(event.entity instanceof EntityArmorStand)) {
                return;
            }
            if (event.entity.getCustomNameTag() == null) {
                return;
            }

            String name = event.entity.getCustomNameTag();

            switch (name) {
                case "§6Bazaar":
                    event.entity.setCustomNameTag("§6The Plug");
                    break;
                case "§cSecurity Sloth":
                    event.entity.setCustomNameTag("§cAverage Ratter");
                    break;
                case "§bSalesman":
                    event.entity.setCustomNameTag("§bScammer");
                    break;
                case "§aYour Island":
                    event.entity.setCustomNameTag("§aThe Backrooms");
                    break;
                case "§5✦ §dElection Room Warp":
                    event.entity.setCustomNameTag("§5✦ §dRigged Election Warp");
                    break;
            }
        }
    }
}
