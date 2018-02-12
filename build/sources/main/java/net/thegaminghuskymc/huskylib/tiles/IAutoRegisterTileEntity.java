package net.thegaminghuskymc.huskylib.tiles;

import net.minecraft.tileentity.TileEntity;

public interface IAutoRegisterTileEntity
{
	Class<? extends TileEntity> getTileEntityClass();

	String getTileEntityRegistryName();
}