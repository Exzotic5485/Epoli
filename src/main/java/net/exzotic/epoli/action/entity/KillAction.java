package net.exzotic.epoli.action.entity;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.Entity;

public class KillAction {

    public static void action(SerializableData.Instance data, Entity entity) {

    }


    public static ActionFactory<Entity> getFactory() {
        return new ActionFactory<>(Epoli.identifier("kill"),
                new SerializableData(),
                KillAction::action
        );
    }
}
