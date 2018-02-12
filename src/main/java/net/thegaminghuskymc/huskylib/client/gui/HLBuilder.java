package net.thegaminghuskymc.huskylib.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Loader;
import net.thegaminghuskymc.huskylib.utils.Refs;

public class HLBuilder extends GuiBuilder {
	
	public static final ResourceLocation GUI_SHEET = new ResourceLocation(Refs.MODID, "textures/gui/gui_sheet.png");

	public HLBuilder() {
		super(GUI_SHEET);
	}

	public void drawProgressBar(GuiContainerBase gui, int progress, int maxProgress, int x, int y, int mouseX, int mouseY, ProgressDirection direction, EnumRenderType layer) {
		if (layer == EnumRenderType.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		if (layer == EnumRenderType.FOREGROUND) {
			mouseX -= gui.getGuiLeft();
			mouseY -= gui.getGuiTop();
		}

		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(x, y, direction.x, direction.y, direction.width, direction.height);

		if (direction.equals(ProgressDirection.RIGHT)) {
			int j = (int) ((double) progress / (double) maxProgress * 16);
			if (j < 0)
				j = 0;
			gui.drawTexturedModalRect(x, y, direction.xActive, direction.yActive, j, 10);
		}

		if (direction.equals(ProgressDirection.LEFT)) {
			int j = (int) ((double) progress / (double) maxProgress * 16);
			if (j < 0)
				j = 0;
			gui.drawTexturedModalRect(x + 16 - j, y, direction.xActive + 16 - j, direction.yActive, j, 10);
		}

		if (isInRect(x, y, direction.width, direction.height, mouseX, mouseY)) {
			int percentage = percentage(maxProgress, progress);
			List<String> list = new ArrayList<>();
			list.add(getPercentageColour(percentage) + "" + percentage + "%");
			net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRenderer);
			GlStateManager.disableLighting();
			GlStateManager.color(1, 1, 1, 1);
		}
	}

	public void drawTank(GuiContainerBase gui, int x, int y, int mouseX, int mouseY, FluidStack fluid, int maxCapacity, boolean isTankEmpty, EnumRenderType layer) {
		if (layer == EnumRenderType.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		if (layer == EnumRenderType.FOREGROUND) {
			mouseX -= gui.getGuiLeft();
			mouseY -= gui.getGuiTop();
		}
		int percentage = 0;
		int amount = 0;
		boolean empty = true;
		if (!isTankEmpty) {
			amount = fluid.amount;
			percentage = percentage(maxCapacity, amount);
			empty = false;
		}
		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(x, y, 228, 18, 22, 56);
		if (!empty)
			drawFluid(gui, fluid, x + 4, y + 4, 14, 48, maxCapacity);
		gui.drawTexturedModalRect(x + 3, y + 3, 231, 74, 16, 50);

		if (isInRect(x, y, 22, 56, mouseX, mouseY)) {
			List<String> list = new ArrayList<>();
			if (empty)
				list.add(TextFormatting.GOLD + "Empty Tank");
			else
				list.add(TextFormatting.GOLD + "" + amount + "mB/" + maxCapacity + "mB " + fluid.getLocalizedName());
			list.add(getPercentageColour(percentage) + "" + percentage + "%" + TextFormatting.GRAY + " Full");
			net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRenderer);
			GlStateManager.disableLighting();
			GlStateManager.color(1, 1, 1, 1);
		}
	}

	public void drawFluid(GuiContainerBase gui, FluidStack fluid, int x, int y, int width, int height, int maxCapacity) {
		gui.mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		y += height;
		final ResourceLocation still = fluid.getFluid().getStill(fluid);
		final TextureAtlasSprite sprite = gui.mc.getTextureMapBlocks().getAtlasSprite(still.toString());

		final int drawHeight = (int) (fluid.amount / (maxCapacity * 1F) * height);
		final int iconHeight = sprite.getIconHeight();
		int offsetHeight = drawHeight;

		int iteration = 0;
		while (offsetHeight != 0) {
			final int curHeight = offsetHeight < iconHeight ? offsetHeight : iconHeight;
			gui.drawTexturedModalRect(x, y - offsetHeight, sprite, width, curHeight);
			offsetHeight -= curHeight;
			iteration++;
			if (iteration > 50)
				break;
		}
		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
	}

	public void drawJEIButton(GuiContainerBase gui, int x, int y, EnumRenderType layer) {
		if (Loader.isModLoaded("jei")) {
			if (layer == EnumRenderType.BACKGROUND) {
				x += gui.getGuiLeft();
				y += gui.getGuiTop();
			}
			gui.mc.getTextureManager().bindTexture(GUI_SHEET);
			gui.drawTexturedModalRect(x, y, 184, 70, 20, 12);
		}
	}

