package com.ownwn.ownwnaddons.utils;

public class ChatListener {
//    public static long runStartTime;
//    public static long runEndTime;
//    public static boolean toggleTimer = false;
//
//    @SubscribeEvent
//    public void onChat(ClientChatReceivedEvent event) {
//
//
//        String player = Minecraft.getMinecraft().thePlayer.getName();
//        String msg = event.message.getFormattedText();
//
//        Matcher leaveMatcher = Pattern.compile("§r§c ☠.+" + player + ".+ disconnected from the Dungeon and became a ghost§r§7.§r").matcher(msg);
//
//
//        if (NewConfig.fragRunTimer.isEnabled()) {
//            if (msg.contains("§aYour active Potion Effects have been paused and stored.")) {
//                if (System.currentTimeMillis() - runEndTime < 60000) {
//                    UChat.chat(OwnwnAddons.PREFIX + "&bFragrun Overview: " +
//                            "\n &aTotal time: " +
//                            (System.currentTimeMillis() - runStartTime) / 1000);
////                    if (NewConfig.fragRunTimer.displayDungeonTime) {
////                        UChat.chat("&aTime in dungeon: " + (runEndTime - runStartTime) / 1000);
////                    }
//                }
//                runStartTime = System.currentTimeMillis();
//                toggleTimer = true;
//
//            }
//            else if (toggleTimer && leaveMatcher.find()) {
//                runEndTime = System.currentTimeMillis();
//                // toggleTimer = false;
//            }
//        }
//    }
}
