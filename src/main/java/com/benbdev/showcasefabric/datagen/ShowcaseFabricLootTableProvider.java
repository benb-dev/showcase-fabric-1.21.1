package com.benbdev.showcasefabric.datagen;

import com.benbdev.showcasefabric.block.ModBlocks;
import com.benbdev.showcasefabric.block.custom.TomatoCropBlock;
import com.benbdev.showcasefabric.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ShowcaseFabricLootTableProvider extends FabricBlockLootTableProvider {
    public ShowcaseFabricLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.TEST_BLOCK);

        BlockStatePropertyLootCondition.Builder builder = BlockStatePropertyLootCondition.builder(ModBlocks.TOMATO_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(TomatoCropBlock.AGE, TomatoCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.TOMATO_CROP, this.cropDrops(ModBlocks.TOMATO_CROP, ModItems.TOMATO_ITEM, ModItems.TOMATO_SEEDS, builder));
        this.addDrop(ModBlocks.ONION_CROP, this.cropDrops(ModBlocks.ONION_CROP, ModItems.ONION_ITEM, ModItems.ONION_ITEM, builder));
        this.addDrop(ModBlocks.CHILI_CROP, this.cropDrops(ModBlocks.CHILI_CROP, ModItems.CHILI_PEPPER_ITEM, ModItems.CHILI_PEPPER_SEEDS, builder));


    }
}