package net.pomagierko.tutorialmod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;


import javax.swing.plaf.nimbus.State;
import java.util.List;

public class EntityDetectorItem extends Item {
    private int duration = 20;

    public EntityDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.getCooldowns().addCooldown(this, 40);
        if (!pLevel.isClientSide()) {
            BlockPos positionClicked = pPlayer.blockPosition();
            boolean foundEntities = false;

            AABB searchArea = new AABB(positionClicked).inflate(16);

            List<Entity> nearbyEntities = pLevel.getEntities(pPlayer, searchArea, this::isValuableEntity);

            for (Entity entity : nearbyEntities) {
                outputEntityLocation(entity, pPlayer);
                foundEntities = true;
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.GLOWING, this.duration, 0);
                    livingEntity.addEffect(mobeffectinstance, pPlayer);
                }
            }

            if (!foundEntities) {
                pPlayer.sendSystemMessage(Component.literal("No entities Found!"));
            }
        }

        itemstack.hurtAndBreak(1, pPlayer, p -> p.broadcastBreakEvent(pUsedHand));
        return InteractionResultHolder.success(itemstack);
    }


    private void outputEntityLocation(Entity entity, Player player) {
        player.sendSystemMessage(Component.literal("Found " + entity.getName().getString() + " at " +
                "(" + entity.getBlockX() + ", " + entity.getBlockY() + ", " + entity.getBlockZ() + ")"));
    }

    private boolean isValuableEntity(Entity entity) {
        return entity instanceof SpectralArrow || entity instanceof Skeleton; // example: whatever you want to detect
    }
}
