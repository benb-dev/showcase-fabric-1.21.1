package com.benbdev.showcasefabric.item;

import com.benbdev.showcasefabric.ShowcaseFabric;
import com.benbdev.showcasefabric.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item TEST_ITEM = registerItem("test_item", new Item(new Item.Settings()));
    public static final Item LIGHTNING_STAFF_ITEM = registerItem("lightning_staff_item", new LightningStaffItem(new Item.Settings()));
    public static final Item SUNDIAL_ITEM = registerItem("sundial_item", new SundialItem(new Item.Settings()));
    public static final Item TEST_STICK_ITEM = registerItem("test_stick_item", new Item(new Item.Settings()));
    public static final Item YOGURT_ITEM = registerItem("yogurt_item", new Item(new Item.Settings().food(FoodComponents.APPLE)));
    public static final Item TOMATO_ITEM = registerItem("tomato_item", new Item(new Item.Settings().food(FoodComponents.APPLE)));
    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
            new AliasedBlockItem(ModBlocks.TOMATO_CROP, new Item.Settings()));
    public static final Item ONION_ITEM = registerItem("onion_item", new AliasedBlockItem(ModBlocks.ONION_CROP, new Item.Settings().food(FoodComponents.CARROT)));
    public static final Item WOODEN_AOE_HOE = registerItem("wooden_aoe_hoe", new IronAOEHoe(ToolMaterials.WOOD, new Item.Settings(), 3));
    public static final Item STONE_AOE_HOE = registerItem("stone_aoe_hoe", new IronAOEHoe(ToolMaterials.STONE, new Item.Settings(), 3));
    public static final Item IRON_AOE_HOE = registerItem("iron_aoe_hoe", new IronAOEHoe(ToolMaterials.IRON, new Item.Settings(), 3));
    public static final Item GOLDEN_AOE_HOE = registerItem("golden_aoe_hoe", new IronAOEHoe(ToolMaterials.GOLD, new Item.Settings(), 5));
    public static final Item DIAMOND_AOE_HOE = registerItem("diamond_aoe_hoe", new IronAOEHoe(ToolMaterials.DIAMOND, new Item.Settings(), 5));
    public static final Item NETHERITE_AOE_HOE = registerItem("netherite_aoe_hoe", new IronAOEHoe(ToolMaterials.NETHERITE, new Item.Settings(), 7));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ShowcaseFabric.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ShowcaseFabric.LOGGER.info("Registering Mod Items for " + ShowcaseFabric.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(TEST_ITEM);
            fabricItemGroupEntries.add(LIGHTNING_STAFF_ITEM);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(YOGURT_ITEM);
        });

    }

}
