package net.thegaminghuskymc.huskylib2.lib.rendering_module;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.thegaminghuskymc.huskylib2.lib.modules.Module;

public class RenderingModule extends Module {

    @Override
    public void addFeatures() {
        super.addFeatures();
    }

    @Override
    public ItemStack getIconStack() {
        return new ItemStack(Blocks.GLASS);
    }

}
