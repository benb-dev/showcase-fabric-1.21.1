package com.benbdev.showcasefabric.item;

import com.benbdev.showcasefabric.ShowcaseFabric;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item TEST_ITEM = registerItem("test_item", new Item(new Item.Settings()));
    public static final Item LIGHTNING_TATER_ITEM = registerItem("lightning_tater_item", new LightningTaterItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ShowcaseFabric.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ShowcaseFabric.LOGGER.info("Registering Mod Items for " + ShowcaseFabric.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(TEST_ITEM);
            fabricItemGroupEntries.add(LIGHTNING_TATER_ITEM);
        });

    }

}
