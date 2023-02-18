package com.ownwn.ownwnaddons.features;

import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.Multithreading;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;
import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecretStuff {
    public static boolean doTroll = true;
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
//            return;
        }

        if (location.equals("")) {

            if (msg.contains("§e[NPC] §9Bartender§f: §rI want to make some new brews, do you think you can give me some ingredients to work with?§r")) {
                newMsg = msg.replace("make some new brews", "cook some new drugs");

            } else if (msg.contains("is now clean!")) {

                Matcher plotMatcher = Pattern.compile("§aPlot \\d+ §r§ais now clean!§r").matcher(msg);
                if (plotMatcher.find()) {
                    newMsg = msg.replace("§r§ais now clean!§r", "§r§ais now free of rats!§r");
                }
            } else if (msg.contains("§e[NPC] §aWeaponsmith§f")) {
                if (msg.contains("§rHey! I am looking to make some Hot Potato Books")) {
                    if (doTroll) {
                        if (Math.random() < 0.1) {
                            Multithreading.schedule(thingo, 13L, TimeUnit.SECONDS);
                            Multithreading.schedule(pranked, 20L, TimeUnit.SECONDS);
                        }
                    }
                    newMsg = msg.replace("Hot Potato Books to enhance some of my weapons", "moonshine");
                } else if (msg.contains("smear these potatoes all over my weapons, that'll sure improve 'em")) {
                    newMsg = msg.replace("smear these potatoes all over my weapons, that'll sure improve 'em", "give you a taste when it's ready");
                }
            } else if (msg.contains("§e[NPC] §9Iron Forger§f: §rI've recently got into cooking, yesterday")) {
                newMsg = msg.replace("baking a carrot cake", "cooking drugs");
            }




        } else if (location.equals("hub")) {
            if (!msg.contains("vote")) {
                return;
            }

            Matcher voteMatcher = Pattern.compile("§r§.(.+) §r§enow has §r§..+% §r§eof votes with §r§..+ votes§r§e!§r").matcher(msg);
            Matcher secondVoteMatcher = Pattern.compile("§r§eYou voted for §r§.(.+) §r§ein the §r§bYear \\d+ Elections§r§e!§r").matcher(msg);

            if (voteMatcher.find()) {
                newMsg = msg.replace(voteMatcher.group(1), "§6Ownwn");
            } else if (secondVoteMatcher.find()) {
                newMsg = msg.replace(secondVoteMatcher.group(1), "§6Ownwn");
            }
        }

        if (newMsg.equals("")) {
            return;
        }
        event.message = new ChatComponentText(newMsg).setChatStyle(event.message.getChatStyle());

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void renderNametag(RenderLivingEvent.Specials.Pre<EntityLivingBase> event) { // event type taken from https://github.com/BiscuitDevelopment/SkyblockAddons
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

            if (name.equals("§bSam")) {
                event.entity.setCustomNameTag("§cWaltuh");
            } else if (name.equals("§aVisitor's Logbook")) {
                event.entity.setCustomNameTag("§aChemistry Notebook");
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
                case "§aOringo":
                    event.entity.setCustomNameTag("§aFunny mod dev");
                    break;
            }
        }


    }

    public static Runnable thingo = () -> {
        UChat.chat("&3-----------------------------------------------------" +
                "\n&cYou were kicked from your guild with reason 'L'" +
                "\n&3-----------------------------------------------------");
        doTroll = false;
    };
    public static Runnable pranked = () -> UChat.chat(OwnwnAddons.PREFIX + "&aPranked!");
}
