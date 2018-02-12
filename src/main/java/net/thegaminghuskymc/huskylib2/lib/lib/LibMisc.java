/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 *
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 *
 * File Created @ [18/03/2016, 21:39:39 (GMT)]
 */
package net.thegaminghuskymc.huskylib2.lib.lib;

import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.List;

public final class LibMisc {

	// Mod Constants
	public static final String MOD_ID = "hl2";
	public static final String MOD_NAME = MOD_ID;
	public static final String BUILD = "GRADLE:BUILD";
	public static final String VERSION = "GRADLE:VERSION-" + BUILD;
	public static final String PREFIX_MOD = MOD_ID + ":";

	// Proxy Constants
	public static final String PROXY_COMMON = "net.thegaminghuskymc.huskylib2.lib.proxy.CommonProxy";
	public static final String PROXY_CLIENT = "net.thegaminghuskymc.huskylib2.lib.proxy.ClientProxy";
	public static final String GUI_FACTORY = "net.thegaminghuskymc.huskylib2.lib.client.gui.GuiFactory";

	public static final ResourceLocation GENERAL_ICONS_RESOURCE = new ResourceLocation(MOD_ID, "textures/misc/general_icons.png");
	
}
