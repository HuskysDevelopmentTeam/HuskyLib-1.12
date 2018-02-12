package net.thegaminghuskymc.huskylib.utils;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IShiftDescription {

    boolean shouldAddDescription(ItemStack stack, World player);

    void addDescription(ItemStack stack, World player, List<String> tooltip);

}
