package net.thegaminghuskymc.huskylib;

import java.util.Objects;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.thegaminghuskymc.huskylib.blocks.BlockTestBlock;
import net.thegaminghuskymc.huskylib.items.blocks.ItemBlockBase;
import net.thegaminghuskymc.huskylib.proxy.IHuskyProxy;
import net.thegaminghuskymc.huskylib.utils.Refs;

@Mod(modid = Refs.MODID, name = Refs.NAME, version = Refs.VERSION, dependencies = Refs.DEPS, acceptedMinecraftVersions = Refs.ACC_MC)
public class HuskyLib {
	
	@Instance(value = Refs.MODID)
	public static HuskyLib instance = new HuskyLib();

    @SidedProxy(clientSide = Refs.CSIDE, serverSide = Refs.SSIDE)
    public static IHuskyProxy proxy;
    
    public static IHuskyLogger huskyLogger;
    public static Logger logger = Logger.getLogger(Refs.NAME);
    
    public static Block test_block;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	proxy.preInit(event);
    	
    	test_block = new BlockTestBlock();
    	ForgeRegistries.BLOCKS.register(test_block);
    	ForgeRegistries.ITEMS.register(new ItemBlockBase(test_block).setRegistryName(Objects.requireNonNull(test_block.getRegistryName())));
    	
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
