package net.exzotic.epoli.power;

import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.calio.data.SerializableData;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.function.Predicate;

public class PassivePower extends Power {

    private final Predicate<Entity> entityCondition;

    public PassivePower(PowerType<?> type, LivingEntity entity, Predicate<Entity> entityCondition){
        super(type, entity);
        this.entityCondition = entityCondition;
    }

    public boolean checkMob(Entity target){
        return (entityCondition == null || entityCondition.test(target));
    }

    public static PowerFactory getFactory() {
        return new PowerFactory<>(Epoli.identifier("passive"),
                new SerializableData()
                        .add("entity_condition", ApoliDataTypes.ENTITY_CONDITION, null),
                data ->
                        (type, player) -> new PassivePower(type, player,
                                (ConditionFactory<Entity>.Instance)data.get("entity_condition")))
                .allowCondition();
    }
}
