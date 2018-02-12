package net.thegaminghuskymc.huskylib.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IInventoryAction {

    void onInventoryAction(EntityPlayer player, int index, int count, ItemStack stack, InventoryAction action);

}
