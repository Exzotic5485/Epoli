package net.exzotic.epoli.registry;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockTags {
    public static final TagKey<Block> REDSTONE_BLOCKS = TagKey.of(Registry.BLOCK_KEY, new Identifier("epoli", "redstone"));
}
