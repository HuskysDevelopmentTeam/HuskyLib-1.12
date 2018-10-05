package net.hdt.huskylib2.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreIngredient;

import java.util.function.Predicate;

public class BlacklistOreIngredient extends OreIngredient {

    final Predicate<ItemStack> blacklist;

    public BlacklistOreIngredient(String key, Predicate<ItemStack> blacklist) {
        super(key);
        this.blacklist = blacklist;
    }

    @Override
    public boolean apply(ItemStack input) {
        return super.apply(input) && !blacklist.test(input);
    }

}
