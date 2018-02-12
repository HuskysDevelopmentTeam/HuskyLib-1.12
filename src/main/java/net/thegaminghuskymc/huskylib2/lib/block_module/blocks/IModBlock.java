package net.thegaminghuskymc.huskylib2.lib.block_module.blocks;


import net.thegaminghuskymc.huskylib2.lib.lib.LibMisc;

public interface IModBlock extends net.thegaminghuskymc.huskylib2.lib.interf.IModBlock {

    @Override
    default String getModNamespace() {
        return LibMisc.MOD_ID;
    }

}
