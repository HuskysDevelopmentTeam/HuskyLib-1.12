package team.hdt.huskylib.interf;

import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.huskylib.block.property.PropertyString;

public interface IStateMapperProvider {

    public static final PropertyString TEXTURE = new PropertyString("texture");

    @SideOnly(Side.CLIENT)
    public IStateMapper getStateMapper();

}
