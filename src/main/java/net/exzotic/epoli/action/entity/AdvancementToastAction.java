package net.exzotic.epoli.action.entity;

import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.advancement.Advancement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.AdvancementToast;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static java.lang.Thread.sleep;
import static net.minecraft.client.toast.SystemToast.Type.TUTORIAL_HINT;

public class AdvancementToastAction {

    public static Advancement advancement;

    public static void action(SerializableData.Instance data, Entity entity) {

        PacketByteBuf buf = PacketByteBufs.create();
    }

    public static void getAdvancement(SerializableData.Instance data, Entity entity){
        if(!entity.getWorld().isClient()) {
            Epoli.LOGGER.info("SERVER");
            Identifier id = data.getId("advancement");
            advancement = entity.getServer().getAdvancementLoader().get(id);
        }
    }

    public static void displayAdvancement(SerializableData.Instance data, Entity entity){
        if (entity == MinecraftClient.getInstance().player) {
            Epoli.LOGGER.info("CLIENT");
            if(advancement != null) {
                MinecraftClient.getInstance().getToastManager().add(new AdvancementToast(advancement));
            } else {
                Epoli.LOGGER.info("NULL");
            }
        }
    }

    public static ActionFactory<Entity> getFactory() {
        return new ActionFactory<>(Epoli.identifier("advancement_toast"),
                new SerializableData()
                        .add("advancement", SerializableDataTypes.IDENTIFIER),
                AdvancementToastAction::action
        );
    }

}
