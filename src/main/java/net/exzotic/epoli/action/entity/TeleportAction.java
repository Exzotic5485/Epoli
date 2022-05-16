package net.exzotic.epoli.action.entity;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class TeleportAction {
    public static void action(SerializableData.Instance data, Entity entity) {
        if (!(entity instanceof LivingEntity)) return;
        double x = data.getDouble("x");
        double y = data.getDouble("y");
        double z = data.getDouble("z");

        ServerWorld tpworld = entity.getServer().getWorld(RegistryKey.of(Registry.WORLD_KEY, data.get("dimension")));

        ((ServerPlayerEntity)entity).teleport(tpworld, x, y, z, 0, 0);
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
