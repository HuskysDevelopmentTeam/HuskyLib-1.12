package team.hdt.huskylib.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import team.hdt.huskylib.interf.IModBlock;
import team.hdt.huskylib.interf.IVariantHolder;

public class ItemModBlock extends ItemBlock implements IVariantHolder {

    private IModBlock modBlock;

    public ItemModBlock(Block block, ResourceLocation res) {
        super(block);
        modBlock = (IModBlock) block;

        ItemMod.variantHolders.add(this);
        if (getVariants().length > 1)
            setHasSubtypes(true);
        setRegistryName(res);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public ItemBlock setTranslationKey(String par1Str) {
        return (ItemBlock) super.setTranslationKey(par1Str);
    }

    @Override
    public String getTranslationKey(ItemStack par1ItemStack) {
        int dmg = par1ItemStack.getItemDamage();
        String[] variants = getVariants();

        String name;
        if (dmg >= variants.length)
            name = modBlock.getBareName();
        else name = variants[dmg];

        return "tile." + getPrefix() + name;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        String[] variants = getVariants();
        if (isInCreativeTab(tab))
            for (int i = 0; i < variants.length; i++)
                if (modBlock.shouldDisplayVariant(i))
                    subItems.add(new ItemStack(this, 1, i));
    }

    @Override
    public String[] getVariants() {
        return modBlock.getVariants();
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
        return modBlock.getCustomMeshDefinition();
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return modBlock.getBlockRarity(stack);
    }

    @Override
    public String getModNamespace() {
        return modBlock.getModNamespace();
    }

}
