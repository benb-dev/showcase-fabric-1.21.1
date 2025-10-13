package com.benbdev.showcasefabric.networking;

import com.benbdev.showcasefabric.ShowcaseFabric;
import com.benbdev.showcasefabric.networking.packet.ExampleC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier ADVANCE_TIME = Identifier.of(ShowcaseFabric.MOD_ID, "advance_time");
    public static final Identifier EXAMPLE_ID = Identifier.of(ShowcaseFabric.MOD_ID, "example");

    public static void registerC2SPackets() {
        //ServerPlayNetworking.registerGlobalReceiver(ADVANCE_TIME, );
        ServerPlayNetworking.registerGlobalReceiver(ExampleC2SPacket.ID, (packet, context) -> {
            ServerPlayerEntity player = context.player();
            String msg = packet.message();
            player.sendMessage(Text.literal("Server received: " + msg), false);
        });
    }

    public static void registerS2CPackets() {

    }
}

