package net.exzotic.epoli.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.exzotic.epoli.Epoli;
import net.exzotic.epoli.power.ActionOnDeath;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Unique
    private float cachedDamageAmount;

    @Inject(method = "damage", at = @At("HEAD"))
    private void cachedDamageAmount(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        this.cachedDamageAmount = amount;
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource source, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        for (ActionOnDeath power : PowerHolderComponent.getPowers(entity, ActionOnDeath.class)) {
            if(power.doesApply(source, cachedDamageAmount)) {
                power.executeAction();
            }
        }
    }
}
