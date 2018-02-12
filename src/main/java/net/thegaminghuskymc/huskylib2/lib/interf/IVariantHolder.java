package net.thegaminghuskymc.huskylib2.lib.interf;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.thegaminghuskymc.huskylib2.lib.lib.LibMisc.MOD_ID;

public interface IVariantHolder {

    String[] getVariants();

    @SideOnly(Side.CLIENT)
    ItemMeshDefinition getCustomMeshDefinition();

    default String getUniqueModel() {
        return null;
    }

    String getModNamespace();

    default String getPrefix() {
        return MOD_ID;
    }
}
