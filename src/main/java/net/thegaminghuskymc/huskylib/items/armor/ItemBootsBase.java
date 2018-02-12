package net.thegaminghuskymc.huskylib.items.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemBootsBase extends ItemArmorBase{

	public ItemBootsBase(ArmorMaterial materialIn, String name, CreativeTabs tabs) {
		super(materialIn, 1, EntityEquipmentSlot.FEET, name + "_boots", tabs);
	}

}
