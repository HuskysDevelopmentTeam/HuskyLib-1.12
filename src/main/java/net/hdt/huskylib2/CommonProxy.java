package net.hdt.huskylib2;

import net.hdt.huskylib2.util.ItemTickHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) { 
		MinecraftForge.EVENT_BUS.register(ProxyRegistry.class);
		MinecraftForge.EVENT_BUS.register(ItemTickHandler.class);
	}

	public void init(FMLInitializationEvent event) {
		// NO-OP
	}

	public void postInit(FMLPostInitializationEvent event) {
		// NO-OP
	}
	
}
