package net.thegaminghuskymc.huskylib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockMultiBase extends BlockBase{
	
	public static String modID;

    public BlockMultiBase(String modID, String name, Material material, CreativeTabs creativeTab, String... subBlocks){
        super(modID, name, creativeTab);
        this.modID = modID;
        ForgeRegistries.ITEMS.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        ForgeRegistries.BLOCKS.register(this);
        registerRender(this);
    }

    public static void registerRender(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(modID, block.getUnlocalizedName().substring(5)), "inventory"));
    }

}
