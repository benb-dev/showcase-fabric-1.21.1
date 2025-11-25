package com.benbdev.showcasefabric;

import com.benbdev.showcasefabric.block.ModBlocks;
import com.benbdev.showcasefabric.item.ModItems;
import com.benbdev.showcasefabric.villager.ModVillagers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowcaseFabric implements ModInitializer {
	public static final String MOD_ID = "showcasefabric";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.registerModItems();
        ModItems.registerItemGroups();
        ModBlocks.registerModBlocks();
        ModVillagers.registerVillagers();

        PayloadTypeRegistry.playS2C().register(SummonLightningS2CPayload.ID, SummonLightningS2CPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(AdvanceTimeC2SPayload.ID, AdvanceTimeC2SPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SummonLightningC2SPayload.ID, SummonLightningC2SPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(AdvanceTimeC2SPayload.ID, (payload, context) -> {
            System.out.println("HI!");
            ServerWorld world = context.player().getServerWorld();
            world.setTimeOfDay(world.getTimeOfDay() + 12000);
        });

        ServerPlayNetworking.registerGlobalReceiver(SummonLightningC2SPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            ServerWorld world = player.getServerWorld();
            BlockPos pos = payload.pos();

            world.getServer().execute(() -> {
                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                if (lightning != null) {
                    lightning.refreshPositionAfterTeleport(pos.getX(), pos.getY(), pos.getZ());
                    world.spawnEntity(lightning);
                }
            });
        });

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);

            // Only activate if player uses a hoe
            if (!(stack.getItem() instanceof HoeItem)) {
                return ActionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);

            // Only trigger on grass block (change to whatever you want)
            if (state.getBlock() == Blocks.GRASS_BLOCK) {

                // 20% chance
                if (world.random.nextFloat() < 0.20f) {

                    // pick a random seed from your list
                    Item randomSeed = ModItems.SEEDS.get(world.random.nextInt(ModItems.SEEDS.size()));

                    // drop it
                    ItemScatterer.spawn(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(randomSeed));
                }
            }

            return ActionResult.PASS;  // let vanilla finish hoeing the block
        });

	}
}