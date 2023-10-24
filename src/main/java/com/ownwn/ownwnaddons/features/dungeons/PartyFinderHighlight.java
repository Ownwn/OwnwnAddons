package com.ownwn.ownwnaddons.features.dungeons;

import com.ownwn.ownwnaddons.utils.CheckSlot;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PartyFinderHighlight {
    @SubscribeEvent
    public void onRenderTooltip(ItemTooltipEvent event) {
        if (!CheckSlot.openGuiName.equals("Party Finder")) { // get name of open GUI
            return;
        }

        if (NewConfig.PF_NAME_HIGHLIGHT.isEmpty() && NewConfig.PF_NAME_GOOD.isEmpty()) {
            return;
        } // check this second because it's probably slightly more intensive

        if (!event.toolTip.get(0).endsWith("'s Party§r")) { // check it's a party finder skull thingy
            return;
        }

        String[] highlightNames = NewConfig.PF_NAME_HIGHLIGHT.toLowerCase().split(", ");
        String[] goodNames = NewConfig.PF_NAME_GOOD.toLowerCase().split(", ");


        for (int i = 5; i < event.toolTip.size(); i++) { // start at 5 to skip unnecessary lines
            String tooltipText = event.toolTip.get(i).toLowerCase();

            if (!tooltipText.startsWith("§5§o ") || !tooltipText.endsWith("§b)")) {
                continue;
            }

            if (!NewConfig.PF_NAME_HIGHLIGHT.isEmpty()) {
                for (String name: highlightNames) {

                    if (tooltipText.contains(name)) {
                        String oldText = event.toolTip.get(i);
                        String newText = "§c⚠" + oldText;


                        event.toolTip.add(i, newText); // shove the new text where the old one used to be.
                        event.toolTip.remove(i + 1); // delete the old text that got pushed down one line.

                    }

                }
            }

            if (!NewConfig.PF_NAME_GOOD.isEmpty()) {
                for (String name: goodNames) {
                    if (!tooltipText.contains(name)) {
                        continue;
                    }
                    String oldText = event.toolTip.get(i);
                    String newText = "§a§l★" + oldText;

                    event.toolTip.add(i, newText); // shove the new text where the old one used to be.
                    event.toolTip.remove(i + 1); // delete the old text that got pushed down one line.

                }
            }
        }

    }

}
