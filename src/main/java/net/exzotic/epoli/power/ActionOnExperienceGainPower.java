package net.exzotic.epoli.power;

import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.function.Consumer;

public class ActionOnExperienceGainPower extends Power {
    private final Consumer<Entity> entityAction;

    public ActionOnExperienceGainPower(PowerType<?> type, LivingEntity entity, Consumer<Entity> entityAction) {
        super(type, entity);
        this.entityAction = entityAction;
    }

    public void executeAction() {
        entityAction.accept(entity);
    }

    public static PowerFactory getFactory() {
        return new PowerFactory<>(Epoli.identifier("action_on_xp_gain"),
                new SerializableData()
                        .add("entity_action", ApoliDataTypes.ENTITY_ACTION, null),
                data ->
                        (type, player) -> new ActionOnExperienceGainPower(type, player,
                                (ActionFactory<Entity>.Instance)data.get("entity_action")))
                .allowCondition();
    }
}
