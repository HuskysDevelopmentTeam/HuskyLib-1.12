package net.hdt.huskylib2.block;

import net.hdt.huskylib2.interf.IModBlock;
import net.hdt.huskylib2.item.ItemModBlock;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockModStairs extends BlockStairs implements IModBlock {

	private final String[] variants;
	private final String bareName;
	private CreativeTabs creativeTabs;

	public BlockModStairs(String name, IBlockState state) {
		super(state);

		variants = new String[] { name };
		bareName = name;

		setTranslationKey(name);
		useNeighborBrightness = true;
		setCreativeTab(getCreativeTabs());
	}

	@Override
	public Block setTranslationKey(String name) {
		super.setTranslationKey(name);
		setRegistryName(getPrefix() + name);
		ProxyRegistry.register(this);
		ProxyRegistry.register(new ItemModBlock(this, new ResourceLocation(getPrefix() + name)));
		return this;
	}

	public void setCreativeTabs(CreativeTabs creativeTabs) {
		this.creativeTabs = creativeTabs;
	}

	public CreativeTabs getCreativeTabs() {
		if(creativeTabs == null) {
			return CreativeTabs.SEARCH;
		} else {
			return creativeTabs;
		}
	}

	@Override
	public String getBareName() {
		return bareName;
	}

	@Override
	public String[] getVariants() {
		return variants;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemMeshDefinition getCustomMeshDefinition() {
		return null;
	}

	@Override
	public EnumRarity getBlockRarity(ItemStack stack) {
		return EnumRarity.COMMON;
	}

	@Override
	public IProperty[] getIgnoredProperties() {
		return new IProperty[0];
	}

	@Override
	public IProperty getVariantProp() {
		return null;
	}

	@Override
	public Class getVariantEnum() {
		return null;
	}

	public static void initStairs(Block base, int meta, BlockStairs block) {
		RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(block, 4),
				"B  ", "BB ", "BBB",
				'B', ProxyRegistry.newStack(base, 1, meta));
	}


}
