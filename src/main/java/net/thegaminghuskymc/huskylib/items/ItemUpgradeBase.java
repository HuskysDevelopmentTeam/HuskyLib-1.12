package net.thegaminghuskymc.huskylib.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thegaminghuskymc.huskylib.utils.Names;

public class ItemUpgradeBase extends ItemBase{

	public ItemUpgradeBase(String name, CreativeTabs creativetab) {
		super(name + "_upgrade", creativetab);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(Names.UpgradeToolTips.UPGRADE_BASE);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		
	}

}
