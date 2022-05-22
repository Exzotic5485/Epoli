package net.exzotic.epoli.component;

import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class SavedTpsComponent extends TestSavedTpsComponent {
    private static final List<LocationComponent> locations = new ArrayList<>();

    @Override
    public void readFromNbt(NbtCompound tag) {
        locations.clear();
        tag.getList("savedtps", NbtType.COMPOUND).forEach(v -> locations.add(LocationComponent.readFromNbt((NbtCompound) v)));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        NbtList locationTag = new NbtList();
        locations.forEach(v -> {
            NbtCompound ct = new NbtCompound();
            v.writeToNbt(ct);
            locationTag.add(ct);
        });
        tag.put("savedtps", locationTag);
    }

    public List<LocationComponent> getLocations() { return locations; }

    public boolean saveLocation(LocationComponent location) {
        return locations.add(location);
    }

    public static boolean removeLocation(String saveid) {
        if (locations.stream().noneMatch(v -> v.getSaveId().equalsIgnoreCase(saveid))) return false;
        return locations.removeIf(v -> v.getSaveId().equalsIgnoreCase(saveid));
    }
}
