package net.thegaminghuskymc.huskylib.client.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thegaminghuskymc.huskylib.client.RenderUtil;
import net.thegaminghuskymc.huskylib.utils.Refs;
import net.thegaminghuskymc.huskylib.utils.TranslationUtils;
import net.thegaminghuskymc.huskylib.utils.math.IPositionProvider;
import net.thegaminghuskymc.huskylib.utils.math.Point2i;

@SideOnly(Side.CLIENT)
public abstract class GuiContainerBase extends GuiContainer {

    private final ResourceLocation textureBackground = new ResourceLocation(Refs.MODID, "textures/gui/background.png");
    private final ResourceLocation texturePowerBars = new ResourceLocation(Refs.MODID, "textures/gui/power_bars.png");
    private final ResourceLocation textureElements = new ResourceLocation(Refs.MODID, "textures/gui/elements.png");
    
    private final ResourceLocation textureSheet = new ResourceLocation(Refs.MODID, "textures/gui/gui_sheet.png");
    
    private final String tooltipEmpty = TranslationUtils.translate(Refs.MODID, "tooltip", "empty");
    
    public static final int ALIGNMENT_LEFT = 0;
    public static final int ALIGNMENT_RIGHT = 1;
    public static final int ALIGNMENT_TOP = 2;
    public static final int ALIGNMENT_BOTTOM = 3;
    public static final int ALIGNMENT_NONE = 4;
    public static final int BACKGROUND_LIGHT = 0;
    public static final int BACKGROUND_DARK = 1;
    public static final int POWER_TESLA = 0;
    public static final int POWER_RF = 1;
    public static final int POWER_FORGE = 2;
    public static final int POWER_EU = 3;
    public static final int POWER_MT = 4;
    
    public GuiContainerBase(Container container) {
        super(container);
    }

    protected void drawFluidGauge(Point2i pos, int backgroundType, Fluid fluid, int amount, int maxAmount){
        this.drawFluidGauge(pos, backgroundType, new FluidTank(fluid, amount, maxAmount), null);
    }

    protected void drawFluidGauge(Point2i pos, int backgroundType, Fluid fluid, int amount, int maxAmount, IPositionProvider mousePosition){
        this.drawFluidGauge(pos, backgroundType, new FluidTank(fluid, amount, maxAmount), mousePosition);
    }

    protected void drawFluidGauge(Point2i pos, int backgroundType, FluidTank fluidTank){
        this.drawFluidGauge(pos, backgroundType, fluidTank, null);
    }
    
    protected int getCenteredOffset(String string)
    {
        return getCenteredOffset(string, xSize);
    }

    protected int getCenteredOffset(String string, int xWidth)
    {
        return (xWidth - fontRenderer.getStringWidth(string)) / 2;
    }

	protected void drawCentredString(String string, int y, int Color, EnumRenderType layer) {
		drawString(string, (xSize / 2 - mc.fontRenderer.getStringWidth(string) / 2), y, Color, layer);
	}

	protected void drawCentredString(String string, int y, int Color, int modifier, EnumRenderType layer) {
		drawString(string, (xSize / 2 - (mc.fontRenderer.getStringWidth(string)) / 2) + modifier, y, Color, layer);
	}
	
	protected void drawString(String string, int x, int y, int Color, EnumRenderType layer) {
		int factorX = 0;
		int factorY = 0;
		if (layer == EnumRenderType.BACKGROUND) {
			factorX = guiLeft;
			factorY = guiTop;
		}
		mc.fontRenderer.drawString(string, x + factorX, y + factorY, Color);
		GlStateManager.color(1, 1, 1, 1);
	}

