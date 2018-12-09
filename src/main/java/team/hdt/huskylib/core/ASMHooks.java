package team.hdt.huskylib.core;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.MinecraftForge;
import team.hdt.huskylib.event.StructureSetBlockEvent;

public class ASMHooks {

    public static final String HOOKS = "team/hdt/huskylib/core/ASMHooks";

    public static IBlockState onStructureSetBlock(StructureComponent component, World world, BlockPos pos, int x, int y, int z, IBlockState state) {
        StructureSetBlockEvent event = new StructureSetBlockEvent(component, world, pos, new BlockPos(x, y, z), state);
        MinecraftForge.EVENT_BUS.post(event);
        return event.getState();
    }
}
