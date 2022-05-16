package net.exzotic.epoli.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.exzotic.epoli.power.PassivePower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityMixin {

    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    public void setTarget(LivingEntity target, CallbackInfo ci){
        MobEntity mobEntity = (MobEntity)(Object)this;
        for (PassivePower power : PowerHolderComponent.getPowers(target, PassivePower.class)) {
            if(power.checkMob(mobEntity)){
                ci.cancel();
            }
        }
    }
}
