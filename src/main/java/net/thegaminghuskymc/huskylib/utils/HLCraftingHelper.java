package net.thegaminghuskymc.huskylib.utils;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.GameData;

public class HLCraftingHelper {

	public static void addShapedOreRecipe(ItemStack output, Object... params) {
		ResourceLocation location = getNameForRecipe(output);
		ShapedOreRecipe recipe = new ShapedOreRecipe(location, output, params);
		recipe.setRegistryName(location);
		GameData.register_impl(recipe);
	}

	public static void addShapedRecipe(ItemStack output, Object... params) {
		ResourceLocation location = getNameForRecipe(output);
		CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(params);
		ShapedRecipes recipe = new ShapedRecipes(output.getItem().getRegistryName().toString(), primer.width, primer.height, primer.input, output);
		recipe.setRegistryName(location);
		GameData.register_impl(recipe);
	}

	public static void addShapelessOreRecipe(ItemStack output, Object... input) {
		ResourceLocation location = getNameForRecipe(output);
		ShapelessOreRecipe recipe = new ShapelessOreRecipe(location, output, input);
		recipe.setRegistryName(location);
		GameData.register_impl(recipe);
	}

	public static void addShapelessRecipe(ItemStack output, Object... input) {
		ResourceLocation location = getNameForRecipe(output);
		ShapelessRecipes recipe = new ShapelessRecipes(location.getResourceDomain(), output, buildInput(input));
		recipe.setRegistryName(location);
		GameData.register_impl(recipe);
	}
	
	public static void addGearRecipe(ItemStack output, Item material){
		addShapedOreRecipe(output, new Object[]{" X ", "XYX", " X ", 'X', material, 'Y', Items.IRON_INGOT});
	}
	
	public static void addGearRecipe(ItemStack output, ItemStack material){
		addShapedOreRecipe(output, new Object[]{" X ", "XYX", " X ", 'X', material, 'Y', Items.IRON_INGOT});
	}
	
	public static void addGearOreRecipe(ItemStack output, Item material, String ingot){
		addShapedOreRecipe(output, new Object[]{" X ", "XYX", " X ", 'X', material, 'Y', ingot});
	}
	
	public static void addGearOreRecipe(ItemStack output, ItemStack material, String ingot){
		addShapedOreRecipe(output, new Object[]{" X ", "XYX", " X ", 'X', material, 'Y', ingot});
	}

	public static ResourceLocation getNameForRecipe(ItemStack output) {
		ModContainer activeContainer = Loader.instance().activeModContainer();
		ResourceLocation baseLoc = new ResourceLocation(activeContainer.getModId(), output.getItem().getRegistryName().getResourcePath());
		ResourceLocation recipeLoc = baseLoc;
		int index = 0;
		while (CraftingManager.REGISTRY.containsKey(recipeLoc)) {
			index++;
			recipeLoc = new ResourceLocation(activeContainer.getModId(), baseLoc.getResourcePath() + "_" + index);
		}
		return recipeLoc;
	}

	private static NonNullList<Ingredient> buildInput (Object[] input) {
		NonNullList<Ingredient> list = NonNullList.create();
		for(Object obj : input){
			if(obj instanceof Ingredient){
				list.add((Ingredient) obj);
			} else {
				Ingredient ingredient = CraftingHelper.getIngredient(obj);
				if(ingredient == null){
					ingredient = Ingredient.EMPTY;
				}
				list.add(ingredient);
			}
		}
		return list;
	}

	public static void addSmelting(Block input, ItemStack output, float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}

	public static void addSmelting(Item input, ItemStack output, float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}

	public static void addSmelting(ItemStack input, ItemStack output, float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}

	public static void addSmelting(ItemStack input, ItemStack output) {
		addSmelting(input, output, 1F);
	}

	public static void addSmelting(Item input, ItemStack output) {
		addSmelting(input, output, 1F);
	}

	public static void addSmelting(Block input, ItemStack output) {
		addSmelting(input, output, 1F);
	}
}
