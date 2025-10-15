//package com.benbdev.showcasefabric.block;
//
//import net.minecraft.block.*;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.state.StateManager;
//import net.minecraft.state.property.IntProperty;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.random.Random;
//import net.minecraft.world.BlockView;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//public class DayCropBlock extends CropBlock implements BlockEntityProvider {
//    public static final IntProperty AGE = IntProperty.of("age", 0, 7);
//
//    public DayCropBlock(Settings settings) {
//        super(settings);
//        setDefaultState(getStateManager().getDefaultState().with(AGE, 0));
//    }
//
//    @Override
//    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
//        builder.add(AGE);
//    }
//
//    @Nullable
//    @Override
//    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//        return new DayCropBlockEntity(pos, state);
//    }
//
//    // Disable random tick growth
//    @Override
//    public boolean hasRandomTicks(BlockState state) {
//        return false;
//    }
//
//    @Override
//    public void randomTick(BlockState state, World world, BlockPos pos, Random random) {
//        // intentionally empty – growth handled by block entity
//    }
//
//    public void setAge(World world, BlockPos pos, BlockState state, int age) {
//        if (age > 7) age = 7;
//        world.setBlockState(pos, state.with(AGE, age), Block.NOTIFY_ALL);
//    }
//
//    @Override
//    public BlockRenderType getRenderType(BlockState state) {
//        return BlockRenderType.MODEL;
//    }
//}