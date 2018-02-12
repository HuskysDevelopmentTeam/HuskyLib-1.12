package net.thegaminghuskymc.huskylib.blocks.crops;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BlockCropBase extends BlockCrops {
	
	private Item seed;
	private Item crop;
	
	public BlockCropBase(String name, Item seed, Item crop) {
		super();
		this.crop = crop;
		this.seed = seed;
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	@Override
	protected Item getSeed() {
		return seed;
	}
	
	@Override
	protected Item getCrop() {
		return crop;
	}

}
