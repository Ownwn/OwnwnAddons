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

        if (NewConfig.PF_NAME_HIGHLIGHT.equals("") && NewConfig.PF_NAME_GOOD.equals("")) {
            return;
        } // check this second because it's probably slightly more intensive

        if (!event.toolTip.get(0).endsWith("'s Party§r")) { // check it's a party finder skull thingy
            return;
        }

        for (int i = 5; i < event.toolTip.size(); i++) {
            String tooltipText = event.toolTip.get(i).toLowerCase();

            if (!tooltipText.startsWith("§5§o ") || !tooltipText.endsWith("§b)")) {
                continue;
            }

            if (!NewConfig.PF_NAME_HIGHLIGHT.equals("")) {
                for (String name: NewConfig.PF_NAME_HIGHLIGHT.toLowerCase().split(", ")) {

                    if (tooltipText.contains(name)) {
                        String oldText = event.toolTip.get(i);
                        String newText = "§c⚠" + oldText;


                        event.toolTip.add(i, newText); // shove the new text where the old one used to be.
                        event.toolTip.remove(i + 1); // delete the old text that got pushed down one line.

                    }

                }
            }

            if (!NewConfig.PF_NAME_GOOD.equals("")) {
                for (String name: NewConfig.PF_NAME_GOOD.toLowerCase().split(", ")) {
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
