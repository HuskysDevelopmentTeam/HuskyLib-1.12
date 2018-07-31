package net.hdt.huskylib2.interf;

import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModBlock extends IVariantHolder, IVariantEnumHolder, IStateMapperProvider {

	public String getBareName();

	public IProperty getVariantProp();

	public IProperty[] getIgnoredProperties();

	public EnumRarity getBlockRarity(ItemStack stack);

	public default boolean shouldDisplayVariant(int variant) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public default IStateMapper getStateMapper() {
		return null;
	}

}
