package net.exzotic.epoli.power;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class RedstonePower extends Power {
    public final double strength;

    public RedstonePower(PowerType<?> type, LivingEntity entity, double strength) {
        super(type, entity);
        this.strength = strength;
    }

    @Override
    public void tick(){
        World world = entity.getWorld();

        if(world.isClient) {
            return;
        }

        MinecraftServer server = world.getServer();

        PlayerEntity player = (PlayerEntity) entity;
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

        serverPlayer.sendMessage(Text.of("Worked"), true);
        Epoli.LOGGER.info("RAN POWER");
    }


    public static PowerFactory getFactory() {
        return new PowerFactory<>(Epoli.identifier("redstone"),
                new SerializableData()
                        .add("strength", SerializableDataTypes.DOUBLE),
                data ->
                        (type, player) -> new RedstonePower(type, player, data.getDouble("strength")))
                .allowCondition();
    }
}
