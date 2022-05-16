package net.exzotic.epoli.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.exzotic.epoli.component.SavedTpsComponent;
import net.minecraft.util.Identifier;


public class ModComponent implements EntityComponentInitializer {

    public static final ComponentKey<SavedTpsComponent> SAVEDTPS = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("epoli", "savedtps"), SavedTpsComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(SAVEDTPS, player -> new SavedTpsComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
