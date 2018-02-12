package net.thegaminghuskymc.huskylib.items.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemLeggingsBase extends ItemArmorBase{

	public ItemLeggingsBase(ArmorMaterial materialIn, String name, CreativeTabs tabs) {
		super(materialIn, 2, EntityEquipmentSlot.LEGS, name + "_leggings", tabs);
	}

}
