package net.hdt.huskylib2.container.slot;

import net.hdt.huskylib2.recipe.RecipeHandler;
import net.minecraft.inventory.IInventory;

public class SlotIngredient extends SlotFiltered {

	public SlotIngredient(IInventory inventoryIn, int index, int xPosition, int yPosition, Object obj) {
		super(inventoryIn, index, xPosition, yPosition, RecipeHandler.asIngredient(obj));
	}
	
}
