package net.exzotic.epoli.registry.action;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.exzotic.epoli.action.item.TriggerItemCooldownAction;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.util.Pair;
import net.minecraft.world.World;

public class EpoliItemActions {

    public static void register() {
        register(TriggerItemCooldownAction.getFactory());
    }

    private static void register(ActionFactory<Pair<World, ItemStack>> actionFactory) {
        Registry.register(ApoliRegistries.ITEM_ACTION, actionFactory.getSerializerId(), actionFactory);
    }
}
