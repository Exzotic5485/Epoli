package net.exzotic.epoli.action.entity;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.exzotic.epoli.component.LocationComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import java.util.Optional;

import static net.exzotic.epoli.registry.ModComponent.SAVEDTPS;

public class TeleportLocationAction {
    public static void action(SerializableData.Instance data, Entity entity) {
        if(entity.getWorld().isClient) {
            return;
        }

        PlayerEntity player = (PlayerEntity) entity;

        String saveid = data.getString("saveid");

        Optional<LocationComponent> location = SAVEDTPS.get(player).getLocations().stream().filter(v -> v.getSaveId().equals(saveid)).findFirst();

        if(location.isEmpty()){
            Epoli.LOGGER.warn("DID NOT TELEPORT");
            return;
        }

        ServerWorld tpworld = player.getServer().getWorld(RegistryKey.of(Registry.WORLD_KEY, location.get().getDimID()));
        double x = location.get().getX();
        double y = location.get().getY();
        double z = location.get().getZ();

        float yaw = location.get().getYaw();
        float pitch = location.get().getPitch();

        ((ServerPlayerEntity)entity).teleport(tpworld, x, y, z, yaw, pitch);
    }

    public static ActionFactory<Entity> getFactory() {
        return new ActionFactory<>(Epoli.identifier("teleport_location"),
                new SerializableData()
                        .add("saveid", SerializableDataTypes.STRING, "myorigin"),
                TeleportLocationAction::action
        );
    }
}
