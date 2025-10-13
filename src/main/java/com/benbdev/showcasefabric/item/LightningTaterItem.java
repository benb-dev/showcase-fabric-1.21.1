package com.benbdev.showcasefabric.item;

import com.benbdev.showcasefabric.SummonLightningS2CPayload;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LightningTaterItem extends Item {
    public LightningTaterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        SummonLightningS2CPayload payload = new SummonLightningS2CPayload(user.getBlockPos());

        for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
            ServerPlayNetworking.send(player, payload);
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}