package net.thegaminghuskymc.huskylib2.lib.client.gui;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thegaminghuskymc.huskylib2.lib.modules.GlobalConfig;
import net.thegaminghuskymc.huskylib2.lib.modules.ModuleLoader;

import java.util.List;

public final class ConfigEvents {

	@SubscribeEvent
	public static void onGuiOpen(GuiOpenEvent event) {
		if(ModuleLoader.firstLoad && event.getGui() instanceof GuiMainMenu) {
			ModuleLoader.firstLoad = false;
			event.setGui(new GuiConfigFirstLoad(event.getGui()));
		}
	}
	
	@SubscribeEvent
	public static void onGuiInit(GuiScreenEvent.InitGuiEvent event) {
		GuiScreen gui = event.getGui();
		
		if(GlobalConfig.enableHlButton && (gui instanceof GuiMainMenu || gui instanceof GuiIngameMenu)) {
			ImmutableSet<String> targets = GlobalConfig.hlButtonOnRight
					? ImmutableSet.of(I18n.format("fml.menu.modoptions"), I18n.format("menu.online").replace("Minecraft", "").trim())
					: ImmutableSet.of(I18n.format("menu.options"), I18n.format("fml.menu.mods"));
					
			List<GuiButton> buttons = event.getButtonList();
			for(GuiButton b : buttons)
				if(targets.contains(b.displayString)) {
					GuiButton qButton = new GuiButtonHl(b.x + (GlobalConfig.hlButtonOnRight ? 103 : -24), b.y);
					buttons.add(qButton);
					return;
				}
		}
	}
	
	@SubscribeEvent
	public static void onButtonClick(GuiScreenEvent.ActionPerformedEvent event) {
		if(event.getButton() instanceof GuiButtonHl)
			Minecraft.getMinecraft().displayGuiScreen(new GuiConfigRoot(event.getGui()));
	}
	
}
