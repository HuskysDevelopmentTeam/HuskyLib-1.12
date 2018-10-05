package net.hdt.huskylib2.recipe;

import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CompoundIngredient;

import java.util.Collection;

// why is this constructor protected again? :thinking:
public class PublicCompoundIngredient extends CompoundIngredient {

    public PublicCompoundIngredient(Collection<Ingredient> children) {
        super(children);
    }

}
