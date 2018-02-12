package net.thegaminghuskymc.huskylib2.lib.tiles;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityMachineBase extends TileEntityBase {

    @Override
    public void readFromNBT(NBTTagCompound compound) {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return compound;
    }

    @Override
    public boolean canRenderBreaking() {
        return true;
    }

}
