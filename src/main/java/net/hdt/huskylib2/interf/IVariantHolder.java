package net.hdt.huskylib2.interf;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IVariantHolder {

	public String[] getVariants();

	@SideOnly(Side.CLIENT)
	public ItemMeshDefinition getCustomMeshDefinition();

	public default String getUniqueModel() {
		return null;
	}
	
	public String getModNamespace();

	public default String getPrefix() {
		return getModNamespace() + ":";
	}

}
