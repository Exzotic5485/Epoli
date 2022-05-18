package net.exzotic.epoli.access;

import net.minecraft.entity.Entity;

public interface ItemStackAccess {
    void setEntity(Entity entity);

    Entity getEntity();
}
