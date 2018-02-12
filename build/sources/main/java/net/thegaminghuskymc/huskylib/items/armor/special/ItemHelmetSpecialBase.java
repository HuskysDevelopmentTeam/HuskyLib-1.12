package net.thegaminghuskymc.huskylib.items.armor.special;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thegaminghuskymc.huskylib.items.armor.ItemArmorBase;

public class ItemHelmetSpecialBase extends ItemArmorBase{

	public ItemHelmetSpecialBase(ArmorMaterial materialIn, String name, CreativeTabs tabs) {
		super(materialIn, 1, EntityEquipmentSlot.HEAD, name + "_helmet", tabs);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!player.isDead == true){
			player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 99999999, 100, false, false));
		}
		
		return EnumActionResult.PASS;
	}

}
