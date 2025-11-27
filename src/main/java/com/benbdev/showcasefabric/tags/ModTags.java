package com.benbdev.showcasefabric.tags;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Item> FOOD = TagKey.of(
            RegistryKeys.ITEM,
            Identifier.of("showcasefabric", "food")
    );

    public static void registerTags() {}
}
