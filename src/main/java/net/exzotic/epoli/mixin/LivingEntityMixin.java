package net.exzotic.epoli.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.exzotic.epoli.Epoli;
import net.exzotic.epoli.power.ActionOnDeath;
import net.exzotic.epoli.power.PreventSoulSandSlownessPower;
import net.exzotic.epoli.power.RedstonePower;
import net.exzotic.epoli.registry.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow protected float lastDamageTaken;

    // Redstone Power
    /**@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void tick(CallbackInfo ci){
        LivingEntity entity = (LivingEntity)(Object)this;
        for (RedstonePower power : PowerHolderComponent.getPowers(entity, RedstonePower.class)) {
            World world = entity.getWorld();
            BlockPos pos = entity.getBlockPos().down();

            BlockState block = world.getBlockState(pos);

            if(block.isIn(ModBlockTags.REDSTONE_BLOCKS)){
                Epoli.LOGGER.info("Hi!");
                // world.updateNeighborsAlways(pos, null);
                world.updateNeighbor(pos, null, pos);
            }
        }
    }
    **/

    //PreventSoulSandSlownessPower
    @Inject(method = "getVelocityMultiplier", at = @At("HEAD"), cancellable = true)
    private void getVelocityMultiplier(CallbackInfoReturnable<Float> cir){
        LivingEntity entity = (LivingEntity)(Object)this;
        for (PreventSoulSandSlownessPower power : PowerHolderComponent.getPowers(entity, PreventSoulSandSlownessPower.class)) {
            float value = 1;
            cir.setReturnValue(value);
        }
    }
}
