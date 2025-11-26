package com.benbdev.showcasefabric.block.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntityTypes {

    public static final EntityType<SnowballEntity> TOMATO = register(
            "tomato",
            EntityType.Builder.<SnowballEntity>create(SnowballEntity::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10)
    );

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, id, type.build(id));
    }

    public static void registerEntityTypes() {

    }
}
