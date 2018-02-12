package net.thegaminghuskymc.huskylib2.lib.block_module.blocks.item_blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.thegaminghuskymc.huskylib2.lib.block_module.blocks.BlockModSlab;
import net.thegaminghuskymc.huskylib2.lib.interf.IModBlock;
import net.thegaminghuskymc.huskylib2.lib.interf.IVariantHolder;
import net.thegaminghuskymc.huskylib2.lib.item_module.items.ItemMod;

public class ItemModBlockSlab extends ItemSlab implements IVariantHolder {
    private IModBlock modBlock;

    public ItemModBlockSlab(Block block, ResourceLocation name) {
        super(block, ((BlockModSlab) block).getSingleBlock(), ((BlockModSlab) block).getFullBlock());
        this.modBlock = (IModBlock) block;
        ItemMod.variantHolders.add(this);
        if (this.getVariants().length > 1) {
            this.setHasSubtypes(true);
        }

        this.setRegistryName(name);
    }

    public int getMetadata(int damage) {
        return damage;
    }

    public ItemBlock setUnlocalizedName(String par1Str) {
        return (ItemBlock) super.setUnlocalizedName(par1Str);
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int dmg = par1ItemStack.getItemDamage();
        String[] variants = this.getVariants();
        String name;
        if (dmg >= variants.length) {
            name = this.modBlock.getBareName();
        } else {
            name = variants[dmg];
        }

        return "tile." + name;
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        String[] variants = this.getVariants();
        if (this.isInCreativeTab(tab)) {
            for (int i = 0; i < variants.length; ++i) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }

    }

    public String[] getVariants() {
        return this.modBlock.getVariants();
    }

    public ItemMeshDefinition getCustomMeshDefinition() {
        return this.modBlock.getCustomMeshDefinition();
    }

    public EnumRarity getRarity(ItemStack stack) {
        return this.modBlock.getBlockRarity(stack);
    }

    public String getModNamespace() {
        return this.modBlock.getModNamespace();
    }
}