	public void drawBigBlueBar(GuiContainerBase gui, int x, int y, int value, int max, int mouseX, int mouseY, String suffix, EnumRenderType layer) {
		if (layer == EnumRenderType.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
		if (!suffix.equals("")) {
			suffix = " " + suffix;
		}
		gui.drawTexturedModalRect(x, y, 0, 218, 114, 18);
		int j = (int) ((double) value / (double) max * 106);
		if (j < 0)
			j = 0;
		gui.drawTexturedModalRect(x + 4, y + 4, 0, 236, j, 10);
		gui.drawCentredString(value + suffix, y + 5, 0xFFFFFF, layer);
		if (isInRect(x, y, 114, 18, mouseX, mouseY)) {
			int percentage = percentage(max, value);
			List<String> list = new ArrayList<>();
			list.add("" + TextFormatting.GOLD + value + "/" + max + suffix);
			list.add(getPercentageColour(percentage) + "" + percentage + "%" + TextFormatting.GRAY + " Full");

			if (value > max) {
				list.add(TextFormatting.GRAY + "Yo this is storing more than it should be able to");
				list.add(TextFormatting.GRAY + "prolly a bug");
				list.add(TextFormatting.GRAY + "pls report and tell how tf you did this");
			}
			GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRenderer);
			GlStateManager.disableLighting();
			GlStateManager.color(1, 1, 1, 1);
		}
	}

	public void drawBigHeatBar(GuiContainerBase gui, int x, int y, int value, int max, EnumRenderType layer) {
		if (layer == EnumRenderType.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(x, y, 0, 218, 114, 18);
		if (value != 0) {
			int j = (int) ((double) value / (double) max * 106);
			if (j < 0)
				j = 0;
			gui.drawTexturedModalRect(x + 4, y + 4, 0, 246, j, 10);
			gui.drawCentredString(value + " Heat", y + 5, 0xFFFFFF, layer);

		}
	}

	public void drawBigBlueBar(GuiContainerBase gui, int x, int y, int value, int max, int mouseX, int mouseY, EnumRenderType layer) {
		drawBigBlueBar(gui, x, y, value, max, mouseX, mouseY, "", layer);
	}

	public void drawSelectedStack(GuiContainerBase gui, int x, int y) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(x - 4, y - 4, 202, 44, 24, 24);
	}

	public void drawBurnBar(GuiContainerBase gui, int progress, int maxProgress, int x, int y, int mouseX, int mouseY, EnumRenderType layer) {
		if (layer == EnumRenderType.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		if (layer == EnumRenderType.FOREGROUND) {
			mouseX -= gui.getGuiLeft();
			mouseY -= gui.getGuiTop();
		}

		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(x, y, 171, 84, 13, 13);
		int j = 13 - (int) ((double) progress / (double) maxProgress * 13);
		if (j > 0) {
			gui.drawTexturedModalRect(x, y + j, 171, 70 + j, 13, 13 - j);

		}
		if (isInRect(x, y, 12, 12, mouseX, mouseY)) {
			int percentage = percentage(maxProgress, progress);
			List<String> list = new ArrayList<>();
			list.add(getPercentageColour(percentage) + "" + percentage + "%");
			net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRenderer);
			GlStateManager.disableLighting();
			GlStateManager.color(1, 1, 1, 1);
		}
	}

	public void drawSlot(GuiScreen gui, int posX, int posY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(posX, posY, 150, 0, 18, 18);
	}

	public void drawScrapSlot(GuiScreen gui, int posX, int posY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(posX, posY, 150, 0, 18, 18);
	}

	public void drawOutputSlotBar(GuiScreen gui, int posX, int posY, int count) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SHEET);
		for (int i = 1; i <= count; i++) {
			if (i == 1) {
				gui.drawTexturedModalRect(posX, posY, 114 + 39, 218, 22, 26);
				posX += 22;
				if (1 == count) {
					gui.drawTexturedModalRect(posX, posY, 136 + 39, 218, 4, 26);
				}
			} else if (i != 1 && i != count) {
				gui.drawTexturedModalRect(posX, posY, 116 + 39, 218, 20, 26);
				posX += 20;
			} else if (i == count) {
				gui.drawTexturedModalRect(posX, posY, 116 + 39, 218, 24, 26);
				posX += 24;
			}
		}
	}

	public void drawOutputSlot(GuiScreen gui, int posX, int posY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(posX, posY, 150, 18, 26, 26);
	}

	public int getScaledBurnTime(int scale, int burnTime, int totalBurnTime) {
		return (int) (((float) burnTime / (float) totalBurnTime) * scale);
	}

	public TextFormatting getPercentageColour(int percentage) {
		if (percentage <= 10) {
			return TextFormatting.RED;
		} else if (percentage >= 75) {
			return TextFormatting.GREEN;
		} else {
			return TextFormatting.YELLOW;
		}
	}

	public int percentage(int MaxValue, int CurrentValue) {
		if (CurrentValue == 0)
			return 0;
		return (int) ((CurrentValue * 100.0f) / MaxValue);
	}

	public enum ProgressDirection {
		RIGHT(84, 151, 100, 151, 16, 10), LEFT(100, 161, 84, 161, 16, 10)/*, DOWN(104, 171, 114, 171, 10, 16), UP(84, 171, 94, 171, 10, 16)*/;
		public int x;
		public int y;
		public int xActive;
		public int yActive;
		public int width;
		public int height;

		ProgressDirection(int x, int y, int xActive, int yActive, int width, int height) {
			this.x = x;
			this.y = y;
			this.xActive = xActive;
			this.yActive = yActive;
			this.width = width;
			this.height = height;
		}
	}
	
}
