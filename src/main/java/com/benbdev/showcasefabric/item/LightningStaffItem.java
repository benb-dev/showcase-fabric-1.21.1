package com.benbdev.showcasefabric.item;

import com.benbdev.showcasefabric.SummonLightningC2SPayload;
import com.benbdev.showcasefabric.SummonLightningS2CPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
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
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LightningStaffItem extends Item {
    public LightningStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (world.isClient()) {
            // Perform a raycast up to 100 blocks
            HitResult hit = user.raycast(100.0, 0.0F, false);
            BlockPos targetPos = null;

            if (hit.getType() == HitResult.Type.BLOCK) {
                targetPos = ((BlockHitResult) hit).getBlockPos();
            } else if (hit.getType() == HitResult.Type.MISS) {
                Vec3d end = user.getCameraPosVec(0).add(user.getRotationVec(0).multiply(100.0));
                targetPos = BlockPos.ofFloored(end);
            }

            if (targetPos != null) {
                SummonLightningC2SPayload payload = new SummonLightningC2SPayload(targetPos);
                ClientPlayNetworking.send(payload);
            }

            return TypedActionResult.success(user.getStackInHand(hand));
        }

        return TypedActionResult.pass(user.getStackInHand(hand));


//        if (world.isClient()) {
//            return TypedActionResult.pass(user.getStackInHand(hand));
//        }
//
//
//        SummonLightningC2SPayload payload = new SummonLightningC2SPayload(user.getBlockPos());
//        ClientPlayNetworking.send(payload);
////        SummonLightningS2CPayload payload = new SummonLightningS2CPayload(user.getBlockPos());
////
////        for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
////            ServerPlayNetworking.send(player, payload);
////        }
//
//        return TypedActionResult.success(user.getStackInHand(hand));
    }
}