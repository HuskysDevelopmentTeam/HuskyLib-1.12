package net.thegaminghuskymc.huskylib.blocks;

import net.minecraft.creativetab.CreativeTabs;

public class BlockOreBase extends BlockBase {
	
	public BlockOreBase(String modID, String name, CreativeTabs creativeTabs) {
		super(modID, name, creativeTabs);
		setHardness(5.0F);
		setResistance(5.0F);
		setHarvestLevel("pickaxe", 1);
	}

}
