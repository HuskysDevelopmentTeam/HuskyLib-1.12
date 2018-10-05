package net.hdt.huskylib2.network.message;

import net.hdt.huskylib2.network.NetworkMessage;
import net.hdt.huskylib2.util.DropInHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
        DropInHandler.executeDropIn(context.getServerHandler().player, slot, stack);

        return null;
    }

}