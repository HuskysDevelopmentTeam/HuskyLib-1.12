package net.thegaminghuskymc.huskylib2.lib.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.Property;
import net.thegaminghuskymc.huskylib2.lib.modules.ModuleLoader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GuiConfigBase extends GuiScreen {

	String title;
	GuiScreen parent;
	
	private static List<Property> restartRequiringProperties = new LinkedList();
	public static boolean mayRequireRestart = false;

	GuiButton backButton;

	public GuiConfigBase(GuiScreen parent) {
		this.parent = parent;
	}

	@Override
	public void initGui() {
		super.initGui();
		
		buttonList.clear();
		title = I18n.translateToLocal("hl2.config.title");
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, title, width / 2, 15, 0xFFFFFF);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override 
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if(keyCode == 1) // Esc
			returnToParent();
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);

		if(backButton != null && button == backButton)
			returnToParent();

		if(button instanceof GuiButtonConfigSetting) {
			GuiButtonConfigSetting configButton = (GuiButtonConfigSetting) button;
			configButton.prop.set(!configButton.prop.getBoolean());
			if(configButton.prop.requiresMcRestart()) {
				if(restartRequiringProperties.contains(configButton.prop))
					restartRequiringProperties.remove(configButton.prop);
				else restartRequiringProperties.add(configButton.prop);
						
				mayRequireRestart = !restartRequiringProperties.isEmpty();
			}
			ModuleLoader.loadConfig();
		}
	}

	void returnToParent() {
		mc.displayGuiScreen(parent);

		if(mc.currentScreen == null)
			mc.setIngameFocus();
	}

}
