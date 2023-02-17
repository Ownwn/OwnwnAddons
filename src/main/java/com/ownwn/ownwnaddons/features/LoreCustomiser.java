package com.ownwn.ownwnaddons.features;

public class LoreCustomiser {
//    @SubscribeEvent(priority = EventPriority.LOW)
//    public void onTooltip(ItemTooltipEvent event) {
//        if (NewConfig.CUSTOM_TEXT_LORE.equals("")) {
//            return;
//        }
//        if (Minecraft.getMinecraft().thePlayer == null) {
//            return;
//        }
//
//        ItemStack item = event.itemStack;
//        if (item == null) {
//            return;
//        }
//        if (!item.hasTagCompound()) {
//            return;
//        }
//        NBTTagCompound compound = item.getSubCompound("ExtraAttributes", false);
//        if (compound == null) {
//            return;
//        }
//
//        if (!compound.hasKey("uuid")) {
//            return;
//        }
//
//        String itemID = String.valueOf(compound.getTag("uuid"));
//
//        String idList = NewConfig.CUSTOM_TEXT_LORE;
//        boolean isDone = false;
//        for (String idMember : idList.split(", ")) {
////            UChat.chat(idMember);
////            UChat.chat(itemID);
//            if (idMember.equalsIgnoreCase(itemID.replace("\"", ""))) {
//                isDone = true;
//                break;
//
//            }
//        }
//        if (!isDone) {
//            return;
//        }
//        //UChat.chat("found it!");
//
//        String itemName = event.toolTip.get(0);
//        event.toolTip.clear();
//        event.toolTip.add(itemName);
//        event.toolTip.add("&&chi &&b this is an item".replace("&&", "§"));
//
//
//
//        boolean go = false;
//        List<String> text = event.toolTip;
//        for (int i = 0; i < text.size(); i++) {
//            if (text.get(i).contains(NewConfig.CUSTOM_TEXT_LORE)) {
//                text.set(i, "§chi");
//                go = true;
//
//            }
//        }
//        if (!go) {
//            return;
//        }
//
//        //UChat.chat("It saw tooltip");
//    }
}
