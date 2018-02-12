package net.thegaminghuskymc.huskylib2.lib.client;

import com.google.common.collect.ImmutableSet;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.thegaminghuskymc.huskylib2.lib.lib.LibObfuscation;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class DevCapeHandler {

	private static final ImmutableSet<String> UUIDS = ImmutableSet.of(
			"8c826f34-113b-4238-a173-44639c53b6e6",
			"0d054077-a977-4b19-9df9-8a4d5bf20ec3");

	private static final Set<EntityPlayer> done = Collections.newSetFromMap(new WeakHashMap());

	@SubscribeEvent
	public static void onRenderPlayer(RenderPlayerEvent.Post event) {
		EntityPlayer player = event.getEntityPlayer();
		String uuid = player.getUUID(player.getGameProfile()).toString();
		if(player instanceof AbstractClientPlayer && UUIDS.contains(uuid) && !done.contains(player)) {
			AbstractClientPlayer clplayer = (AbstractClientPlayer) player;
			if(clplayer.hasPlayerInfo()) {
				NetworkPlayerInfo info = ReflectionHelper.getPrivateValue(AbstractClientPlayer.class, clplayer, LibObfuscation.PLAYER_INFO);
				Map<Type, ResourceLocation> textures = ReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, info, LibObfuscation.PLAYER_TEXTURES);
				ResourceLocation loc = new ResourceLocation("hl2", "textures/misc/dev_cape.png");
				textures.put(Type.CAPE, loc);
				textures.put(Type.ELYTRA, loc);
				done.add(player);
			}
		}
	}

}
