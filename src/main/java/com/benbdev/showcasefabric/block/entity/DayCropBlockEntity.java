//package com.benbdev.showcasefabric.block.entity;
//
//import com.benbdev.showcasefabric.block.DayCropBlock;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//public class DayCropBlockEntity extends BlockEntity {
//    private long dayPlanted = -1; // uninitialized
//    private int lastAge = 0;
//    private static final int DAYS_PER_STAGE = 2; // grow 1 stage every 2 days
//
//    public DayCropBlockEntity(BlockPos pos, BlockState state) {
//        super(ModBlockEntities.DAY_CROP, pos, state);
//    }
//
//    public static void tick(World world, BlockPos pos, BlockState state, DayCropBlockEntity be) {
//        if (world.isClient) return;
//
//        long currentDay = world.getTimeOfDay() / 24000L;
//
//        // Initialize planting day
//        if (be.dayPlanted == -1) {
//            be.dayPlanted = currentDay;
//            return;
//        }
//
//        // Check how many days passed
//        long daysPassed = currentDay - be.dayPlanted;
//        int expectedStage = (int) (daysPassed / DAYS_PER_STAGE);
//
//        if (expectedStage > be.lastAge && expectedStage <= 7) {
//            be.lastAge = expectedStage;
//            ((DayCropBlock) state.getBlock()).setAge(world, pos, state, expectedStage);
//        }
//    }
//}