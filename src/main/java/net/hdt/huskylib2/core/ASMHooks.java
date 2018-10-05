package net.hdt.huskylib2.core;

import net.hdt.huskylib2.event.StructureSetBlockEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.MinecraftForge;

public class ASMHooks {

    public static final String HOOKS = "net/hdt/huskylib2/core/ASMHooks";

    public static IBlockState onStructureSetBlock(StructureComponent component, World world, BlockPos pos, int x, int y, int z, IBlockState state) {
        StructureSetBlockEvent event = new StructureSetBlockEvent(component, world, pos, new BlockPos(x, y, z), state);
        MinecraftForge.EVENT_BUS.post(event);
        return event.getState();
    }
}
