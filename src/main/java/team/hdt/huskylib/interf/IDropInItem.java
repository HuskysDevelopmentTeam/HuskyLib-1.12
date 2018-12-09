package team.hdt.huskylib.interf;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

public interface IDropInItem {

    @CapabilityInject(IDropInItem.class)
    public static Capability<IDropInItem> DROP_IN_CAPABILITY = null;

    public boolean canDropItemIn(EntityPlayer player, ItemStack stack, ItemStack incoming);

    public ItemStack dropItemIn(EntityPlayer player, ItemStack stack, ItemStack incoming);

    @SideOnly(Side.CLIENT)
    public default List<String> getDropInTooltip(ItemStack stack) {
        return Arrays.asList(I18n.format("huskylib2.misc.rightClickAdd"));
    }

}