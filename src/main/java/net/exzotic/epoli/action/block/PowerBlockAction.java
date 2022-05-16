package net.exzotic.epoli.action.block;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.util.Scheduler;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Triple;

import java.util.function.Consumer;

public class PowerBlockAction {

    private static final Scheduler SCHEDULER = new Scheduler();

    public static void action(SerializableData.Instance data, Triple<World, BlockPos, Direction> block){
        World world = block.getLeft();
        BlockPos pos = block.getMiddle();


        SCHEDULER.queue(queued(data, block.getMiddle(), world), 10);
    }

    public static Consumer<MinecraftServer> queued(SerializableData.Instance data, BlockPos pos, World world){
        Epoli.LOGGER.info("Waited");
        return null;
    }

    public static ActionFactory<Triple<World, BlockPos, Direction>> getFactory() {
        return new ActionFactory<>(Epoli.identifier("powerblock"),
                new SerializableData()
                        .add("duration", SerializableDataTypes.INT, 0),
                PowerBlockAction::action
        );
    }
}
