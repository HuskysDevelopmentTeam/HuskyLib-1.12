package net.thegaminghuskymc.huskylib2.testmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.thegaminghuskymc.huskylib2.testmod.proxy.CommonProxy;

import java.util.logging.Logger;

@Mod(modid = Refs.MODID, name = Refs.NAME, version = Refs.VERSION, acceptedMinecraftVersions = Refs.ACC_MC)
public class HuskyLibTestMod {

    @Instance(value = Refs.MODID)
    public static HuskyLibTestMod instance = new HuskyLibTestMod();

    @SidedProxy(clientSide = Refs.CSIDE, serverSide = Refs.SSIDE)
    public static CommonProxy proxy;

    public static Logger logger = Logger.getLogger(Refs.NAME);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