    protected void drawFluidGauge(Point2i pos, int backgroundType, FluidTank fluidTank, IPositionProvider mousePosition){
        final int width = 20;
        final int height = 68;
        this.mc.getTextureManager().bindTexture(this.textureElements);
        GlStateManager.pushMatrix();
        GlStateManager.color(1F, 1F, 1F, 1F);

        switch(backgroundType){
            case BACKGROUND_LIGHT:
                this.drawTexturedModalRect(pos.getX(), pos.getY(), 3, 106, width, height);
                break;
            case BACKGROUND_DARK:
                this.drawTexturedModalRect(pos.getX(), pos.getY(), 3, 176, width, height);
                break;
        }

        switch(backgroundType){
            case BACKGROUND_LIGHT:
                this.drawTexturedModalRect(pos.getX() + 1, pos.getY() + 1, 24, 107, width - 2, height - 2);
                break;
            case BACKGROUND_DARK:
                this.drawTexturedModalRect(pos.getX() + 1, pos.getY() + 1, 24, 177, width - 2, height - 2);
                break;
        }

        GlStateManager.popMatrix();

        if(mousePosition != null){
            AxisAlignedBB aabb = new AxisAlignedBB(pos.getX(), pos.getY(), 0D, pos.getX() + width, pos.getY() + height, 0D);

            if(aabb.intersectsWithXY(new Vec3d(mousePosition.getPosition().getX(), mousePosition.getPosition().getY(), 0D))){
                GlStateManager.pushMatrix();
                GlStateManager.color(1F, 1F, 1F, 1F);
                List<String> text = Lists.newArrayList();
                text.add((fluidTank.getFluidAmount() > 0 ? Integer.toString(fluidTank.getFluidAmount()) : "0") + " mB");
                text.add(TextFormatting.YELLOW + (fluidTank.getFluidAmount() > 0 ? fluidTank.getFluid().getLocalizedName() : this.tooltipEmpty));
                int screenWidth = Minecraft.getMinecraft().displayWidth;
                int screenHeight = Minecraft.getMinecraft().displayHeight;
                GuiUtils.drawHoveringText(text, mousePosition.getPosition().getX(), mousePosition.getPosition().getY(), screenWidth, screenHeight, 200, this.fontRenderer);
                GlStateManager.popMatrix();
            }
        }
    }
    
    //TODO: Make a upgrade slots thing
    /*public void drawUpgradeSlots(){
    	
    }*/
    
    public void drawEnergyBar(GuiScreen gui, int x, int y, int height, int energyStored, int maxEnergyStored, int mouseX, int mouseY, String powerType) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(textureSheet);

		gui.drawTexturedModalRect(x, y, 0, 150, 14, height);
		
		switch(powerType){
        	case "Tesla":
//            	color = new ColorRGBA(0, 194, 220, 255);
        		int drawTesla = (int) ((double) energyStored / (double) maxEnergyStored * (height));
        		gui.drawTexturedModalRect(x + 1, y + height - drawTesla - 1, 15, height + 149 - drawTesla, 12, drawTesla);
            	break;
        	case "RF":
//            	color = new ColorRGBA(255, 42, 0, 255);
        		int drawRF = (int) ((double) energyStored / (double) maxEnergyStored * (height));
        		gui.drawTexturedModalRect(x + 1, y + height - drawRF - 1, 15, height + 149 - drawRF, 12, drawRF);
            	break;
        	case "FE":
//        		color = new ColorRGBA(40, 90, 220, 255);
        		int drawFE = (int) ((double) energyStored / (double) maxEnergyStored * (height));
        		gui.drawTexturedModalRect(x + 1, y + height - drawFE - 1, 15, height + 149 - drawFE, 12, drawFE);
//        		Gui.drawRect(x + 1, y + height - drawFE - 1, 1, 1, 0x285ADC);
        		break;
        	case "EU":
//            	color = new ColorRGBA(50, 50, 240, 255);
        		int drawEU = (int) ((double) energyStored / (double) maxEnergyStored * (height));
        		gui.drawTexturedModalRect(x + 1, y + height - drawEU - 1, 43, height + 149 - drawEU, 12, drawEU);
//        		Gui.drawRect(x + 1, y + height - drawEU - 1, 1, 1, 0x3232F0);
            	break;
        	case "MT":
//            	color = new ColorRGBA(0, 224, 224, 255 / 2);
        		int drawMT = (int) ((double) energyStored / (double) maxEnergyStored * (height));
        		gui.drawTexturedModalRect(x + 1, y + height - drawMT - 1, 15, height + 149 - drawMT, 12, drawMT);
//        		Gui.drawRect(x + 1, y + height - drawMT - 1, 1, 1, 0x00F4F4);
            	break;
		}

