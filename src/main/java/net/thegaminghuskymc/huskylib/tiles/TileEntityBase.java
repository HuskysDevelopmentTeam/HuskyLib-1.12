package net.thegaminghuskymc.huskylib.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityBase extends TileEntity {
	
	protected static final String NBT_DIRECTION = "Direction";

    private EnumFacing direction = EnumFacing.NORTH;
    
    public void setDirection(EnumFacing direction) {
        this.direction = direction;

        world.notifyNeighborsOfStateChange(pos, world.getBlockState(pos).getBlock(), true);

        markDirty();
    }

    public EnumFacing getDirection() {
        return direction;
    }

}
