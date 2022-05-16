package net.exzotic.epoli.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.calio.data.SerializableDataType;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.exzotic.epoli.power.RedstonePower;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(World.class)
public abstract class WorldMixin implements WorldAccess {

    /**
    @Inject(method = "isReceivingRedstonePower", at = @At("HEAD"), cancellable = true)
    private void powerRedstone(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        World world = (World)(Object)this;
        List<LivingEntity> entities = world.getNonSpectatingEntities(LivingEntity.class, new Box(pos.up()));
        if(entities.size() > 0){
            var entity = entities.get(0);

            for (RedstonePower power : PowerHolderComponent.getPowers(entity, RedstonePower.class)) {
                cir.setReturnValue(true);
            }
        }
    }
    **/
}
