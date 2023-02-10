package com.ownwn.ownwnaddons.features;

public class OwaDevMode {
//    public static long lastOne = 0;
//    @SubscribeEvent
//    public void onTick(TickEvent.ClientTickEvent event) {
//        if (!Owa.devMode) {
//            return;
//        }
//        if (System.currentTimeMillis() - lastOne < 2000) {
//            return;
//        }
//        lastOne = System.currentTimeMillis();
//        Owa.devMode = false;
//        IChatComponent openModsDirectory = new ChatComponentText("§8[§717§8] §7InvWipedIrl: can i buy a set or something").setChatStyle(new ChatStyle()
//                .setColor(EnumChatFormatting.GREEN).setBold(true)
//                .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/say hi"))
//                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(EnumChatFormatting.YELLOW + "Open mods directory with command " + EnumChatFormatting.GOLD + "/moo directory\n➜ Click to open mods directory"))));
//        MinecraftForge.EVENT_BUS.post(new ClientChatReceivedEvent((byte) 1, openModsDirectory));
//    }
//
//    @SubscribeEvent(priority = EventPriority.LOW)
//    public void onChat(ClientChatReceivedEvent event) {
//        if (!event.message.getFormattedText().contains("can i buy")) {
//            return;
//        }
//        Minecraft.getMinecraft().thePlayer.addChatMessage(event.message);
//    }
////    @Subscribe
////    public void onPacket(SendPacketEvent event) {
////        if (!Owa.devMode) {
////            return;
////        }
////        String packet = event.packet.toString();
////        if (packet.contains("PacketEntityHead") || packet.contains("PacketEntityRelMove") || packet.contains("PacketKeepAlive") || packet.contains("PacketTimeUpdate") || packet.contains("PacketEntityTeleport") || packet.contains("BlockChange") || packet.contains("PacketChat") || packet.contains("PacketChunkData")) {
////            return;
////        }
////        if (packet.contains("minecraft.network.play.client.C03PacketPlayer@") || packet.contains("PacketPlayerPosition") || packet.contains("PacketPlayerLook")) {
////            return;
////        }
////
////        UChat.chat(event.packet);
////    }
}
