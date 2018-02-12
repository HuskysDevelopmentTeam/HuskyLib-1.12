package net.thegaminghuskymc.huskylib2.lib.tiles

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing

open class TileEntityBase : TileEntity() {

    var direction = EnumFacing.NORTH
        set(direction) {
            field = direction

            world.notifyNeighborsOfStateChange(pos, world.getBlockState(pos).block, true)

            markDirty()
        }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
    }

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        return tag
    }

    override fun getUpdateTag(): NBTTagCompound {
        return this.writeToNBT(NBTTagCompound())
    }

    override fun getUpdatePacket(): SPacketUpdateTileEntity? {
        return SPacketUpdateTileEntity(this.pos, 255, this.updateTag)
    }

    override fun onDataPacket(manager: NetworkManager?, packet: SPacketUpdateTileEntity?) {
        this.readFromNBT(packet!!.nbtCompound)
    }

    override fun markDirty() {
        super.markDirty()

        if (this.world != null) {
            val state = getWorld().getBlockState(this.pos)

            if (state != null) {
                state.block.updateTick(this.world, this.pos, state, this.world.rand)
                this.world.notifyBlockUpdate(pos, state, state, 3)
            }
        }
    }

    companion object {

        protected val NBT_DIRECTION = "Direction"
    }

}
