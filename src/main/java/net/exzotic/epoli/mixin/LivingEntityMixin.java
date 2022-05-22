package net.exzotic.epoli.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.exzotic.epoli.power.DamageLimitPower;
import net.exzotic.epoli.power.PreventSoulSandSlownessPower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    private float damage(float originalValue, DamageSource source, float amount){
        LivingEntity entity = (LivingEntity)(Object)this;
        for (DamageLimitPower power : PowerHolderComponent.getPowers(entity, DamageLimitPower.class)) {
            if(power.doesApply(source, amount)) {
                float limit = power.getLimit();

                if(amount > limit){
                    return limit;
                }
            }
        }
        return originalValue;
    }
}
