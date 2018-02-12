package net.thegaminghuskymc.huskylib;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;

public class RebornRegistry {

	public static void registerBlock(Block block, String name) {
		block.setRegistryName(name);
		GameData.register_impl(block);
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		GameData.register_impl(itemBlock);
	}

	public static void registerBlock(Block block, ResourceLocation name) {
		block.setRegistryName(name);
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		GameData.register_impl(itemBlock);
		GameData.register_impl(block);
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name) {
		block.setRegistryName(name);
		GameData.register_impl(block);
		try {
			ItemBlock itemBlock = itemclass.getConstructor(Block.class).newInstance(block);
			itemBlock.setRegistryName(name);
			GameData.register_impl(itemBlock);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass, ResourceLocation name) {
		block.setRegistryName(name);
		GameData.register_impl(block);
		try {
			ItemBlock itemBlock = itemclass.getConstructor(Block.class).newInstance(block);
			itemBlock.setRegistryName(name);
			GameData.register_impl(itemBlock);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void registerBlock(Block block) {
		GameData.register_impl(block);
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		GameData.register_impl(itemBlock);
	}

	public static void registerItem(Item item) {
		GameData.register_impl(item);
	}

	public static void registerItem(Item item, ResourceLocation name) {
		item.setRegistryName(name);
		GameData.register_impl(item);
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemModel(Item i, int meta) {
		ResourceLocation loc = i.getRegistryName();
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "inventory"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemModel(Block b, int meta) {
		registerItemModel(Item.getItemFromBlock(b), meta);
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemModel(Item i, int meta, String variant) {
		ResourceLocation loc = i.getRegistryName();
		if(!(i instanceof ItemBlock))
			loc = new ResourceLocation(loc.getResourceDomain(), "item/" + loc.getResourcePath());
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "type=" + variant));
	}
}
