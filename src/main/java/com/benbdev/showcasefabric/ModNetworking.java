//package com.benbdev.showcasefabric;
//
//import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
//import net.fabricmc.fabric.api.networking.v1.ClientPlayNetworking;
//import net.minecraft.network.PacketByteBuf;
//import net.minecraft.util.Identifier;
//import net.minecraft.server.network.ServerPlayerEntity;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.util.thread.ThreadExecutor;
//import net.minecraft.util.math.random.Random;
//
//import io.netty.buffer.Unpooled;
//
//public class ModNetworking {
//    public static final Identifier ADVANCE_TIME_PACKET = Identifier.of("showcasefabric", "advance_time");
//
//    // Called from your mod initializer (on server)
//    public static void registerServerReceiver() {
//        ServerPlayNetworking.registerGlobalReceiver(ADVANCE_TIME_PACKET, (server, player, handler, buf, responseSender) -> {
//            // Run on the main server thread
//            server.execute(() -> {
//                ServerWorld world = player.getServerWorld();
//                long newTime = (world.getTimeOfDay() + 12000L) % 24000L;
//                world.setTimeOfDay(newTime);
//            });
//        });
//    }
//
//    // Called client-side
//    public static void sendAdvanceTimePacket() {
//        ClientPlayNetworking.send(ADVANCE_TIME_PACKET, new PacketByteBuf(Unpooled.buffer()));
//    }
//}