package com.benbdev.showcasefabric;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record AdvanceTimeC2SPayload(BlockPos pos) implements CustomPayload {
    public static final Identifier ADVANCE_TIME_PAYLOAD_ID = Identifier.of(ShowcaseFabric.MOD_ID, "advance_time");
    public static final CustomPayload.Id<AdvanceTimeC2SPayload> ID = new CustomPayload.Id<>(ADVANCE_TIME_PAYLOAD_ID);
    public static final PacketCodec<RegistryByteBuf, AdvanceTimeC2SPayload> CODEC =
            PacketCodec.tuple(BlockPos.PACKET_CODEC, AdvanceTimeC2SPayload::pos, AdvanceTimeC2SPayload::new);
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}