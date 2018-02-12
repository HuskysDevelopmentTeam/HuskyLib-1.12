package net.thegaminghuskymc.huskylib.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class ItemShovelBase extends ItemSpade {
	
	public ItemShovelBase(ToolMaterial material, String name, CreativeTabs tabs) {
		super(material);
		setUnlocalizedName(name + "_shovel");
		setRegistryName(name + "_shovel");
		setCreativeTab(tabs);
	}
}
