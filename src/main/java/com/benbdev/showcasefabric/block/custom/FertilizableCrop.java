package com.benbdev.showcasefabric.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface FertilizableCrop {

    boolean fertilize(World world, BlockPos pos, BlockState state, PlayerEntity player);

    default boolean canBeFertilized(BlockState state) {
        return true;
    }
}