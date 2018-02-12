package net.thegaminghuskymc.huskylib.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;

public class ItemHoeBase extends ItemHoe {
	
	public ItemHoeBase(ToolMaterial material, String name, CreativeTabs tabs) {
		super(material);
		setUnlocalizedName(name + "_hoe");
		setRegistryName(name + "_hoe");
		setCreativeTab(tabs);
	}

}
