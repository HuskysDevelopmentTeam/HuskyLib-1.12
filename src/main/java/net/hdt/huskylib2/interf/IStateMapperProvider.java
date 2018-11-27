package net.hdt.huskylib2.interf;

import net.hdt.huskylib2.block.property.PropertyString;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IStateMapperProvider {

    public static final PropertyString TEXTURE = new PropertyString("texture");

    @SideOnly(Side.CLIENT)
    public IStateMapper getStateMapper();

}
