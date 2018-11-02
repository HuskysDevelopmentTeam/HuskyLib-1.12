package net.hdt.huskylib2;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = HuskyLib.MOD_ID, name = HuskyLib.MOD_NAME, version = HuskyLib.VERSION, acceptedMinecraftVersions = HuskyLib.ACCEPTED_MC_VERSIONS)
public class HuskyLib {

    public static final String MOD_ID = "huskylib2";
    public static final String MOD_NAME = "HuskyLib-1.12.2";
    static final String VERSION = "1.5.8";
    static final String ACCEPTED_MC_VERSIONS = "[1.12.2, 1.13]";

    private static final String PROXY_COMMON = "net.hdt.huskylib2.CommonProxy";
    private static final String PROXY_CLIENT = "net.hdt.huskylib2.ClientProxy";

    @Mod.Instance(value = MOD_ID)
    public static HuskyLib instance = new HuskyLib();

    public static Logger LOG = LogManager.getLogger(MOD_ID);

    public static String[] STRUCTURECOMPONENT_SETBLOCKSTATE = new String[]{"setBlockState", "func_175811_a", "a", "(Lnet/minecraft/world/World;Lnet/minecraft/block/state/IBlockState;IIILnet/minecraft/world/gen/structure/StructureBoundingBox;)V"};

    @SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_COMMON)
    private static CommonProxy proxy;

    public static void log(String str) {
        LOG.info("[{} ASM] {}", MOD_ID, str);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}