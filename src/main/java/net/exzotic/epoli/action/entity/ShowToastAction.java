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

public class ShowToastAction {

    public static void action(SerializableData.Instance data, Entity entity) {
        if (entity == MinecraftClient.getInstance().player) {
            ToastManager manager = MinecraftClient.getInstance().getToastManager();
            Text title = Text.of(data.getString("title"));
            Text description = Text.of(data.getString("description"));

            SystemToast.show(manager, TUTORIAL_HINT, title, description);
        }

        if(entity.getWorld().isClient) {
            return;
        }
    }

    public static ActionFactory<Entity> getFactory() {
        return new ActionFactory<>(Epoli.identifier("alert"),
                new SerializableData()
                .add("title", SerializableDataTypes.STRING, null)
                .add("description", SerializableDataTypes.STRING, null),
                ShowToastAction::action
        );
    }
}
