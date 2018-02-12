package net.thegaminghuskymc.huskylib.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.thegaminghuskymc.huskylib.enums.EnumMaterialType;

public class BlockTestBlock extends EnumBlock<EnumMaterialType>{
	
	public static final PropertyEnum<EnumMaterialType> TYPE = PropertyEnum.create("meta", EnumMaterialType.class);

	public BlockTestBlock() {
		super(Material.ROCK, "test_block", CreativeTabs.BUILDING_BLOCKS, TYPE, EnumMaterialType.class, EnumMaterialType.toStringArray());
	}

}
