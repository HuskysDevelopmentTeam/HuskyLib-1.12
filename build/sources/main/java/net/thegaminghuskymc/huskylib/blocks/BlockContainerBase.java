package net.thegaminghuskymc.huskylib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thegaminghuskymc.huskylib.tiles.TileEntityBase;

public class BlockContainerBase extends BlockBase implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    private Class<? extends TileEntity> tileEntityClass;
    private boolean isRedstoneEmitter;
    
    public BlockContainerBase(String modid, String name, Material material, CreativeTabs creativeTabs, Class<? extends TileEntity> tileEntityClass) {
        super(modid, name, creativeTabs);
        isBlockContainer = true;
        setTileEntityClass(tileEntityClass);
    }
    
    public BlockContainerBase(String modid, String name, Material material, CreativeTabs creativeTabs) {
    	super(modid, name, creativeTabs);
        isBlockContainer = true;
        setTileEntityClass(TileEntityBase.class);
    }


    public BlockContainerBase setTileEntityClass(Class<? extends TileEntity> tileEntityClass) {
        this.tileEntityClass = tileEntityClass;
        return this;
    }

    public BlockContainerBase emitsRedstone() {
        setRedstoneEmitter(true);
        return this;
    }

    protected Class<? extends TileEntity> getTileEntity() {
        return tileEntityClass;
    }

    protected TileEntityBase get(IBlockAccess w, BlockPos pos) {
        TileEntity te = w.getTileEntity(pos);
        return (TileEntityBase) te;
    }

    protected boolean canRotateVertical() {
        return true;
    }

    @Override
    public Block setUnlocalizedName(String name) {
        super.setUnlocalizedName(name);
        return this;
    }

    public boolean isRedstoneEmitter() {
        return isRedstoneEmitter;
    }

    public void setRedstoneEmitter(boolean isRedstoneEmitter) {
        this.isRedstoneEmitter = isRedstoneEmitter;
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBase();
    }

}