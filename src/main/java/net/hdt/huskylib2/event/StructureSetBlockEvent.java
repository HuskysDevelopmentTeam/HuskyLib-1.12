package net.hdt.huskylib2.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class StructureSetBlockEvent extends Event {

    private final StructureComponent component;
    private final World world;
    private final BlockPos pos;
    private final BlockPos relativePos;
    private IBlockState state;

    public StructureSetBlockEvent(StructureComponent component, World world, BlockPos pos, BlockPos relativePos, IBlockState state) {
        this.component = component;
        this.world = world;
        this.pos = pos;
        this.state = state;
        this.relativePos = relativePos;
    }

    public StructureComponent getComponent() {
        return component;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockPos getRelativePos() {
        return relativePos;
    }

    public IBlockState getState() {
        return state;
    }

    public void setState(IBlockState state) {
        this.state = state;
    }


}
