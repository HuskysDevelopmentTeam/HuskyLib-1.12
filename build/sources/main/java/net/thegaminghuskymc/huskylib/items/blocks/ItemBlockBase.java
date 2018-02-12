package net.thegaminghuskymc.huskylib.items.blocks;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thegaminghuskymc.huskylib.blocks.BlockSubBase;
import net.thegaminghuskymc.huskylib.blocks.EnumBlock;
import net.thegaminghuskymc.huskylib.utils.IShiftDescription;
import net.thegaminghuskymc.huskylib.utils.Names;

public class ItemBlockBase extends ItemBlock {

    public ItemBlockBase(Block block) {
        super(block);

        if(block instanceof BlockSubBase){
            this.setHasSubtypes(((EnumBlock)block).getSubNames() != null);
        }
    }

    @Override
    public int getMetadata(int damage) {
        if(this.block instanceof EnumBlock){
            return ((EnumBlock)this.block).getSubNames() != null ? damage : super.getMetadata(damage);
        }
        else{
            return super.getMetadata(damage);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
    	if(!isInCreativeTab(tab)){
    		return;
    	}
        this.block.getSubBlocks(tab, subItems);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if(this.block instanceof EnumBlock){
        	if(((EnumBlock)this.block).getSubNames() != null){
        		return this.getUnlocalizedName() + "." + ((EnumBlock)this.block).getSubNames()[stack.getMetadata()];
            }
            else{
            	return super.getUnlocalizedName(stack);
            }
        }
        else{
        	return super.getUnlocalizedName(stack);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        if(this.block instanceof IShiftDescription){
            if(((IShiftDescription)this.block).shouldAddDescription(stack, player)){
                if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
                    ((IShiftDescription)this.block).addDescription(stack, player, tooltip);
                }
                else{
                    tooltip.add(Names.HelpToolTips.SHIFT_FOR_INFO);
                }
            }
        }
    }

}
