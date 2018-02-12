package net.thegaminghuskymc.huskylib2.lib;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thegaminghuskymc.huskylib2.lib.lib.LibMisc;
import net.thegaminghuskymc.huskylib2.lib.proxy.CommonProxy;
import net.thegaminghuskymc.huskylib2.lib.tiles.TileEntitySkull;
import net.thegaminghuskymc.huskylib2.lib.utils.Refs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.VERSION, guiFactory = LibMisc.GUI_FACTORY)
public class HuskyLib {

    public static final Logger logger = LogManager.getFormatterLogger(LibMisc.MOD_ID);
    @Instance(value = Refs.MODID)
    public static HuskyLib instance = new HuskyLib();
    @SidedProxy(clientSide = LibMisc.PROXY_CLIENT, serverSide = LibMisc.PROXY_COMMON)
    public static CommonProxy proxy;

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
