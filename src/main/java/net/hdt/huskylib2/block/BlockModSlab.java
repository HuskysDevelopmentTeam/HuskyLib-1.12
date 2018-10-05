package net.hdt.huskylib2.block;

import net.hdt.huskylib2.interf.IModBlock;
import net.hdt.huskylib2.item.ItemModBlockSlab;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Random;

public abstract class BlockModSlab extends BlockSlab implements IModBlock {

    public static final PropertyEnum prop = PropertyEnum.create("prop", DummyEnum.class);
    public static HashMap<BlockModSlab, BlockModSlab> halfSlabs = new HashMap<>();
    public static HashMap<BlockModSlab, BlockModSlab> fullSlabs = new HashMap<>();
    static boolean tempDoubleSlab;
    boolean doubleSlab;
    private String[] variants;
    private String bareName;
    private CreativeTabs creativeTabs;

    public BlockModSlab(String name, Material materialIn, boolean doubleSlab) {
        super(hacky(materialIn, doubleSlab));

        this.doubleSlab = doubleSlab;
        if (doubleSlab)
            name += "_double";

        variants = new String[]{name};
        bareName = name;

        setTranslationKey(getPrefix() + name);
        if (!doubleSlab) {
            useNeighborBrightness = true;
            setDefaultState(blockState.getBaseState().withProperty(HALF, EnumBlockHalf.BOTTOM).withProperty(prop, DummyEnum.BLARG));
        }
    }

    public BlockModSlab(Builder properties, String name, boolean doubleSlab) {
        super(hacky(properties.material, doubleSlab), properties.mapColor);

        this.blockSoundType = properties.soundType;
        this.lightValue = properties.lightValue;
        this.blockResistance = properties.resistance;
        this.blockHardness = properties.hardness;
        this.needsRandomTick = properties.needsRandomTick;
        this.slipperiness = properties.slipperiness;

        this.doubleSlab = doubleSlab;
        if (doubleSlab)
            name += "_double";

        variants = new String[]{name};
        bareName = name;

        setTranslationKey(getPrefix() + name);
        if (!doubleSlab) {
            useNeighborBrightness = true;
            setDefaultState(blockState.getBaseState().withProperty(HALF, EnumBlockHalf.BOTTOM).withProperty(prop, DummyEnum.BLARG));
        }
    }

    public static Material hacky(Material m, boolean doubleSlab) {
        tempDoubleSlab = doubleSlab;
        return m;
    }

    public static void initSlab(Block base, int meta, BlockModSlab half, BlockModSlab full) {
        fullSlabs.put(half, full);
        fullSlabs.put(full, full);
        halfSlabs.put(half, half);
        halfSlabs.put(full, half);

        half.register();
        full.register();

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(half, 6),
                "BBB",
                'B', ProxyRegistry.newStack(base, 1, meta));
    }

    @Override
    public BlockStateContainer createBlockState() {
        return tempDoubleSlab ? new BlockStateContainer(this, getVariantProp()) : new BlockStateContainer(this, HALF, getVariantProp());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        if (doubleSlab)
            return getDefaultState();
        else return getDefaultState().withProperty(HALF, meta == 8 ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        if (doubleSlab)
            return 0;
        else return state.getValue(HALF) == EnumBlockHalf.TOP ? 8 : 0;
    }

    public BlockSlab getFullBlock() {
        return fullSlabs.get(this);
    }

    public BlockSlab getSingleBlock() {
        return halfSlabs.get(this);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(getSingleBlock());
    }

    @Override
    public Item getItemDropped(IBlockState p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(getSingleBlock());
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return super.quantityDropped(state, fortune, random);
    }

    public void register() {
        setRegistryName(getPrefix() + bareName);
        ProxyRegistry.register(this);
        if (!isDouble())
            ProxyRegistry.register(new ItemModBlockSlab(this, new ResourceLocation(getPrefix() + bareName)));
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
        return doubleSlab ? new IProperty[]{prop, HALF} : new IProperty[]{prop};
    }

    @Override
    public boolean isDouble() {
        return doubleSlab;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return isDouble();
    }

    @Override
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        IBlockState state = getActualState(base_state, world, pos);
        return isDouble()
                || (state.getValue(BlockSlab.HALF) == EnumBlockHalf.TOP && side == EnumFacing.UP)
                || (state.getValue(BlockSlab.HALF) == EnumBlockHalf.BOTTOM && side == EnumFacing.DOWN);
    }

    @Override
    public IProperty<?> getVariantProp() {
        return prop;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return prop;
    }

    @Override
    public Class getVariantEnum() {
        return DummyEnum.class;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return DummyEnum.BLARG;
    }

    public enum DummyEnum implements BlockMetaVariants.EnumBase {
        BLARG
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
