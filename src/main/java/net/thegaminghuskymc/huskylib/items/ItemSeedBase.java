package net.thegaminghuskymc.huskylib.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class ItemSeedBase extends ItemSeeds{

	public ItemSeedBase(String name, Block crop, CreativeTabs creativetab) {
		super(crop, Blocks.FARMLAND);
		setUnlocalizedName(name + "_seed");
		setRegistryName(name + "_seed");
		setCreativeTab(creativetab);
	}

}
