package net.exzotic.epoli.power;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.LivingEntity;

public class MultiplyArrowSpeedPower extends Power {

    private final int multiplier;


    public MultiplyArrowSpeedPower(PowerType<?> type, LivingEntity entity, int multiplier) {
        super(type, entity);
        this.multiplier = multiplier;
    }

    public int getMultiplier(){
        return multiplier;
    }

    public static PowerFactory getFactory() {
        return new PowerFactory<>(Epoli.identifier("multiply_bow_speed"),
                new SerializableData()
                .add("multiplier", SerializableDataTypes.INT, 1),
                data ->
                        (type, player) -> new MultiplyArrowSpeedPower(type, player, data.getInt("multiplier")))
                .allowCondition();
    }

}
