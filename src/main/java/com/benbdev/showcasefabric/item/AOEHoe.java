package com.benbdev.showcasefabric.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AOEHoe extends HoeItem {

    protected int AOESize;

    public AOEHoe(ToolMaterial toolMaterial, Settings settings, int AOESize) {
        super(toolMaterial, settings);
        this.AOESize = AOESize;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos center = context.getBlockPos();
        PlayerEntity player = context.getPlayer();

        boolean tilledAny = false;

        int halfOff = AOESize / 2;

        List<BlockPos> positions = new ArrayList<>(AOESize * AOESize);
        for (int dx = 0; dx < AOESize; dx++) {
            for (int dz = 0; dz < AOESize; dz++) {
                // center the grid: offset ranges from -halfOff ... + (AOESize - halfOff - 1)
                int ox = dx - halfOff;
                int oz = dz - halfOff;
                positions.add(center.add(ox, 0, oz));
            }
        }

        // Sort by distance to center (center first)
        positions.sort(Comparator.comparingDouble(pos -> pos.getSquaredDistance(center)));

        for (BlockPos targetPos : positions) {
            // Build a BlockHitResult centered on the block so canTillFarmland sees a click on that block
            Vec3d hitVec = Vec3d.ofCenter(targetPos);
            BlockHitResult hitResult = new BlockHitResult(hitVec, context.getSide(), targetPos, false);
            ItemUsageContext targetContext = new ItemUsageContext(player, context.getHand(), hitResult);

            // Get the block
            BlockState targetState = world.getBlockState(targetPos);
            Block block = targetState.getBlock();

            if ((block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.DIRT_PATH)
                    && world.getBlockState(targetPos.up()).isAir()) {

                world.setBlockState(targetPos, Blocks.FARMLAND.getDefaultState(), Block.NOTIFY_ALL);
                world.playSound(null, targetPos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                context.getStack().damage(1, player, LivingEntity.getSlotForHand(context.getHand()));
                tilledAny = true;
            }

        }

        return tilledAny ? ActionResult.SUCCESS : ActionResult.PASS;
    }

}
