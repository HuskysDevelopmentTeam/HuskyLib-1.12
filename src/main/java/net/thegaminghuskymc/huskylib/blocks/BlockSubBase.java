package net.thegaminghuskymc.huskylib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.thegaminghuskymc.huskylib.items.blocks.ItemBlockBase;

public class BlockSubBase<E extends Enum<E> & IStringSerializable> extends Block {
	
    private String blockName;
    private String[] subNames = null;
    
    private Class<? extends Enum<?>> clazz;

	public BlockSubBase(String modid, String name, Material material, CreativeTabs creativetab) {
		super(material);
		this.blockName = name;
		this.subNames = null;
		this.clazz = null;
		setCreativeTab(creativetab);
		setRegistryName(modid, name);
        setUnlocalizedName(modid + "." + name);
	}
	
	public BlockSubBase(String modid, String name, Material material, CreativeTabs creativeTab, String... subNames) {
		super(material);
		this.blockName = name;
		this.subNames = subNames;
		this.clazz = null;
		setCreativeTab(creativeTab);
		setRegistryName(modid, name);
        setUnlocalizedName(modid + "." + name);
	}
	
	public BlockSubBase(String modid, String name, Material material, CreativeTabs creativeTabs, Class<? extends Enum<?>> clazz, String... subNames) {
		super(material);
		this.blockName = name;
		this.subNames = subNames;
		this.clazz = clazz;
		setCreativeTab(creativeTabs);
		setRegistryName(modid, name);
        setUnlocalizedName(modid + "." + name);
	}

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, this.getMetaFromState(state));
    }
	
	@Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		for(int i = 0; i < clazz.getEnumConstants().length; i++){
            list.add(new ItemStack(this, 1, i));
        }
    }

	public Item createItemBlock() {
		return new ItemBlockBase(this).setRegistryName(this.getRegistryName());
	}
	
	public String getBlockName(){
        return this.blockName;
    }

    public String[] getSubNames(){
        return this.subNames;
    }
    
    public ItemBlock getItemBlock(){
    	return new ItemBlockBase(this);
    }

}
