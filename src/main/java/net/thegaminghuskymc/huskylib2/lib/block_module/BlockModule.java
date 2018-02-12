package net.thegaminghuskymc.huskylib2.lib.block_module;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.thegaminghuskymc.huskylib2.lib.modules.Module;

public class BlockModule extends Module {

    @Override
    public void addFeatures() {
        super.addFeatures();
    }

    @Override
    public ItemStack getIconStack() {
        return new ItemStack(Blocks.GRASS);
    }

}
