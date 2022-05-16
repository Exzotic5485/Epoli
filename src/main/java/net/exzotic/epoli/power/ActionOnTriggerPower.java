package net.exzotic.epoli.power;

import com.google.common.base.Strings;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.util.Comparison;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ActionOnTriggerPower extends Power {
    private final Consumer<Entity> entityAction;
    private final String objective;
    private List<String> operation = new ArrayList<>();
    private final Comparison comparison;
    private final Integer compareto;

    public ActionOnTriggerPower(PowerType<?> type, LivingEntity entity, Consumer<Entity> entityAction, String objective, List<String> operation, Comparison comparison, Integer compareto ) {
        super(type, entity);
        this.entityAction = entityAction;
        this.objective = objective;
        this.operation = operation;
        this.comparison = comparison;
        this.compareto = compareto;
    }

    public boolean isScore(ScoreboardPlayerScore score) {
        if(objective.equals(score.getObjective().getName())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOperation(String op){
        if(operation.contains(op)){
            return true;
        } else {
            return false;
        }
    }

    public boolean compare(int value) {
        if (comparison.compare(value, compareto)) {
            return true;
        } else {
            return false;
        }
    }

    public void executeAction() {
        entityAction.accept(entity);
    }


    public static PowerFactory getFactory() {
        return new PowerFactory<>(Epoli.identifier("action_on_trigger"),
                new SerializableData()
                        .add("entity_action", ApoliDataTypes.ENTITY_ACTION, null)
                .add("objective", SerializableDataTypes.STRING, null)
                .add("operation", SerializableDataType.list(SerializableDataTypes.STRING), null)
                        .add("comparison", ApoliDataTypes.COMPARISON, Comparison.GREATER_THAN_OR_EQUAL)
                        .add("compare_to", SerializableDataTypes.INT, 0),
                data ->
                        (type, player) -> new ActionOnTriggerPower(type, player,
                                (ActionFactory<Entity>.Instance)data.get("entity_action"), (String)data.get("objective"), (List<String>)data.get("operation"), (Comparison)data.get("comparison"), (Integer)data.get("compare_to")))
                .allowCondition();
    }
}
