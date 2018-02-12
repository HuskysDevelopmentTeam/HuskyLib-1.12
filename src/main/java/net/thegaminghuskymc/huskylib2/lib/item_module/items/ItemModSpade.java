package net.thegaminghuskymc.huskylib2.lib.item_module.items;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import java.util.Set;


public abstract class ItemModSpade extends ItemModTool {

    private static final Set<Block> EFFECTIVE_ON;

    static {
        EFFECTIVE_ON = Sets.newHashSet(Blocks.CLAY,
                Blocks.DIRT,
                Blocks.FARMLAND,
                Blocks.GRASS,
                Blocks.GRAVEL,
                Blocks.MYCELIUM,
                Blocks.SAND,
                Blocks.SNOW,
                Blocks.SNOW_LAYER,
                Blocks.SOUL_SAND,
                Blocks.GRASS_PATH,
                Blocks.CONCRETE_POWDER);
    }

    public ItemModSpade(Item.ToolMaterial material, String name, String... variants) {
        super(1.5F, -3.0F, material, EFFECTIVE_ON, name, variants);
    }
}
