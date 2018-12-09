package team.hdt.huskylib.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import team.hdt.huskylib.HuskyLib;
import team.hdt.huskylib.network.message.MessageDropIn;

public class NetworkHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(HuskyLib.MOD_ID);

    private static int i = 0;

    public static void register(Class clazz, Side handlerSide) {
        INSTANCE.registerMessage(clazz, clazz, i++, handlerSide);
    }

    public static void initARLMessages() {
        register(MessageDropIn.class, Side.SERVER);
    }

}