package net.exzotic.epoli.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.exzotic.epoli.Epoli;
import net.exzotic.epoli.power.ActionOnDeath;
import net.exzotic.epoli.power.ActionOnExperienceGainPower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "addExperience", at = @At("HEAD"))
    public void addExperience(int experience, CallbackInfo ci){
        PlayerEntity entity = (PlayerEntity)(Object)this;
        for (ActionOnExperienceGainPower power : PowerHolderComponent.getPowers(entity, ActionOnExperienceGainPower.class)) {
            power.executeAction();
        }
    }
}
