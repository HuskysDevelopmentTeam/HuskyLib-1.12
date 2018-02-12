package net.thegaminghuskymc.huskylib2.lib.entity_module;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thegaminghuskymc.huskylib2.lib.modules.Module;

public class EntityModule extends Module {

    @Override
    public void addFeatures() {
        super.addFeatures();
    }

    @Override
    public ItemStack getIconStack() {
        return new ItemStack(Items.SPAWN_EGG, 1, 3);
    }

}
