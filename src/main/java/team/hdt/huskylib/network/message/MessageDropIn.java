package team.hdt.huskylib.network.message;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import team.hdt.huskylib.network.NetworkMessage;
import team.hdt.huskylib.util.DropInHandler;

public class MessageDropIn extends NetworkMessage<MessageDropIn> {

    public int slot;
    public ItemStack stack = ItemStack.EMPTY;

    public MessageDropIn() {
    }

    public MessageDropIn(int slot) {
        this.slot = slot;
    }

    public MessageDropIn(int slot, ItemStack stack) {
        this(slot);
        this.stack = stack;
    }

    @Override
    public IMessage handleMessage(MessageContext context) {
        EntityPlayer player = context.getServerHandler().player;
        player.getServer().addScheduledTask(() -> DropInHandler.executeDropIn(player, slot, stack));

        return null;
    }

}