package com.benbdev.showcasefabric;

import com.benbdev.showcasefabric.block.ModBlocks;
import com.benbdev.showcasefabric.item.ModItems;
import com.benbdev.showcasefabric.villager.ModVillagers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowcaseFabric implements ModInitializer {
	public static final String MOD_ID = "showcasefabric";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.registerModItems();
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

	}
}