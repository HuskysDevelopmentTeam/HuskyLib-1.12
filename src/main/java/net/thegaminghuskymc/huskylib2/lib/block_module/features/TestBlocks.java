package net.thegaminghuskymc.huskylib2.lib.block_module.features;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.thegaminghuskymc.huskylib2.lib.block_module.blocks.BlockMod;
import net.thegaminghuskymc.huskylib2.lib.block_module.blocks.BlockModPillar;
import net.thegaminghuskymc.huskylib2.lib.modules.Feature;

public class TestBlocks extends Feature {

    private BlockMod test;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        test = new BlockModPillar("test", Material.ROCK);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }
}
