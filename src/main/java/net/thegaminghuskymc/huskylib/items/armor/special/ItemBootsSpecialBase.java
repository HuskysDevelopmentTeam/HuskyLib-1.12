package net.thegaminghuskymc.huskylib.items.armor.special;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.thegaminghuskymc.huskylib.items.armor.ItemArmorBase;

public class ItemBootsSpecialBase extends ItemArmorBase{
	
	public ItemBootsSpecialBase(ArmorMaterial materialIn, String name, CreativeTabs tabs) {
		super(materialIn, 1, EntityEquipmentSlot.FEET, name + "_boots", tabs);
	}

}
