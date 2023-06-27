package net.exzotic.epoli.action.entity;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.exzotic.epoli.component.LocationComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Optional;

import static net.exzotic.epoli.registry.ModComponent.SAVEDTPS;

public class TeleportLocationAction {
    public static void action(SerializableData.Instance data, Entity entity) {
        if(entity.getWorld().isClient) {
            return;
        }

        LivingEntity livingEntity = (LivingEntity)entity;

        String saveid = data.getString("saveid");

        Optional<LocationComponent> location = SAVEDTPS.get(livingEntity).getLocations().stream().filter(v -> v.getSaveId().equals(saveid)).findFirst();

        if(location.isEmpty()){
            Epoli.LOGGER.warn("DID NOT TELEPORT");
            return;
        }

        ServerWorld tpworld = livingEntity.getServer().getWorld(RegistryKey.of(RegistryKeys.WORLD, location.get().getDimID()));
        double x = location.get().getX();
        double y = location.get().getY();
        double z = location.get().getZ();

        float yaw = location.get().getYaw();
        float pitch = location.get().getPitch();

        if(livingEntity instanceof PlayerEntity){
            ((ServerPlayerEntity)entity).teleport(tpworld, x, y, z, yaw, pitch);
            return;
        } else {
            livingEntity.moveToWorld(tpworld);
            livingEntity.teleport(x, y, z);
        }
    }

    public static ActionFactory<Entity> getFactory() {
        return new ActionFactory<>(Epoli.identifier("teleport_location"),
                new SerializableData()
                        .add("saveid", SerializableDataTypes.STRING, "myorigin"),
                TeleportLocationAction::action
        );
    }
}
