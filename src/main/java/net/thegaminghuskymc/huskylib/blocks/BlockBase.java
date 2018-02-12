package net.thegaminghuskymc.huskylib.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBase extends Block {
	
	private String[] subNames;

	public BlockBase(String modid, String name, CreativeTabs creativetab) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(modid, name);
		setHardness(5.0F);
		setResistance(5.0F);
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(creativetab);
	}
	
	public BlockBase(String modid, String name, CreativeTabs creativeTabs, String... subNames) {
		super(Material.ROCK);
		if(subNames.length == 0)
			subNames = new String[] { name };
		this.subNames = subNames;
		setUnlocalizedName(name);
		setRegistryName(modid, name);
		setHardness(5.0F);
		setResistance(5.0F);
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(creativeTabs);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
		
	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}

	public String[] getSubNames() {
		return subNames;
	}

	public IProperty getVariantProp() {
		return null;
	}

	public Class getVariantEnum() {
		return null;
	}

}
