package net.exzotic.epoli.action.item;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.exzotic.epoli.access.ItemStackAccess;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.minecraft.world.World;


public class TriggerItemCooldownAction {
    public static void action(SerializableData.Instance data, Pair<World, ItemStack> worldAndStack) {
        if(worldAndStack.getLeft().isClient()){
            return;
        }

        LivingEntity stackHolder = (LivingEntity) ((ItemStackAccess) (Object) worldAndStack.getRight()).getEntity();

        int duration = data.getInt("duration");

        if(stackHolder != null) {
            ((PlayerEntity) stackHolder).getItemCooldownManager().set(worldAndStack.getRight().getItem(), duration);
        }
    }

    public static ActionFactory<Pair<World, ItemStack>> getFactory() {
        return new ActionFactory<>(Epoli.identifier("trigger_item_cooldown"),
                new SerializableData()
                        .add("duration", SerializableDataTypes.INT, 20),
                TriggerItemCooldownAction::action
        );
    }
}
