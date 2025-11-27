package com.benbdev.showcasefabric.block;

import com.benbdev.showcasefabric.ShowcaseFabric;
import com.benbdev.showcasefabric.block.custom.ComposterFertilizerBlock;
import com.benbdev.showcasefabric.block.custom.OnionCropBlock;
import com.benbdev.showcasefabric.block.custom.TomatoCropBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block TEST_BLOCK = registerBlock("test_block", new Block(AbstractBlock.Settings
            .create()
            .strength(4f)
            .requiresTool()
            .sounds(BlockSoundGroup.BAMBOO_WOOD)));

    public static final Block TOMATO_CROP = registerBlockWithoutBlockItem("tomato_crop", new TomatoCropBlock(AbstractBlock.Settings
            .create()
            .noCollision()
            .ticksRandomly()
            .breakInstantly()
            .sounds(BlockSoundGroup.CROP)
            .pistonBehavior(PistonBehavior.DESTROY)
            .mapColor(MapColor.DARK_GREEN)));

    public static final Block ONION_CROP = registerBlockWithoutBlockItem("onion_crop", new OnionCropBlock(AbstractBlock.Settings
            .create()
            .noCollision()
            .ticksRandomly()
            .breakInstantly()
            .sounds(BlockSoundGroup.CROP)
            .pistonBehavior(PistonBehavior.DESTROY)
            .mapColor(MapColor.DARK_GREEN)));

    public static final Block CHILI_CROP = registerBlockWithoutBlockItem("chili_crop", new ChiliCropBlock(AbstractBlock.Settings
            .create()
            .noCollision()
            .ticksRandomly()
            .breakInstantly()
            .sounds(BlockSoundGroup.CROP)
            .pistonBehavior(PistonBehavior.DESTROY)
            .mapColor(MapColor.DARK_GREEN)));

    public static final Block COMPOSTER_FERTILIZER = registerBlock("composter_fertilizer", new ComposterFertilizerBlock(AbstractBlock.Settings.create()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(ShowcaseFabric.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(ShowcaseFabric.MOD_ID, name), block);
    }

    public static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(ShowcaseFabric.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        ShowcaseFabric.LOGGER.info("Registering Mod Blocks for " + ShowcaseFabric.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(TEST_BLOCK);
        });
    }

}
