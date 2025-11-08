package com.benbdev.showcasefabric.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AOEHoe extends HoeItem {

    protected int AOESize;

    public AOEHoe(ToolMaterial toolMaterial, Settings settings, int AOESize) {
        super(toolMaterial, settings);
        this.AOESize = AOESize;
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        boolean tilled = false;



        for (int dx = 0; dx < AOESize; dx++) {
            for (int dz = 0; dz < AOESize; dz++) {
                BlockPos targetPos = pos.add(dx, 0, dz);
                BlockState targetState = world.getBlockState(targetPos);

                if (targetState.isOf(Blocks.GRASS_BLOCK) ||
                        targetState.isOf(Blocks.DIRT) ||
                        targetState.isOf(Blocks.DIRT_PATH)) {

                    BlockState farmland = Blocks.FARMLAND.getDefaultState();

                    if (world.getBlockState(targetPos.up()).isAir()) {
                        world.setBlockState(targetPos, farmland, Block.NOTIFY_ALL);
                        tilled = true;
                    }
                }
            }
        }

        if (tilled) {
            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            context.getStack().damage(1, player, LivingEntity.getSlotForHand(context.getHand()));
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

}
