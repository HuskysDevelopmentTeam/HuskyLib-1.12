package team.hdt.huskylib.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.List;

public class MultiRecipe extends ModRecipe {

    IRecipe matched;
    private List<IRecipe> subRecipes = new LinkedList<>();

    public MultiRecipe(ResourceLocation res) {
        super(res);
    }

    public void addRecipe(IRecipe recipe) {
        subRecipes.add(recipe);
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        matched = null;
        for (IRecipe recipe : subRecipes)
            if (recipe.matches(inv, worldIn)) {
                matched = recipe;
                return true;
            }

        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        if (matched == null)
            return ItemStack.EMPTY;

        return matched.getCraftingResult(inv);
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

}
