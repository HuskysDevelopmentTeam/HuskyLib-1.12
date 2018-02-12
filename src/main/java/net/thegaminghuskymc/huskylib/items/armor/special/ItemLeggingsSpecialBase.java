package net.thegaminghuskymc.huskylib.items.armor.special;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.thegaminghuskymc.huskylib.items.armor.ItemArmorBase;

public class ItemLeggingsSpecialBase extends ItemArmorBase{

	public ItemLeggingsSpecialBase(ArmorMaterial materialIn, String name, CreativeTabs tabs) {
		super(materialIn, 2, EntityEquipmentSlot.LEGS, name + "_leggings", tabs);
	}

}
