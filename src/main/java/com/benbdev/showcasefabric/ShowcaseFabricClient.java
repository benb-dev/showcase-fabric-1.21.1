package com.benbdev.showcasefabric;

import com.benbdev.showcasefabric.block.ModBlocks;
import com.benbdev.showcasefabric.networking.ModMessages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;

public class ShowcaseFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.TOMATO_CROP);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.CHILI_CROP);

//        ClientPlayNetworking.registerGlobalReceiver(SummonLightningS2CPayload.ID, (payload, context) -> {
//            ClientWorld world = context.client().world;
//
//            if (world == null) {
//                return;
//            }
//
//            BlockPos lightningPos = payload.pos();
//            LightningEntity entity = EntityType.LIGHTNING_BOLT.create(world);
//
//            if (entity != null) {
//                entity.setPosition(lightningPos.getX(), lightningPos.getY(), lightningPos.getZ());
//                world.addEntity(entity);
//            }
//        });
    }
}
