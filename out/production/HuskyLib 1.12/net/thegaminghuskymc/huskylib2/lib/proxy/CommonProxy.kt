package net.thegaminghuskymc.huskylib2.lib.proxy

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.thegaminghuskymc.huskylib2.lib.utils.Refs

@Mod.EventBusSubscriber(modid = Refs.MODID)
open class CommonProxy {

    @SubscribeEvent
    open fun preInit(event: FMLPreInitializationEvent) {

    }

    @SubscribeEvent
    open fun init(event: FMLInitializationEvent) {

    }

    @SubscribeEvent
    open fun postInit(event: FMLPostInitializationEvent) {

    }

}
