package net.thegaminghuskymc.huskylib.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class ItemPickaxeBase extends ItemPickaxe {

	public ItemPickaxeBase(ToolMaterial material, String name, CreativeTabs tabs) {
		super(material);
		setUnlocalizedName(name + "_pickaxe");
		setRegistryName(name + "_pickaxe");
		setCreativeTab(tabs);
	}

}
