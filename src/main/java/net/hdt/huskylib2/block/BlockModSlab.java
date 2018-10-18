package net.hdt.huskylib2.block;

import net.hdt.huskylib2.interf.IModBlock;
import net.hdt.huskylib2.interf.IModBlockAlt;
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

    static boolean tempDoubleSlab;

    public static final PropertyEnum prop = PropertyEnum.create("prop", DummyEnum.class);

    public static HashMap<BlockModSlab, BlockModSlab> halfSlabs = new HashMap<>();
    public static HashMap<BlockModSlab, BlockModSlab> fullSlabs = new HashMap<>();
    boolean doubleSlab;
    private String[] variants;
    private String bareName;

    public BlockModSlab(String name, Material materialIn, boolean doubleSlab) {
        super(materialIn);

        this.doubleSlab = doubleSlab;
        if (doubleSlab)
            name += "_double";

        variants = new String[]{name};
        bareName = name;

        setTranslationKey(name);
        if (!doubleSlab) {
            useNeighborBrightness = true;
            setDefaultState(blockState.getBaseState().withProperty(HALF, EnumBlockHalf.BOTTOM).withProperty(prop, DummyEnum.BLARG));
        }
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
        return doubleSlab ? new BlockStateContainer(this, getVariantProp()) : new BlockStateContainer(this, HALF, getVariantProp());
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
        return doubleSlab ? new IProperty[] { prop, HALF } : new IProperty[] { prop };
    }

    @Override
    public String getTranslationKey(int meta) {
        return getTranslationKey();
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

    public static enum DummyEnum implements BlockMetaVariants.EnumBase {
        BLARG
    }

}
