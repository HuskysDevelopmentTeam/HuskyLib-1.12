package net.thegaminghuskymc.huskylib.blocks;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.thegaminghuskymc.huskylib.tiles.TileEntityBase;
import net.thegaminghuskymc.huskylib.utils.Names;
import net.thegaminghuskymc.huskylib.utils.StringHelper;

public class BlockGeneratorBase extends BlockMachineBase{

	public BlockGeneratorBase(String modid, String name, CreativeTabs creativeTabs) {
		super(modid, name + "_generator", new TileEntityBase(), creativeTabs);
	}
	
	public BlockGeneratorBase(String modid, String name, TileEntity te, CreativeTabs creativeTabs) {
		super(modid, name + "_generator", te, creativeTabs);
	}
	
	@Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        if (!StringHelper.isShiftKeyDown()) {
            tooltip.add(Names.HelpToolTips.SHIFT_FOR_INFO);
        } else {
        	tooltip.add(Names.CraftingToolTips.GENERATOR);
        }
    }

}
