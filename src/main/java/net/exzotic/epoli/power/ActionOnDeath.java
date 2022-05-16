package net.exzotic.epoli.power;

import com.mojang.datafixers.util.Pair;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.calio.data.SerializableData;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ActionOnDeath extends Power {
    private final Consumer<Entity> entityAction;
    private final Predicate<Pair<DamageSource, Float>> condition;

    public ActionOnDeath(PowerType<?> type, LivingEntity entity, Consumer<Entity> entityAction, Predicate<Pair<DamageSource, Float>> condition) {
        super(type, entity);
        this.entityAction = entityAction;
        this.condition = condition;
    }

    public boolean doesApply(DamageSource source, float amount) {
        return condition == null || condition.test(new Pair<>(source, amount));
    }

    public void executeAction() {
        entityAction.accept(entity);
    }

    public static PowerFactory getFactory() {
        return new PowerFactory<>(Epoli.identifier("action_on_death"),
                new SerializableData()
                        .add("entity_action", ApoliDataTypes.ENTITY_ACTION, null)
                .add("damage_condition", ApoliDataTypes.DAMAGE_CONDITION, null),
                data ->
                        (type, player) -> new ActionOnDeath(type, player,
                                (ActionFactory<Entity>.Instance)data.get("entity_action"),
                                (ConditionFactory<Pair<DamageSource, Float>>.Instance)data.get("damage_condition")))
                .allowCondition();
    }
}
