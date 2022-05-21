package net.exzotic.epoli.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.exzotic.epoli.power.MultiplyArrowSpeedPower;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {

    @Unique
    private static PlayerEntity storedEntity;

    @Inject(method = "getPullProgress", at = @At("TAIL"), cancellable = true)
    private static void getPullPirogress(int useTicks, ItemStack stack, CallbackInfoReturnable<Float> cir){
        for (MultiplyArrowSpeedPower power : PowerHolderComponent.getPowers(storedEntity, MultiplyArrowSpeedPower.class)) {
            float f = cir.getReturnValue();
            int multiplier = power.getMultiplier();
            f = f * multiplier;
            cir.setReturnValue(f);
        }
    }

    @Inject(method = "use", at = @At("HEAD"))
    private void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
        this.storedEntity = user;
    }

}
