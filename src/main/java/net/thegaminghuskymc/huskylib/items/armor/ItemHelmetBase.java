package net.thegaminghuskymc.huskylib.items.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemHelmetBase extends ItemArmorBase{

	public ItemHelmetBase(ArmorMaterial materialIn, String name, CreativeTabs tabs) {
		super(materialIn, 1, EntityEquipmentSlot.HEAD, name + "_helmet", tabs);
	}

}
