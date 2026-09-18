package net.pomagierko.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.Level.ExplosionInteraction.TNT;

public class MineBlock extends Block {
    public MineBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        pLevel.explode(pEntity, pPos.getCenter().x, pPos.getCenter().y, pPos.getCenter().z, 4f, TNT);
    }
}
