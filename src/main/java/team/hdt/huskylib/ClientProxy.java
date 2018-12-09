package team.hdt.huskylib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.huskylib.util.ClientTicker;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(ClientTicker.class);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

}
