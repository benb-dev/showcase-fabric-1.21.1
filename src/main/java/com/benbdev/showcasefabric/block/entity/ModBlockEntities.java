//package com.benbdev.showcasefabric.block.entity;
//
//import com.benbdev.showcasefabric.ShowcaseFabric;
//import com.benbdev.showcasefabric.block.ModBlocks;
//import net.minecraft.block.entity.BlockEntityType;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//import net.minecraft.util.Identifier;
//
//public class ModBlockEntities {
//    public static BlockEntityType<DayCropBlockEntity> DAY_CROP;
//
//    public static void registerAll() {
//        DAY_CROP = Registry.register(
//                Registries.BLOCK_ENTITY_TYPE,
//                Identifier.of(ShowcaseFabric.MOD_ID, "day_crop"),
//                BlockEntityType.Builder.create(DayCropBlockEntity::new, ModBlocks.DAY_CROP).build(null)
//        );
//    }
//}