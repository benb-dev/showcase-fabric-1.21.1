package com.benbdev.showcasefabric.block.entity;

import com.benbdev.showcasefabric.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class TomatoEntity extends SnowballEntity {
    public TomatoEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    public TomatoEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.TOMATO_ITEM;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof VillagerEntity ? 10 : 0;
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), i);
    }
}
