package net.exzotic.epoli.registry;

import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.exzotic.epoli.power.*;
import net.minecraft.util.registry.Registry;

public class EpoliPowerFactory {

    public static void register(){
        register(PreventSoulSandSlownessPower.getFactory());
        register(ActionOnExperienceGainPower.getFactory());
        register(PassivePower.getFactory());
        register(ActionOnTriggerPower.getFactory());
        register(ActionOnDeath.getFactory());
    }

    public static void register(PowerFactory<?> serializer) {
        Registry.register(ApoliRegistries.POWER_FACTORY, serializer.getSerializerId(), serializer);
    }
}
