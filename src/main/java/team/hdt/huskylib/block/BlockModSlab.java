package team.hdt.huskylib.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.huskylib.interf.IModBlock;
import team.hdt.huskylib.item.ItemModBlockSlab;
import team.hdt.huskylib.recipe.RecipeHandler;
import team.hdt.huskylib.util.ProxyRegistry;

import java.util.HashMap;
import java.util.Random;

public abstract class BlockModSlab extends BlockSlab implements IModBlock {

    private static final PropertyEnum<Variant> VARIANT = PropertyEnum.create("variant", Variant.class);

    private static HashMap<BlockModSlab, BlockModSlab> halfSlabs = new HashMap<>();
    private static HashMap<BlockModSlab, BlockModSlab> fullSlabs = new HashMap<>();
    private boolean doubleSlab;
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

        IBlockState iblockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

        if (!doubleSlab) {
            iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate);
        this.useNeighborBrightness = !this.isDouble();
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
        return isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, VARIANT, HALF);
    }

    @Override
    public final IBlockState getStateFromMeta(final int meta) {
        IBlockState blockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

        if (!this.isDouble()) {
            blockstate = blockstate.withProperty(HALF, ((meta & 8) != 0) ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
        }

        return blockstate;
    }

    @Override
    public final int getMetaFromState(final IBlockState state) {
        int meta = 0;

        if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
            meta |= 8;
        }

        return meta;
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[0];
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
    public String getTranslationKey(int meta) {
        return getTranslationKey();
    }

    @Override
    public boolean isDouble() {
        return doubleSlab;
    }

    @Override
    public IProperty<?> getVariantProp() {
        return VARIANT;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Class getVariantEnum() {
        return Variant.class;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return Variant.DEFAULT;
    }

    public enum Variant implements IStringSerializable {
        DEFAULT;

        @Override
        public String getName() {
            return "default";
        }
    }

}