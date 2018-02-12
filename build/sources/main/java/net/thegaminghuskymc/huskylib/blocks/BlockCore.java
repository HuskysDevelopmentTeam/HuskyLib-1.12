package net.thegaminghuskymc.huskylib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCore extends Block {

	protected String modName;
	protected String name;

	public BlockCore(Material material) {
		this(material, "huskylib");
	}

	public BlockCore(Material material, String modName) {
		super(material);
		this.modName = modName;
	}

	@Override
	public Block setUnlocalizedName(String name) {

		this.name = name;
		name = modName + "." + name;
		return super.setUnlocalizedName(name);
	}

}