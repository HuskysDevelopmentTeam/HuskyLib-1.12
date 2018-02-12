package net.thegaminghuskymc.huskylib2.lib.interf;

import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IStateMapperProvider {

    @SideOnly(Side.CLIENT)
    public IStateMapper getStateMapper();

}
