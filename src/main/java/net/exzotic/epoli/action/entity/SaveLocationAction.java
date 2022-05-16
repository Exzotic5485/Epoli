package net.exzotic.epoli.action.entity;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.exzotic.epoli.component.LocationComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Optional;

import static net.exzotic.epoli.component.SavedTpsComponent.removeLocation;
import static net.exzotic.epoli.registry.ModComponent.SAVEDTPS;

public class SaveLocationAction {
    public static void action(SerializableData.Instance data, Entity entity) {
        if(entity.getWorld().isClient) {
            return;
        }

        PlayerEntity player = (PlayerEntity) entity;

        String saveid = data.getString("saveid");

        Optional<LocationComponent> checkloc = SAVEDTPS.get(player).getLocations().stream().filter(v -> v.getSaveId().equals(saveid)).findFirst();
        boolean overwrite = data.getBoolean("overwrite");

        if(!checkloc.isEmpty()){
            removeLocation(saveid);
        }

        SAVEDTPS.get(player).saveLocation(new LocationComponent(
                player.getPos(),
                player.getPitch(),
                player.getYaw(),
                player.getWorld().getRegistryKey().getValue(),
                saveid));

    }

    public static ActionFactory<Entity> getFactory() {
        return new ActionFactory<>(Epoli.identifier("save_location"),
                new SerializableData()
                        .add("saveid", SerializableDataTypes.STRING, "myorigin")
                        .add("overwrite", SerializableDataTypes.BOOLEAN, true),
                SaveLocationAction::action
        );
    }
}
