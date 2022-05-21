package net.exzotic.epoli.action.entity;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.exzotic.epoli.Epoli;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

import static net.minecraft.client.toast.SystemToast.Type.TUTORIAL_HINT;

public class SystemToastAction {

    public static void action(SerializableData.Instance data, Entity entity) {
        if (entity == MinecraftClient.getInstance().player) {
            ToastManager manager = MinecraftClient.getInstance().getToastManager();
            Text title = data.get("title");
            Text description = data.get("description");

            SystemToast.show(manager, TUTORIAL_HINT, title, description);
        }
    }

    public static ActionFactory<Entity> getFactory() {
        return new ActionFactory<>(Epoli.identifier("system_toast"),
                new SerializableData()
                .add("title", SerializableDataTypes.TEXT, null)
                .add("description", SerializableDataTypes.TEXT, null),
                SystemToastAction::action
        );
    }
}
