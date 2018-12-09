package team.hdt.huskylib.item;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import team.hdt.huskylib.interf.IVariantHolder;
import team.hdt.huskylib.util.ProxyRegistry;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemMod extends Item implements IVariantHolder {

    public static final List<IVariantHolder> variantHolders = new ArrayList<>();

    private final String[] variants;
    private final String bareName;

    public ItemMod(String name, String... variants) {
        register(name);
        if (variants.length > 1)
            setHasSubtypes(true);

        if (variants.length == 0)
            variants = new String[]{name};

        bareName = name;
        this.variants = variants;
        variantHolders.add(this);
    }

    public Item register(String name) {
        setTranslationKey(name);
        setRegistryName(new ResourceLocation(getPrefix() + name));
        ProxyRegistry.register(this);

        return this;
    }

    @Override
    public String getTranslationKey(ItemStack par1ItemStack) {
        int dmg = par1ItemStack.getItemDamage();
        String[] variants = getVariants();

        String name;
        if (dmg >= variants.length)
            name = bareName;
        else name = variants[dmg];

        return "item." + getPrefix() + name;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (isInCreativeTab(tab))
            for (int i = 0; i < getVariants().length; i++)
                subItems.add(new ItemStack(this, 1, i));
    }

    @Override
    public String[] getVariants() {
        return variants;
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

}
