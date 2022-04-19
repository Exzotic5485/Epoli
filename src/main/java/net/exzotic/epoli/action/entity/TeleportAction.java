package net.exzotic.epoli.action.entity;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class TeleportAction {
    public static void action(SerializableData.Instance data, Entity entity) {
        if (!(entity instanceof LivingEntity)) return;
        double x = data.getDouble("x");
        double y = data.getDouble("y");
        double z = data.getDouble("z");

        var dimension = data.getString("dimension");

        entity.teleport(x, y, z);
    }

    public static ActionFactory<Entity> getFactory() {
        return new ActionFactory<>(Epoli.identifier("teleport"),
                new SerializableData()
                        .add("x", SerializableDataTypes.DOUBLE, 0D)
                        .add("y", SerializableDataTypes.DOUBLE, 0D)
                        .add("z", SerializableDataTypes.DOUBLE, 0D)
                        .add("dimension", SerializableDataTypes.STRING, "minecraft:overworld"),
                TeleportAction::action
        );
    }
}
