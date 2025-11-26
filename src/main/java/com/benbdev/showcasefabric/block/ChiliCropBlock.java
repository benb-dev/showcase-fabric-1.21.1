package com.benbdev.showcasefabric.block;

import com.benbdev.showcasefabric.block.custom.FertilizableCrop;
import com.benbdev.showcasefabric.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class ChiliCropBlock extends CropBlock implements FertilizableCrop {
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = IntProperty.of("age", 0, 3);
    public static final BooleanProperty FERTILIZED = BooleanProperty.of("fertilized");

    public ChiliCropBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(AGE, 0)
                .with(FERTILIZED, false));
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.CHILI_PEPPER_SEEDS;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(FERTILIZED);
    }

    @Override
    public boolean fertilize(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(FERTILIZED)) {
            return false;
        }
        world.setBlockState(pos, state.with(FERTILIZED, true), Block.NOTIFY_ALL);
        world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, pos, 15);
        //world.addParticle(ParticleTypes.CRIT, pos.getX(), pos.getY(), pos.getZ(),0,0,0);
        return true;
    }

    @Override
    public boolean canBeFertilized(BlockState state) {
        return !state.get(FERTILIZED);
    }
}
