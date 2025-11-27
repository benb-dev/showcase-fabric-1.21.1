package com.benbdev.showcasefabric.datagen;

import com.benbdev.showcasefabric.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.concurrent.CompletableFuture;

public class ShowcaseFabricItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ShowcaseFabricItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries) {
        super(output, registries);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries) {
        var foodTag = getOrCreateTagBuilder(ModTags.FOOD);

        // Iterate over all registered items
        for (RegistryEntry<Item> entry : registries.getWrapperOrThrow(RegistryKeys.ITEM).streamEntries().toList()) {
            Item item = entry.value();


            // If it is edible, add it to the tag
            if (item.getComponents().contains(DataComponentTypes.FOOD)) {
                entry.getKey().ifPresent(foodTag::add);
            }
        }
    }
}
