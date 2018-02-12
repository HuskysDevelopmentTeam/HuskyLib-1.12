package net.thegaminghuskymc.huskylib2.testmod.proxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thegaminghuskymc.huskylib2.lib.utils.Refs;

@Mod.EventBusSubscriber(modid = Refs.MODID)
public class CommonProxy {

    @SubscribeEvent
    public void preInit(FMLPreInitializationEvent event) {

    }

    @SubscribeEvent
    public void init(FMLInitializationEvent event) {

    }

    @SubscribeEvent
    public void postInit(FMLPostInitializationEvent event) {

    }

}
