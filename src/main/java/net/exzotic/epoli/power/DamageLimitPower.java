package net.exzotic.epoli.power;

import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Pair;

import java.util.function.Predicate;

public class DamageLimitPower extends Power {
    private final Predicate<Pair<DamageSource, Float>> damageCondition;
    private float limit;

    public DamageLimitPower(PowerType<?> type, LivingEntity entity, Float limit, Predicate<Pair<DamageSource, Float>> damageCondition) {
        super(type, entity);
        this.damageCondition = damageCondition;
        this.limit = limit;
    }

    public boolean doesApply(DamageSource source, float damageAmount) {
        if(damageCondition != null){
            return damageCondition.test(new Pair<>(source, damageAmount));
        } else {
            return true;
        }
    }

    public float getLimit() {
        return limit;
    }

    public static PowerFactory getFactory() {
        return new PowerFactory<>(Epoli.identifier("damage_limit"),
                new SerializableData()
                .add("limit", SerializableDataTypes.FLOAT)
                .add("damage_condition", ApoliDataTypes.DAMAGE_CONDITION, null),
                data ->
                        (type, player) -> new DamageLimitPower(type, player, data.getFloat("limit"), (ConditionFactory<Pair<DamageSource, Float>>.Instance)data.get("damage_condition")))
                .allowCondition();
    }

}
