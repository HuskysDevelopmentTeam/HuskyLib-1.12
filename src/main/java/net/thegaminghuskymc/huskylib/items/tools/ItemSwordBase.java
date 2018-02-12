package net.thegaminghuskymc.huskylib.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ItemSwordBase extends ItemSword {

	public ItemSwordBase(ToolMaterial material, String name, CreativeTabs tabs) {
		super(material);
		setUnlocalizedName(name + "_sword");
		setRegistryName(name + "_sword");
		setCreativeTab(tabs);
	}

}
