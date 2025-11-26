package com.benbdev.showcasefabric.item;

import com.benbdev.showcasefabric.ShowcaseFabric;
import com.benbdev.showcasefabric.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModItems {

    public static final Item TEST_ITEM = registerItem("test_item", new Item(new Item.Settings()));
    public static final Item LIGHTNING_STAFF_ITEM = registerItem("lightning_staff_item", new LightningStaffItem(new Item.Settings()));
    public static final Item SUNDIAL_ITEM = registerItem("sundial_item", new SundialItem(new Item.Settings()));
    public static final Item TEST_STICK_ITEM = registerItem("test_stick_item", new Item(new Item.Settings()));
    public static final Item YOGURT_ITEM = registerItem("yogurt_item", new Item(new Item.Settings().food(FoodComponents.APPLE)));
    public static final Item TOMATO_ITEM = registerItem("tomato_item", new TomatoItem(new Item.Settings().food(FoodComponents.APPLE)));
    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
            new AliasedBlockItem(ModBlocks.TOMATO_CROP, new Item.Settings()));
    public static final Item ONION_ITEM = registerItem("onion_item", new Item(new Item.Settings().food(FoodComponents.CARROT)));
    public static final Item ONION_SEEDS = registerItem("onion_seeds", new AliasedBlockItem(ModBlocks.ONION_CROP, new Item.Settings()));
    public static final Item WOODEN_AOE_HOE = registerItem("wooden_aoe_hoe", new IronAOEHoe(ToolMaterials.WOOD, new Item.Settings(), 3));
    public static final Item STONE_AOE_HOE = registerItem("stone_aoe_hoe", new IronAOEHoe(ToolMaterials.STONE, new Item.Settings(), 3));
    public static final Item IRON_AOE_HOE = registerItem("iron_aoe_hoe", new IronAOEHoe(ToolMaterials.IRON, new Item.Settings(), 3));
    public static final Item GOLDEN_AOE_HOE = registerItem("golden_aoe_hoe", new IronAOEHoe(ToolMaterials.GOLD, new Item.Settings(), 5));
    public static final Item DIAMOND_AOE_HOE = registerItem("diamond_aoe_hoe", new IronAOEHoe(ToolMaterials.DIAMOND, new Item.Settings(), 5));
    public static final Item NETHERITE_AOE_HOE = registerItem("netherite_aoe_hoe", new IronAOEHoe(ToolMaterials.NETHERITE, new Item.Settings(), 7));
    public static final Item FERTILIZER = registerItem("fertilizer", new FertilizerItem(new Item.Settings()));
    public static final Item HEAVY_CREAM_ITEM = registerItem("heavy_cream_item", new Item(new Item.Settings().food(FoodComponents.DRIED_KELP)));
    public static final Item FRESH_MILK_ITEM = registerItem("fresh_milk_item", new Item(new Item.Settings()));
    public static final Item FRESH_WATER_ITEM = registerItem("fresh_water_item", new Item(new Item.Settings()));
    public static final Item CORIANDER_SEEDS = registerItem("coriander_seed_item", new Item(new Item.Settings()));
    public static final Item CUMIN_SEEDS = registerItem("cumin_seed_item", new Item(new Item.Settings()));
    public static final Item CHILI_PEPPER_ITEM = registerItem("chili_pepper_item", new Item(new Item.Settings().food(FoodComponents.MELON_SLICE)));
    public static final Item CHILI_PEPPER_SEEDS = registerItem("chili_pepper_seeds", new AliasedBlockItem(ModBlocks.CHILI_CROP, new Item.Settings()));
    public static final Item SALT_ITEM = registerItem("salt_item", new Item(new Item.Settings()));
    public static final List<Item> SEEDS = List.of(TOMATO_SEEDS,ONION_ITEM, CORIANDER_SEEDS, CUMIN_SEEDS, CHILI_PEPPER_SEEDS);

    public static final Map<Item, Item> SEED_MAP = Map.ofEntries(
            Map.entry(TOMATO_ITEM, TOMATO_SEEDS),
            Map.entry(ONION_ITEM, ONION_SEEDS),
            Map.entry(CHILI_PEPPER_ITEM, CHILI_PEPPER_SEEDS)
    );

    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY =
            RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(ShowcaseFabric.MOD_ID, "item_group"));

    public static ItemGroup CUSTOM_ITEM_GROUP;

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ShowcaseFabric.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ShowcaseFabric.LOGGER.info("Registering Mod Items for " + ShowcaseFabric.MOD_ID);
    }

    public static void registerItemGroups() {

        CUSTOM_ITEM_GROUP = Registry.register(
                Registries.ITEM_GROUP,
                CUSTOM_ITEM_GROUP_KEY,
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(ModItems.NETHERITE_AOE_HOE))
                        .displayName(Text.translatable("itemGroup.showcasefabric"))
                        .entries((displayContext, entries) -> {
                            entries.add(ModItems.ONION_ITEM);
                            entries.add(ModItems.ONION_SEEDS);
                            entries.add(ModItems.TOMATO_ITEM);
                            entries.add(ModItems.TOMATO_SEEDS);
                            entries.add(ModItems.YOGURT_ITEM);
                            entries.add(ModItems.LIGHTNING_STAFF_ITEM);
                            entries.add(ModItems.FERTILIZER);
                            entries.add(ModItems.WOODEN_AOE_HOE);
                            entries.add(ModItems.STONE_AOE_HOE);
                            entries.add(ModItems.IRON_AOE_HOE);
                            entries.add(ModItems.GOLDEN_AOE_HOE);
                            entries.add(ModItems.DIAMOND_AOE_HOE);
                            entries.add(ModItems.NETHERITE_AOE_HOE);
                            entries.add(ModItems.HEAVY_CREAM_ITEM);
                            entries.add(ModItems.FRESH_WATER_ITEM);
                            entries.add(ModItems.FRESH_MILK_ITEM);
                            entries.add(ModItems.CORIANDER_SEEDS);
                            entries.add(ModItems.CUMIN_SEEDS);
                            entries.add(ModItems.CHILI_PEPPER_ITEM);
                            entries.add(ModItems.CHILI_PEPPER_SEEDS);
                            entries.add(ModItems.SALT_ITEM);
                        })
                        .build()
        );

        ShowcaseFabric.LOGGER.info("Registered Item Group for " + ShowcaseFabric.MOD_ID);
    }

}
