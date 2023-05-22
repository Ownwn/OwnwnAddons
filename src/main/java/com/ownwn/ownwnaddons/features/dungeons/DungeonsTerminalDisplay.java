package com.ownwn.ownwnaddons.features.dungeons;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DungeonsTerminalDisplay extends SingleTextHud { // this is trash but fixing it is effort

    public static String returnText = "";
    public static String completedTerms = "(§r§c???§a)";
    public static boolean deviceDone = false;
    public static boolean shouldDisplay = false;



    public DungeonsTerminalDisplay() {
        super("Terminals", false);
    }

    @Override
    public String getText(boolean example) {
        if (example) return "§b" + "(5/7) §aDevice: §aYES";
        return returnText;
    }


    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (!NewConfig.dungeonsTerminalDisplay.isEnabled()) {
            return;
        }

        String msg = event.message.getFormattedText();

        switch (msg) { // messages sent when a gate is destroyed
            case "§r§4[BOSS] Goldor§r§c: §r§cThe little ants have a brain it seems.§r":
            case "§r§4[BOSS] Goldor§r§c: §r§cI will replace that gate with a stronger one!§r":
            case "§r§4[BOSS] Goldor§r§c: §r§cYOUR END IS NEAR!!§r":
            case "§r§4[BOSS] Goldor§r§c: §r§cWho dares trespass into my domain?§r":
                reset();
                break;
            case "§r§aThe Core entrance is opening!§r": // p3 over
                deviceDone = false;
                shouldDisplay = false;
                break;

        }

        if (!msg.contains("activated a") && !msg.contains("completed a")) {
            if (returnText.equals("")) {
                returnText = "§a" + completedTerms +
                        " §rDevice: §4NO";
                return;
            }
        }

        Matcher activatedMatcher = Pattern.compile("§r§.+§r§a activated a .+! (\\(§r§c\\d§r§a/\\d\\))§r").matcher(msg);
        Matcher completedMatcher = Pattern.compile("§r§.+§r§a completed a device! (\\(§r§c\\d§r§a/\\d\\))§r").matcher(msg);
        if (activatedMatcher.find()) {
            completedTerms = activatedMatcher.group(1);
        } else if (completedMatcher.find()) {
            completedTerms = completedMatcher.group(1);
            deviceDone = true;
        }

        
        if (deviceDone) {
            returnText = "§a" + completedTerms +
                    " §rDevice: §aYES";

        } else {
            returnText = "§a" + completedTerms +
                    " §rDevice: §4NO";
        }

    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        shouldDisplay = false;

    }
    public void reset() {
        shouldDisplay = true;
        completedTerms = "(§r§c???§a)";
        deviceDone = false;
    }

    @Override
    public boolean shouldShow() {
        return super.shouldShow() && shouldDisplay;
    }

}