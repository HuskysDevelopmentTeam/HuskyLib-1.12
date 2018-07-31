package net.hdt.huskylib2;

import net.hdt.huskylib2.util.ClientTicker;
import net.hdt.huskylib2.util.ModelHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		MinecraftForge.EVENT_BUS.register(ModelHandler.class);
		MinecraftForge.EVENT_BUS.register(ClientTicker.class);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		ModelHandler.init();
	}
	
}
