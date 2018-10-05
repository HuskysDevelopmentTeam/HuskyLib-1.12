package net.hdt.huskylib2.block;

import net.hdt.huskylib2.interf.IModBlock;
import net.hdt.huskylib2.item.ItemModBlock;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockModStairs extends BlockStairs implements IModBlock {

    private final String[] variants;
    private final String bareName;
    private CreativeTabs creativeTabs;

    public BlockModStairs(String name, IBlockState state) {
        super(state);

        variants = new String[]{name};
        bareName = name;

        register(name);
        useNeighborBrightness = true;
        setCreativeTab(getCreativeTabs());
    }

    public BlockModStairs(Builder properties, String name, IBlockState state) {
        super(state);

        variants = new String[]{name};
        bareName = name;

        register(name);
        useNeighborBrightness = true;
        setCreativeTab(getCreativeTabs());
    }

    public static void initStairs(Block base, int meta, BlockStairs block) {
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(block, 4),
                "B  ", "BB ", "BBB",
                'B', ProxyRegistry.newStack(base, 1, meta));
    }

    public Block register(String name) {
        setTranslationKey(getPrefix() + name);
        setRegistryName(getPrefix() + name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(new ItemModBlock(this, new ResourceLocation(getPrefix() + name)));
        return this;
    }

    public CreativeTabs getCreativeTabs() {
        if (creativeTabs == null) {
            return CreativeTabs.SEARCH;
        } else {
            return creativeTabs;
        }
    }

    public void setCreativeTabs(CreativeTabs creativeTabs) {
        this.creativeTabs = creativeTabs;
    }

    @Override
    public String getBareName() {
        return bareName;
    }

    @Override
    public String[] getVariants() {
        return variants;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

    @Override
    public EnumRarity getBlockRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[0];
    }

    @Override
    public IProperty getVariantProp() {
        return null;
    }

    @Override
    public Class getVariantEnum() {
        return null;
    }

    public static class Builder {
        private Material material;
        private MapColor mapColor;
        private SoundType soundType = SoundType.STONE;
        private int lightValue;
        private float resistance;
        private float hardness;
        private boolean needsRandomTick;
        private float slipperiness = 0.6F;

        private Builder(Material materialIn, MapColor mapColorIn) {
            this.material = materialIn;
            this.mapColor = mapColorIn;
        }

        public static Builder create(Material materialIn) {
            return create(materialIn, materialIn.getMaterialMapColor());
        }

        public static Builder create(Material materialIn, MapColor mapColorIn) {
            return new Builder(materialIn, mapColorIn);
        }

        public static Builder from(Block blockIn) {
            Builder block$builder = new Builder(blockIn.getMaterial(blockIn.getDefaultState()), blockIn.getMaterial(blockIn.getDefaultState()).getMaterialMapColor());
            block$builder.material = blockIn.getMaterial(blockIn.getDefaultState());
            block$builder.needsRandomTick = blockIn.getTickRandomly();
            block$builder.lightValue = blockIn.getLightValue(blockIn.getDefaultState());
            block$builder.material = blockIn.getMaterial(blockIn.getDefaultState());
            block$builder.mapColor = blockIn.getMaterial(blockIn.getDefaultState()).getMaterialMapColor();
            block$builder.soundType = blockIn.getSoundType();
            return block$builder;
        }

        public Builder slipperiness(float slipperinessIn) {
            this.slipperiness = slipperinessIn;
            return this;
        }

        protected Builder sound(SoundType soundTypeIn) {
            this.soundType = soundTypeIn;
            return this;
        }

        protected Builder lightValue(int lightValueIn) {
            this.lightValue = lightValueIn;
            return this;
        }

        public Builder hardnessAndResistance(float hardnessIn, float resistanceIn) {
            this.hardness = hardnessIn;
            this.resistance = Math.max(0.0F, resistanceIn);
            return this;
        }

        protected Builder zeroHardnessAndResistance() {
            return this.hardnessAndResistance(0.0F);
        }

        protected Builder hardnessAndResistance(float hardnessAndResistance) {
            this.hardnessAndResistance(hardnessAndResistance, hardnessAndResistance);
            return this;
        }

        protected Builder needsRandomTick() {
            this.needsRandomTick = true;
            return this;
        }

    }

}
