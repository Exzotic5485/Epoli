package net.exzotic.epoli.registry;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    public static final TagKey<Block> REDSTONE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier("epoli", "redstone"));
}
