package com.benbdev.showcasefabric.item;

import com.benbdev.showcasefabric.block.custom.FertilizableCrop;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FertilizerItem extends Item {
    public FertilizerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(pos);

        if (state.getBlock() instanceof FertilizableCrop crop) {
            if (crop.canBeFertilized(state)) {
                crop.fertilize(world, pos, state, player);
                if (!player.isCreative()) {
                    context.getStack().decrement(1);
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
