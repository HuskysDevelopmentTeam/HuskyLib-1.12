package team.hdt.huskylib.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.huskylib.interf.IModBlock;
import team.hdt.huskylib.item.ItemModBlock;
import team.hdt.huskylib.util.ProxyRegistry;

public abstract class BlockModFalling extends BlockFalling implements IModBlock {

    private final String[] variants;
    private final String bareName;
    private CreativeTabs creativeTabs;

    public BlockModFalling(String name, String... variants) {
        if (variants.length == 0)
            variants = new String[]{name};

        bareName = name;
        this.variants = variants;

        if (registerInConstruction())
            register(name);
    }

    public BlockModFalling(Builder properties, String name, String... variants) {
        super(properties.material);
        this.blockSoundType = properties.soundType;
        this.lightValue = properties.lightValue;
        this.blockResistance = properties.resistance;
        this.blockHardness = properties.hardness;
        this.needsRandomTick = properties.needsRandomTick;
        this.slipperiness = properties.slipperiness;

        if (variants.length == 0)
            variants = new String[]{name};

        bareName = name;
        this.variants = variants;

        if (registerInConstruction())
            register(name);
    }

    public Block register(String name) {
        setTranslationKey(getPrefix() + name);
        setRegistryName(getPrefix() + name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(createItemBlock(new ResourceLocation(getPrefix() + name)));
        return this;
    }

    public ItemBlock createItemBlock(ResourceLocation res) {
        return new ItemModBlock(this, res);
    }

    public boolean registerInConstruction() {
        return true;
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
