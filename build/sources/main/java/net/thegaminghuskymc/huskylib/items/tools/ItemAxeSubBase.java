package net.thegaminghuskymc.huskylib.items.tools;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thegaminghuskymc.huskylib.utils.Names;
import net.thegaminghuskymc.huskylib.utils.StringHelper;

public class ItemAxeSubBase extends ItemAxeBase{
	
	private String itemName;
	private String[] subNames;
	
	public ItemAxeSubBase(ToolMaterial material, String name, CreativeTabs tabs) {
		super(material, name, tabs);
		this.itemName = name;
		this.subNames = null;
	}
	
	public ItemAxeSubBase(ToolMaterial material, String name, CreativeTabs tabs, String... subNames) {
		this(material, name, tabs);
		this.itemName = name;
		this.subNames = subNames;
	}
	
	@Override
    public int getDamage(ItemStack stack) {
        if(this.subNames != null){
            return stack.getMetadata();
        }
        else{
            return super.getDamage(stack);
        }
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if(this.subNames != null){
        	if(!isInCreativeTab(tab)){
        		return;
        	}
            for(int i = 0; i < this.subNames.length; i++){
                subItems.add(new ItemStack(this, 1, i));
            }
        }
        else{
            subItems.add(new ItemStack(this, 1, 0));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getUnlocalizedName(ItemStack stack) {
        if(this.subNames != null){
            return this.getUnlocalizedName() + "." + this.subNames[stack.getMetadata()];
        }
        else{
            return super.getUnlocalizedName(stack);
        }
    }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(!StringHelper.isShiftKeyDown()){
			tooltip.add(Names.HelpToolTips.SHIFT_FOR_INFO);
		}
		else
			tooltip.add(Names.ItemToolTips.WIP_AXE);
	}

}
