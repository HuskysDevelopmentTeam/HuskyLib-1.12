package net.hdt.huskylib2.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.model.ModelLoader;

public abstract class MRSlab extends BlockSlab {

    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.create("variant", Variant.class);

    protected String name;

    public MRSlab(String name, Material material) {
        super(material);
        this.setRegistryName(name);
        this.setTranslationKey(name);

        this.name = name;

        IBlockState iblockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

        if(!this.isDouble()) {
            iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate);
        this.useNeighborBrightness = !this.isDouble();
    }

    public void registerItemModel(Item itemBlock) {
        ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(name, "inventory"));
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public String getTranslationKey(int meta) {
        return super.getTranslationKey();
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return Variant.DEFAULT;
    }

    @Override
    public final IBlockState getStateFromMeta(final int meta) {
        IBlockState blockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

        if(!this.isDouble()) {
            blockstate = blockstate.withProperty(HALF, ((meta&8)!=0)?EnumBlockHalf.TOP:EnumBlockHalf.BOTTOM);
        }

        return blockstate;
    }

    @Override
    public final int getMetaFromState(final IBlockState state) {
        int meta = 0;

        if(!this.isDouble()&& state.getValue(HALF)==EnumBlockHalf.TOP) {
            meta |= 8;
        }

        return meta;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        if(!this.isDouble()){
            return new BlockStateContainer(this, VARIANT, HALF);
        }
        return new BlockStateContainer(this, VARIANT);
    }

    public static class Double extends MRSlab
    {
        public float slipperiness;

        public Double(String name, Material material, CreativeTabs creativeTabs, float slipperiness) {
            super(name, material);
            setCreativeTab(creativeTabs);
            setSlipperiness(slipperiness);

            this.slipperiness = slipperiness;
        }

        @Override
        public boolean isDouble() {
            return true;
        }

        public void setSlipperiness(float slipperiness)
        {
            this.slipperiness = slipperiness;
        }
    }

    public static class Half extends MRSlab
    {
        public float slipperiness;

        public Half(String name, Material material, CreativeTabs creativeTabs, float slipperiness) {
            super(name, material);
            setCreativeTab(creativeTabs);
            setSlipperiness(slipperiness);

            this.slipperiness = slipperiness;
        }

        @Override
        public boolean isDouble() {
            return false;
        }

        public void setSlipperiness(float slipperiness)
        {
            this.slipperiness = slipperiness;
        }
    }

    public enum Variant implements IStringSerializable
    {
        DEFAULT;

        @Override
        public String getName() {
            return "default";
        }
    }
}