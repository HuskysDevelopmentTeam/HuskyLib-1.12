package net.thegaminghuskymc.huskylib.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSubBase extends ItemBase{
	
	private String itemName;
	private String[] subNames;
	
	public ItemSubBase(String name, CreativeTabs tabs) {
		super(name, tabs);
		this.itemName = name;
		this.subNames = null;
	}
	
	public ItemSubBase(String name, CreativeTabs tabs, String... subNames) {
		this(name, tabs);
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
        if(this.subNames != null & isInCreativeTab(tab)){
            for(int i = 0; i < this.subNames.length; i++){
                subItems.add(new ItemStack(this, 1, i));
            }
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

}