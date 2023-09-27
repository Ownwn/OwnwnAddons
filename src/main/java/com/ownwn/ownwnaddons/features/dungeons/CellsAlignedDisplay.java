package com.ownwn.ownwnaddons.features.dungeons;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import com.ownwn.ownwnaddons.utils.Utils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CellsAlignedDisplay extends SingleTextHud {

    public CellsAlignedDisplay() { super("Aligned", false);}
    public static long alignmentTime = 0;


    @Override
    protected String getText(boolean example) {
        if (example) {
            return "5s";
        }



        float timeLeft;
        long now = System.currentTimeMillis();

        if (now >= alignmentTime) {
            return "READY";
        }


        timeLeft = (alignmentTime - System.currentTimeMillis()) / 1000f;

        return Utils.roundNum(timeLeft, 1) + "s";

    }

    @Override
    public boolean shouldShow() {
        return super.shouldShow() && Utils.checkLocMap("Dungeon");
//        return super.shouldShow();
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String text = event.message.getFormattedText();
        String unformatted = Utils.stripFormatting(text);
        /* i love hypixel inconsistencies
        §r§eYou §r§aaligned §r§eyourself!§r
        §r§eYou aligned §r§a4 §r§eother players!§r
         */
        if (!unformatted.startsWith("You aligned ") && !text.endsWith("§ecasted §aCells Alignment §eon you!§r")) {
            return;
        }
        alignmentTime = System.currentTimeMillis() + 6000; // 6 seconds of aligned
    }
}
