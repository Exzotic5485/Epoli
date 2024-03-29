package net.exzotic.epoli;

import net.exzotic.epoli.registry.EpoliPowerFactory;
import net.exzotic.epoli.registry.action.EpoliEntityActions;
import net.exzotic.epoli.registry.action.EpoliItemActions;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Epoli implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("epoli");
    public static final String MODID = "epoli";

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Epoli!");
        EpoliEntityActions.register();
        EpoliPowerFactory.register();
        EpoliItemActions.register();
    }

    public static Identifier identifier(String path) {
        return new Identifier(MODID, path);
    }



}
