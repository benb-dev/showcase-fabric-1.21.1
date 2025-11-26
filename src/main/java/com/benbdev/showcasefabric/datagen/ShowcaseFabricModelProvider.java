package com.benbdev.showcasefabric.datagen;

import com.benbdev.showcasefabric.block.ChiliCropBlock;
import com.benbdev.showcasefabric.block.ModBlocks;
import com.benbdev.showcasefabric.block.custom.OnionCropBlock;
import com.benbdev.showcasefabric.block.custom.TomatoCropBlock;
import com.benbdev.showcasefabric.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ShowcaseFabricModelProvider extends FabricModelProvider {
    public ShowcaseFabricModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCrop(ModBlocks.TOMATO_CROP, TomatoCropBlock.AGE, 0, 1, 2, 3, 4);
        blockStateModelGenerator.registerCrop(ModBlocks.ONION_CROP, OnionCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.registerCrop(ModBlocks.CHILI_CROP, ChiliCropBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.TOMATO_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHILI_PEPPER_ITEM, Models.GENERATED);
        //itemModelGenerator.register(ModItems.ONION_ITEM, Models.GENERATED);

    }
}
