package net.thegaminghuskymc.huskylib2.lib.item_module;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thegaminghuskymc.huskylib2.lib.modules.Module;

public class ItemModule extends Module {

    @Override
    public void addFeatures() {
        super.addFeatures();
    }

    @Override
    public ItemStack getIconStack() {
        return new ItemStack(Items.STICK);
    }

}