		if (isInRect(x, y, 14, height, mouseX, mouseY)) {
			List<String> list = new ArrayList<String>();
			list.add(energyStored + " / " + maxEnergyStored + " " + powerType);
			list.add("This machine runs on " + powerType);
			GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRenderer);
			GlStateManager.disableLighting();
		}
	}
    
    public void drawProgressBar(GuiScreen gui, double progress, int x, int y, int mouseX, int mouseY) {
		gui.mc.getTextureManager().bindTexture(textureSheet);
		gui.drawTexturedModalRect(x, y, 84, 151, 16, 10);
		int j = (int) (progress);
		List<String> list = new ArrayList<String>();
		if (j > 0) {
			gui.drawTexturedModalRect(x, y, 100, 151, j - 1, 10);
			if (isInRect(x, y, 14, height, x, y)) {
				list.add(j + "%");
				GuiUtils.drawHoveringText(list, x, y, gui.width, gui.height, -1, gui.mc.fontRenderer);
				GlStateManager.disableLighting();
			}
		}
	}

	public void drawTank(GuiScreen gui, FluidTank tank, int x, int y, float zLevel, int width, int height, int mouseX, int mouseY) {
		RenderUtil.renderGuiTank(tank, x, y, zLevel, width, height);
		if (isInRect(x, y, 14, height, mouseX, mouseY)) {
			List<String> list = new ArrayList<String>();
			if (tank.getFluid() != null) {
				list.add(tank.getFluidAmount() + " / " + tank.getCapacity() + " " + tank.getFluid().getLocalizedName());
			} else {
				list.add("empty");
			}
			GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRenderer);
			GlStateManager.disableLighting();
		}
	}

	//TODO fix
	public void drawBurnBar(GuiScreen gui, double progress, int x, int y, int mouseX, int mouseY) {
		gui.mc.getTextureManager().bindTexture(textureSheet);
		gui.drawTexturedModalRect(x, y, 171, 84, 13, 13);
		int j = (int) (progress);
		List<String> list = new ArrayList<String>();
		if (j > 0) {
			gui.drawTexturedModalRect(x, y, 171, 70, 13, j + 1);
			if (isInRect(x, y, 14, height, x, y)) {
				list.add(j + "%");
				GuiUtils.drawHoveringText(list, x, y, gui.width, gui.height, -1, gui.mc.fontRenderer);
				GlStateManager.disableLighting();
			}
		}
	}

	public void drawOutputSlot(GuiScreen gui, int x, int y) {
		gui.mc.getTextureManager().bindTexture(textureSheet);
		gui.drawTexturedModalRect(x, y, 174, 0, 26, 26);
	}

	public void drawInfoButton(int buttonID, int x, int y, List<GuiButton> buttonList) {
		buttonList.add(new GuiButton(0, x, y, 20, 20, "i"));
	}

	public void handleInfoButtonClick(int buttonID, List<GuiButton> buttonList) {
		//        buttonList.get(buttonID).
	}

	public void drawInfo(GuiScreen gui, int x, int y, int height, int width, boolean draw) {
		if (draw) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(textureSheet);
			gui.drawTexturedModalRect(x, y, 0, 0, width / 2, height / 2);
			gui.drawTexturedModalRect(x + width / 2, y, 150 - width / 2, 0, width / 2, height / 2);
			gui.drawTexturedModalRect(x, y + height / 2, 0, 150 - height / 2, width / 2, height / 2);
			gui.drawTexturedModalRect(x + width / 2, y + height / 2, 150 - width / 2, 150 - height / 2, width / 2, height / 2);
		}
	}
    
    public boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
		return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
	}

	public void drawPlayerSlots(GuiScreen gui, int posX, int posY, boolean center) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(textureSheet);

		if (center) {
			posX -= 81;
		}

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				gui.drawTexturedModalRect(posX + x * 18, posY + y * 18, 150, 0, 18, 18);
			}
		}

		for (int x = 0; x < 9; x++) {
			gui.drawTexturedModalRect(posX + x * 18, posY + 58, 150, 0, 18, 18);
		}
	}

    protected void drawPowerBar(Point2i pos, int backgroundType, int powerType, int power, int capacity){
        this.drawPowerBar(pos, backgroundType, powerType, power, capacity, null);
    }

    protected void drawPowerBar(Point2i pos, int backgroundType, int powerType, int power, int capacity, IPositionProvider mousePosition){
    	final int width = 18;
        final int height = 74;
        int powerOffset = (power * (height + 1)) / capacity;
        this.mc.getTextureManager().bindTexture(this.texturePowerBars);

        switch(backgroundType){
            case BACKGROUND_LIGHT:
                GlStateManager.pushMatrix();
                GlStateManager.color(1F, 1F, 1F, 1F);
                drawTexturedModalRect(pos.getX(), pos.getY(), 3, 1, width, height);
                GlStateManager.color(1F, 1F, 1F, 1F);
                GlStateManager.popMatrix();
                break;
            case BACKGROUND_DARK:
                GlStateManager.pushMatrix();
                GlStateManager.color(1F, 1F, 1F, 1F);
                drawTexturedModalRect(pos.getX(), pos.getY(), 3, 53, width, height);
                GlStateManager.color(1F, 1F, 1F, 1F);
                GlStateManager.popMatrix();
                break;
        }

        GlStateManager.pushMatrix();
        drawTexturedModalRect(pos.getX() + 1, (pos.getY() + height - powerOffset), 18, ((height + 1) - powerOffset), width, (powerOffset + 2));
        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.popMatrix();

        if(mousePosition != null){
            AxisAlignedBB aabb = new AxisAlignedBB(pos.getX(), pos.getY(), 0D, pos.getX() + width, pos.getY() + height, 0D);

            if(aabb.intersectsWithXY(new Vec3d(mousePosition.getPosition().getX(), mousePosition.getPosition().getY(), 0D))){
                GlStateManager.pushMatrix();
                GlStateManager.color(1F, 1F, 1F, 1F);
                List<String> text = Lists.newArrayList();

                switch(powerType){
                    case POWER_TESLA:
                        text.add(Integer.toString(power) + " TESLA");
                        break;
                    case POWER_RF:
                        text.add(Integer.toString(power) + " RF");
                        break;
                    case POWER_FORGE:
                        text.add(Integer.toString(power) + " FU");
                        break;
                    case POWER_EU:
                        text.add(Integer.toString(power) + " EU");
                        break;
                }

                int screenwidth = Minecraft.getMinecraft().displayWidth;
                int screenHeight = Minecraft.getMinecraft().displayHeight;
                GuiUtils.drawHoveringText(text, mousePosition.getPosition().getX(), mousePosition.getPosition().getY(), screenwidth, screenHeight, 200, this.fontRenderer);
                GlStateManager.popMatrix();
            }
        }
    }

    protected void drawRectangleWithBorder(Point2i pos, Point2i size, Color color, Color colorBorder){
        this.drawRectangleWithBorder(pos, size, color, color, colorBorder);
    }

    protected void drawRectangleWithBorder(Point2i pos, Point2i size, Color colorStart, Color colorEnd, Color colorBorder){
        int startX = pos.getX();
        int startY = pos.getY();
        int endX = pos.getX() + size.getX();
        int endY = pos.getY() + size.getY();
        GlStateManager.pushMatrix();
        GlStateManager.color(1F, 1F, 1F, 1F);
        GuiUtils.drawGradientRect(0, startX, startY, endX, endY, colorStart.getRGB(), colorEnd.getRGB());
        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.color(1F, 1F, 1F, 1F);
        this.drawHorizontalLine(startX, endX - 1, startY, colorBorder.getRGB());
        this.drawHorizontalLine(startX, endX - 1, endY - 1, colorBorder.getRGB());
        this.drawVerticalLine(startX, startY, endY, colorBorder.getRGB());
        this.drawVerticalLine(endX - 1, startY, endY, colorBorder.getRGB());
        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.popMatrix();
    }

    protected void drawRectangle(Point2i pos, Point2i size, Color color){
        this.drawRectangle(pos, size, color, color);
    }

    protected void drawRectangle(Point2i pos, Point2i size, Color colorStart, Color colorEnd){
        int startX = pos.getX();
        int startY = pos.getY();
        int endX = pos.getX() + size.getX();
        int endY = pos.getY() + size.getY();
        GlStateManager.pushMatrix();
        GlStateManager.color(1F, 1F, 1F, 1F);
        GuiUtils.drawGradientRect(0, startX, startY, endX, endY, colorStart.getRGB(), colorEnd.getRGB());
        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.popMatrix();
    }

    /*protected void drawBackground(Point2i pos, Point2i size, int alignment){
        this.drawBackground(pos, size, alignment, new ColourRGBA(255, 255, 255, 255));
    }

    protected void drawBackground(Point2i pos, Point2i size, int alignment, Colour color){
        GlStateManager.pushMatrix();
        Point2i posCornerTL = new Point2i(pos.getX(), pos.getY());
        Point2i minUVCornerTL = new Point2i(0, 0);
        Point2i maxUVCornerTL = new Point2i(4, 4);
        Point2i posCornerBL = new Point2i(pos.getX(), pos.getY() + size.getY());
        Point2i minUVCornerBL = new Point2i(0, 20);
        Point2i maxUVCornerBL = new Point2i(4, 24);
        Point2i posCornerTR = new Point2i(pos.getX() + size.getX(), pos.getY());
        Point2i minUVCornerTR = new Point2i(20, 0);
        Point2i maxUVCornerTR = new Point2i(24, 4);
        Point2i posCornerBR = new Point2i(pos.getX() + size.getX(), pos.getY() + size.getY());
        Point2i minUVCornerBR = new Point2i(20, 20);
        Point2i maxUVCornerBR = new Point2i(24, 24);

        switch(alignment){
            case ALIGNMENT_LEFT:
                this.drawBackground(posCornerTL, minUVCornerTL, maxUVCornerTL, color);
                this.drawBackground(posCornerBL, minUVCornerBL, maxUVCornerBL, color);

                for(int left = 0; left < size.getY() - 4; left++){
                    Point2i posEdgeLeft = new Point2i(pos.getX(), pos.getY() + 4 + left);
                    Point2i minUVEdgeLeft = new Point2i(0, 4);
                    Point2i maxUVEdgeLeft = new Point2i(4, 1);
                    this.drawBackground(posEdgeLeft, minUVEdgeLeft, maxUVEdgeLeft, color);
                }

                for(int top = 0; top < size.getX() - 4; top++){
                    Point2i posEdgeTop = new Point2i(pos.getX() + 4 + top, pos.getY());
                    Point2i minUVEdgeTop = new Point2i(4, 0);
                    Point2i maxUVEdgeTop = new Point2i(1, 4);
                    this.drawBackground(posEdgeTop, minUVEdgeTop, maxUVEdgeTop, color);
                }

                for(int bottom = 0; bottom < size.getX() - 4; bottom++){
                    Point2i posEdgeBottom = new Point2i(pos.getX() + 4 + bottom, pos.getY() + size.getY());
                    Point2i minUVEdgeBottom = new Point2i(4, 20);
                    Point2i maxUVEdgeBottom = new Point2i(1, 24);
                    this.drawBackground(posEdgeBottom, minUVEdgeBottom, maxUVEdgeBottom, color);
                }

                for(int fillX = 0; fillX < size.getX() - 4; fillX++){
                    for(int fillY = 0; fillY < size.getY() - 4; fillY++){
                        Point2i posFill = new Point2i(pos.getX() + 4 + fillX, pos.getY() + 4 + fillY);
                        Point2i minUVFill = new Point2i(4, 4);
                        Point2i maxUVFill = new Point2i(1, 1);
                        this.drawBackground(posFill, minUVFill, maxUVFill, color);
                    }
                }

                break;
            case ALIGNMENT_RIGHT:
                this.drawBackground(posCornerTR, minUVCornerTR, maxUVCornerTR, color);
                this.drawBackground(posCornerBR, minUVCornerBR, maxUVCornerBR, color);

                for(int right = 0; right < size.getY() - 4; right++){
                    Point2i posEdgeRight = new Point2i(pos.getX() + size.getX(), pos.getY() + 4 + right);
                    Point2i minUVEdgeRight = new Point2i(20, 4);
                    Point2i maxUVEdgeRight = new Point2i(24, 1);
                    this.drawBackground(posEdgeRight, minUVEdgeRight, maxUVEdgeRight, color);
                }

                for(int top = 0; top < size.getX() - 4; top++){
                    Point2i posEdgeTop = new Point2i(pos.getX() + 4 + top, pos.getY());
                    Point2i minUVEdgeTop = new Point2i(4, 0);
                    Point2i maxUVEdgeTop = new Point2i(1, 4);
                    this.drawBackground(posEdgeTop, minUVEdgeTop, maxUVEdgeTop, color);
                }

                for(int bottom = 0; bottom < size.getX() - 4; bottom++){
                    Point2i posEdgeBottom = new Point2i(pos.getX() + 4 + bottom, pos.getY() + size.getY());
                    Point2i minUVEdgeBottom = new Point2i(4, 20);
                    Point2i maxUVEdgeBottom = new Point2i(1, 24);
                    this.drawBackground(posEdgeBottom, minUVEdgeBottom, maxUVEdgeBottom, color);
                }

                for(int fillX = 0; fillX < size.getX() - 4; fillX++){
                    for(int fillY = 0; fillY < size.getY() - 4; fillY++){
                        Point2i posFill = new Point2i(pos.getX() + 4 + fillX, pos.getY() + 4 + fillY);
                        Point2i minUVFill = new Point2i(4, 4);
                        Point2i maxUVFill = new Point2i(1, 1);
                        this.drawBackground(posFill, minUVFill, maxUVFill, color);
                    }
                }

                break;
            case ALIGNMENT_TOP:
                this.drawBackground(posCornerTL, minUVCornerTL, maxUVCornerTL, color);
                this.drawBackground(posCornerTR, minUVCornerTR, maxUVCornerTR, color);

                for(int left = 0; left < size.getY() - 4; left++){
                    Point2i posEdgeLeft = new Point2i(pos.getX(), pos.getY() + 4 + left);
                    Point2i minUVEdgeLeft = new Point2i(0, 4);
                    Point2i maxUVEdgeLeft = new Point2i(4, 1);
                    this.drawBackground(posEdgeLeft, minUVEdgeLeft, maxUVEdgeLeft, color);
                }

                for(int right = 0; right < size.getY() - 4; right++){
                    Point2i posEdgeRight = new Point2i(pos.getX() + size.getX(), pos.getY() + 4 + right);
                    Point2i minUVEdgeRight = new Point2i(20, 4);
                    Point2i maxUVEdgeRight = new Point2i(24, 1);
                    this.drawBackground(posEdgeRight, minUVEdgeRight, maxUVEdgeRight, color);
                }

                for(int top = 0; top < size.getX() - 4; top++){
                    Point2i posEdgeTop = new Point2i(pos.getX() + 4 + top, pos.getY());
                    Point2i minUVEdgeTop = new Point2i(4, 0);
                    Point2i maxUVEdgeTop = new Point2i(1, 4);
                    this.drawBackground(posEdgeTop, minUVEdgeTop, maxUVEdgeTop, color);
                }

                for(int fillX = 0; fillX < size.getX() - 4; fillX++){
                    for(int fillY = 0; fillY < size.getY() - 4; fillY++){
                        Point2i posFill = new Point2i(pos.getX() + 4 + fillX, pos.getY() + 4 + fillY);
                        Point2i minUVFill = new Point2i(4, 4);
                        Point2i maxUVFill = new Point2i(1, 1);
                        this.drawBackground(posFill, minUVFill, maxUVFill, color);
                    }
                }

                break;
            case ALIGNMENT_BOTTOM:
                this.drawBackground(posCornerBL, minUVCornerBL, maxUVCornerBL, color);
                this.drawBackground(posCornerBR, minUVCornerBR, maxUVCornerBR, color);

                for(int left = 0; left < size.getY() - 4; left++){
                    Point2i posEdgeLeft = new Point2i(pos.getX(), pos.getY() + 4 + left);
                    Point2i minUVEdgeLeft = new Point2i(0, 4);
                    Point2i maxUVEdgeLeft = new Point2i(4, 1);
                    this.drawBackground(posEdgeLeft, minUVEdgeLeft, maxUVEdgeLeft, color);
                }

                for(int right = 0; right < size.getY() - 4; right++){
                    Point2i posEdgeRight = new Point2i(pos.getX() + size.getX(), pos.getY() + 4 + right);
                    Point2i minUVEdgeRight = new Point2i(20, 4);
                    Point2i maxUVEdgeRight = new Point2i(24, 1);
                    this.drawBackground(posEdgeRight, minUVEdgeRight, maxUVEdgeRight, color);
                }

                for(int bottom = 0; bottom < size.getX() - 4; bottom++){
                    Point2i posEdgeBottom = new Point2i(pos.getX() + 4 + bottom, pos.getY() + size.getY());
                    Point2i minUVEdgeBottom = new Point2i(4, 20);
                    Point2i maxUVEdgeBottom = new Point2i(1, 24);
                    this.drawBackground(posEdgeBottom, minUVEdgeBottom, maxUVEdgeBottom, color);
                }

                for(int fillX = 0; fillX < size.getX() - 4; fillX++){
                    for(int fillY = 0; fillY < size.getY() - 4; fillY++){
                        Point2i posFill = new Point2i(pos.getX() + 4 + fillX, pos.getY() + 4 + fillY);
                        Point2i minUVFill = new Point2i(4, 4);
                        Point2i maxUVFill = new Point2i(1, 1);
                        this.drawBackground(posFill, minUVFill, maxUVFill, color);
                    }
                }

                break;
            case ALIGNMENT_NONE:
                this.drawBackground(posCornerTL, minUVCornerTL, maxUVCornerTL, color);
                this.drawBackground(posCornerTR, minUVCornerTR, maxUVCornerTR, color);
                this.drawBackground(posCornerBL, minUVCornerBL, maxUVCornerBL, color);
                this.drawBackground(posCornerBR, minUVCornerBR, maxUVCornerBR, color);

                for(int left = 0; left < size.getY() - 4; left++){
                    Point2i posEdgeLeft = new Point2i(pos.getX(), pos.getY() + 4 + left);
                    Point2i minUVEdgeLeft = new Point2i(0, 4);
                    Point2i maxUVEdgeLeft = new Point2i(4, 1);
                    this.drawBackground(posEdgeLeft, minUVEdgeLeft, maxUVEdgeLeft, color);
                }

                for(int right = 0; right < size.getY() - 4; right++){
                    Point2i posEdgeRight = new Point2i(pos.getX() + size.getX(), pos.getY() + 4 + right);
                    Point2i minUVEdgeRight = new Point2i(20, 4);
                    Point2i maxUVEdgeRight = new Point2i(24, 1);
                    this.drawBackground(posEdgeRight, minUVEdgeRight, maxUVEdgeRight, color);
                }

                for(int top = 0; top < size.getX() - 4; top++){
                    Point2i posEdgeTop = new Point2i(pos.getX() + 4 + top, pos.getY());
                    Point2i minUVEdgeTop = new Point2i(4, 0);
                    Point2i maxUVEdgeTop = new Point2i(1, 4);
                    this.drawBackground(posEdgeTop, minUVEdgeTop, maxUVEdgeTop, color);
                }

                for(int bottom = 0; bottom < size.getX() - 4; bottom++){
                    Point2i posEdgeBottom = new Point2i(pos.getX() + 4 + bottom, pos.getY() + size.getY());
                    Point2i minUVEdgeBottom = new Point2i(4, 20);
                    Point2i maxUVEdgeBottom = new Point2i(1, 24);
                    this.drawBackground(posEdgeBottom, minUVEdgeBottom, maxUVEdgeBottom, color);
                }

                for(int fillX = 0; fillX < size.getX() - 4; fillX++){
                    for(int fillY = 0; fillY < size.getY() - 4; fillY++){
                        Point2i posFill = new Point2i(pos.getX() + 4 + fillX, pos.getY() + 4 + fillY);
                        Point2i minUVFill = new Point2i(4, 4);
                        Point2i maxUVFill = new Point2i(1, 1);
                        this.drawBackground(posFill, minUVFill, maxUVFill, color);
                    }
                }

                break;
        }

        GlStateManager.popMatrix();
    }*/

    /*private void drawBackground(Point2i pos, Point2i minUV, Point2i maxUV, Colour color) {
        this.mc.getTextureManager().bindTexture(this.textureBackground);
        color.glColour();
        this.drawTexturedModalRect(pos.getX(), pos.getY(), minUV.getX(), minUV.getY(), maxUV.getX(), maxUV.getY());
    }*/

}
