package com.benbdev.showcasefabric;

import com.benbdev.showcasefabric.item.ModItems;
import com.benbdev.showcasefabric.networking.ModMessages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;

public class ShowcaseFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();

        ClientPlayNetworking.registerGlobalReceiver(SummonLightningS2CPayload.ID, (payload, context) -> {
            ClientWorld world = context.client().world;

            if (world == null) {
                return;
            }

            BlockPos lightningPos = payload.pos();
            LightningEntity entity = EntityType.LIGHTNING_BOLT.create(world);

            if (entity != null) {
                entity.setPosition(lightningPos.getX(), lightningPos.getY(), lightningPos.getZ());
                world.addEntity(entity);
            }
        });

//        ClientPlayNetworking.registerGlobalReceiver(AdvanceTimeC2SPayload.ID, (payload, context) -> {
//            ClientWorld world = context.client().world;
//            if (world == null) {
//                return;
//            }
//            System.out.println("Trying to change time");
//            //world.setTime(world.getTime() + 12000);
//            world.setTimeOfDay(world.getTimeOfDay() + 12000);
//            System.out.println("Tried to change time");
//
//        });

//        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
//            if (!world.isClient()) {
//                return ActionResult.PASS;
//            }
//
//            ItemStack usedItemStack = player.getStackInHand(hand);
//
//            if (usedItemStack.isOf(ModItems.SUNDIAL_ITEM) && hand == Hand.MAIN_HAND) {
//                System.out.println("In EVENT.register..");
//                AdvanceTimeC2SPayload payload = new AdvanceTimeC2SPayload(player.getBlockPos());
//                ClientPlayNetworking.send(payload);
//
//                return ActionResult.SUCCESS;
//            }
//
//            return ActionResult.PASS;
//        });

        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (!world.isClient()) {
                return TypedActionResult.pass(player.getStackInHand(hand));
            }

            ItemStack usedItemStack = player.getStackInHand(hand);

            if (usedItemStack.isOf(ModItems.SUNDIAL_ITEM) && hand == Hand.MAIN_HAND) {
                System.out.println("Right-clicked Sundial!");
                AdvanceTimeC2SPayload payload = new AdvanceTimeC2SPayload(player.getBlockPos());
                ClientPlayNetworking.send(payload);
                return TypedActionResult.success(usedItemStack);
            }

            return TypedActionResult.pass(usedItemStack);
        });
    }
}
