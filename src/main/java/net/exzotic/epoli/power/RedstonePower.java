package net.exzotic.epoli.power;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.LivingEntity;

public class RedstonePower extends Power {

    public RedstonePower(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
    }

    public static PowerFactory getFactory() {
        return new PowerFactory<>(Epoli.identifier("redstone"),
                new SerializableData(),
                data ->
                        (type, player) -> new RedstonePower(type, player))
                .allowCondition();
    }
}
