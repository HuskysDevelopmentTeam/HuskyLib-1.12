package net.hdt.huskylib2.item;

import net.hdt.huskylib2.interf.IVariantHolder;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class ItemModArmor extends ItemArmor implements IVariantHolder {

    private final String bareName;

    public ItemModArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);

        setTranslationKey(name);
        bareName = name;
        ItemMod.variantHolders.add(this);
        setCreativeTab(CreativeTabs.COMBAT);
    }

    @Override
    public Item setTranslationKey(String name) {
        super.setTranslationKey(name);
        setRegistryName(new ResourceLocation(getPrefix() + name));
        ProxyRegistry.register(this);

        return this;
    }

    @Override
    public String getTranslationKey(ItemStack par1ItemStack) {
        par1ItemStack.getItemDamage();

        return "item." + getPrefix() + bareName;
    }

    @Override
    public String[] getVariants() {
        return new String[]{bareName};
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

}
