package net.thegaminghuskymc.huskylib.items.armor.special;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.thegaminghuskymc.huskylib.items.armor.ItemArmorBase;

public class ItemChestplateSpecialBase extends ItemArmorBase{

	public ItemChestplateSpecialBase(ArmorMaterial materialIn, String name, CreativeTabs tabs) {
		super(materialIn, 1, EntityEquipmentSlot.CHEST, name + "_chestplate", tabs);
	}

}
