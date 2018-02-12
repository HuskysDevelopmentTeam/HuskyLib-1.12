package net.thegaminghuskymc.huskylib.items.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemChestplateBase extends ItemArmorBase{

	public ItemChestplateBase(ArmorMaterial materialIn, String name, CreativeTabs tabs) {
		super(materialIn, 1, EntityEquipmentSlot.CHEST, name + "_chestplate", tabs);
	}

}
