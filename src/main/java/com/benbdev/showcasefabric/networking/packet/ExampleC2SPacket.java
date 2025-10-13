//package com.benbdev.showcasefabric.networking.packet;
//
//import net.fabricmc.fabric.api.networking.v1.PacketSender;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.SpawnReason;
//import net.minecraft.network.PacketByteBuf;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.server.network.ServerPlayNetworkHandler;
//import net.minecraft.server.network.ServerPlayerEntity;
//import net.minecraft.server.world.ServerWorld;
//
//public class ExampleC2SPacket {
//    public static void receive(MinecraftServer server,
//                               ServerPlayerEntity player,
//                               ServerPlayNetworkHandler handler,
//                               PacketByteBuf buf,
//                               PacketSender responseSender) {
//        // Only on Server
//        EntityType.COW.spawn(((ServerWorld) player.getWorld()), null, null
//        );
//    }
//}

package com.benbdev.showcasefabric.networking.packet;

import com.benbdev.showcasefabric.ShowcaseFabric;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ExampleC2SPacket(String message) implements CustomPayload {
    public static final Id<ExampleC2SPacket> ID = new Id<>(Identifier.of(ShowcaseFabric.MOD_ID, "example"));
    public static final PacketCodec<RegistryByteBuf, ExampleC2SPacket> CODEC =
            PacketCodec.of(ExampleC2SPacket::write, ExampleC2SPacket::new);

    public ExampleC2SPacket(RegistryByteBuf buf) {
        this(buf.readString());
    }

    public void write(RegistryByteBuf buf) {
        buf.writeString(message);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
