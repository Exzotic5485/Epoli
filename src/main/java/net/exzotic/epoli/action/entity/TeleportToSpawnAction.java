package net.exzotic.epoli.action.entity;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TeleportToSpawnAction {

    public static void action(SerializableData.Instance data, Entity entity){
        World world = entity.getWorld();

        if(world.isClient) {
            return;
        }

        boolean playerspawn = data.getBoolean("player_spawn");

        if(playerspawn == true){
            PlayerEntity player = (PlayerEntity) entity;
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

            BlockPos spawnPos = serverPlayer.getSpawnPointPosition();

            if(spawnPos == null){
                serverPlayer.sendMessage(Text.of("No Respawn Point Set."));
                return;
            }

            ServerWorld spawnWorld = serverPlayer.getServer().getWorld(serverPlayer.getSpawnPointDimension());

            ((ServerPlayerEntity)entity).teleport(spawnWorld, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);


        } else {
            PlayerEntity player = (PlayerEntity) entity;
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

            ServerWorld serverWorld = (ServerWorld) world;

            BlockPos worldSpawn = serverWorld.getSpawnPos();

            MinecraftServer server = world.getServer();

            ServerWorld overworld = server.getWorld(World.OVERWORLD);

            ((ServerPlayerEntity)entity).teleport(overworld, worldSpawn.getX(), worldSpawn.getY(), worldSpawn.getZ(), 0, 0);
        }
    }


    public static ActionFactory<Entity> getFactory() {
        return new ActionFactory<>(Epoli.identifier("teleport_to_spawn"),
                new SerializableData()
                        .add("player_spawn", SerializableDataTypes.BOOLEAN, false),
                TeleportToSpawnAction::action
        );
    }
}
