package net.exzotic.epoli.registry.action;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.exzotic.epoli.action.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.registry.Registry;

public class EpoliEntityActions {
    public static void register() {
        register(TeleportAction.getFactory());
        register(TeleportToSpawnAction.getFactory());
        register(KillAction.getFactory());
        register(SaveLocationAction.getFactory());
        register(TeleportLocationAction.getFactory());
        register(SystemToastAction.getFactory());
    }

    private static void register(ActionFactory<Entity> actionFactory) {
        Registry.register(ApoliRegistries.ENTITY_ACTION, actionFactory.getSerializerId(), actionFactory);
    }
}
