package net.thegaminghuskymc.huskylib.client.gui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.thegaminghuskymc.huskylib.client.container.ContainerBase;
import net.thegaminghuskymc.huskylib.utils.Refs;
import net.thegaminghuskymc.huskylib.utils.RenderHelper;
import net.thegaminghuskymc.huskylib.utils.StringHelper;

/**
 * Created by Prospector
 */
@SuppressWarnings("deprecation")
public class GuiBase extends GuiContainer {

	public int xSize = 176;
	public int ySize = 176;
	public HLBuilder builder = new HLBuilder();
	public TileEntity tile;
	protected boolean drawTitle;
    protected boolean drawInventory;
    protected int mouseX;
    protected int mouseY;
    protected int lastIndex;
    protected String name;
    private ResourceLocation texture = new ResourceLocation(Refs.MODID, "textures/gui/gui_base.png");
    protected List<?> tooltip;
    protected boolean tooltips;

	public GuiBase(EntityPlayer player, TileEntity tile) {
		super(new ContainerBase() {
			
			@Override
			public boolean canInteractWith(EntityPlayer playerIn) {
				return false;
			}
			
			@Override
			public int getSizeInventory() {
				return 0;
			}
		});
		this.tile = tile;
		drawTitle = true;
        drawInventory = true;
        mouseX = 0;
        mouseY = 0;
        tooltip = new LinkedList<Object>();
        tooltips = true;
	}
	
	@Override
	public void initGui(){
        super.initGui();
    }
	
	public void handleElementButtonClick(String s, int i){
		
    }
	
	public void drawIcon(TextureAtlasSprite icon, int x, int y)
    {
        RenderHelper.setBlockTextureSheet();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(x, y, icon, 16, 16);
    }

    public void drawColorIcon(TextureAtlasSprite icon, int x, int y)
    {
        drawTexturedModalRect(x, y, icon, 16, 16);
    }

    public void drawSizedRect(int x1, int y1, int x2, int y2, int color)
    {
        if(x1 < x2)
        {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if(y1 < y2)
        {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }
        float a = (float)(color >> 24 & 0xff) / 255F;
        float r = (float)(color >> 16 & 0xff) / 255F;
        float g = (float)(color >> 8 & 0xff) / 255F;
        float b = (float)(color & 0xff) / 255F;
        GlStateManager.disableTexture2D();
        GlStateManager.color(r, g, b, a);
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION);
        buffer.pos(x1, y2, zLevel).endVertex();
        buffer.pos(x2, y2, zLevel).endVertex();
        buffer.pos(x2, y1, zLevel).endVertex();
        buffer.pos(x1, y1, zLevel).endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.enableTexture2D();
    }

	public void drawSlot(int x, int y, EnumRenderType layer) {
		if (layer == EnumRenderType.BACKGROUND) {
			x += guiLeft;
			y += guiTop;
		}
		builder.drawSlot(this, x - 1, y - 1);
	}
	
	@Override
	public void drawScreen(int x, int y, float partialTicks) {
		this.drawGuiContainerForegroundLayer(x, y);
		if(tooltips && mc.player.inventory.getItemStack() == null)
        {
            drawTooltip(tooltip);
        }
        mouseX = x;
        mouseY = y;
	}
	
	public void drawTooltip(List<?> list)
    {
        drawTooltipHoveringText(list, mouseX + guiLeft, mouseY + guiTop, fontRenderer);
        tooltip.clear();
    }
    
    protected void drawTooltipHoveringText(List<?> list, int x, int y, FontRenderer font)
    {
        if(list == null || list.isEmpty())
            return;
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        int k = 0;
        Iterator<?> iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            String s = (String)iterator.next();
            int l = font.getStringWidth(s);
            if(l > k)
                k = l;
        } while(true);
        int i1 = x + 12;
        int j1 = y - 12;
        int k1 = 8;
        if(list.size() > 1)
            k1 += 2 + (list.size() - 1) * 10;
        if(i1 + k > width)
            i1 -= 28 + k;
        if(j1 + k1 + 6 > height)
            j1 = height - k1 - 6;
        zLevel = 300F;
        itemRender.zLevel = 300F;
        int l1 = 0xf0100010;
        drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
        drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
        drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
        drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
        drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
        int i2 = 0x505000ff;
        int j2 = (i2 & 0xfefefe) >> 1 | i2 & 0xff000000;
        drawGradientRect(i1 - 3, (j1 - 3) + 1, (i1 - 3) + 1, (j1 + k1 + 3) - 1, i2, j2);
        drawGradientRect(i1 + k + 2, (j1 - 3) + 1, i1 + k + 3, (j1 + k1 + 3) - 1, i2, j2);
        drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, (j1 - 3) + 1, i2, i2);
        drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);
        for(int k2 = 0; k2 < list.size(); k2++)
        {
            String s1 = (String)list.get(k2);
            font.drawStringWithShadow(s1, i1, j1, -1);
            if(k2 == 0)
                j1 += 2;
            j1 += 10;
        }

        zLevel = 0.0F;
        itemRender.zLevel = 0.0F;
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableRescaleNormal();
    }

	protected void drawScrapSlot(int x, int y, EnumRenderType layer) {
		if (layer == EnumRenderType.BACKGROUND) {
			x += guiLeft;
			y += guiTop;
		}
		builder.drawScrapSlot(this, x - 1, y - 1);
	}

	protected void drawOutputSlotBar(int x, int y, int count, EnumRenderType layer) {
		if (layer == EnumRenderType.BACKGROUND) {
			x += guiLeft;
			y += guiTop;
		}
		builder.drawOutputSlotBar(this, x - 4, y - 4, count);
	}

	protected void drawArmourSlots(int x, int y, EnumRenderType layer) {
		if (layer == EnumRenderType.BACKGROUND) {
			x += guiLeft;
			y += guiTop;
		}
		builder.drawSlot(this, x - 1, y - 1);
		builder.drawSlot(this, x - 1, y - 1 + 18);
		builder.drawSlot(this, x - 1, y - 1 + 18 + 18);
		builder.drawSlot(this, x - 1, y - 1 + 18 + 18 + 18);
	}

	protected void drawOutputSlot(int x, int y, EnumRenderType layer) {
		if (layer == EnumRenderType.BACKGROUND) {
			x += guiLeft;
			y += guiTop;
		}
		builder.drawOutputSlot(this, x - 5, y - 5);
	}
	
	public void drawSizedModalRect(int x1, int y1, int x2, int y2, int color)
    {
        if(x1 < x2)
        {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if(y1 < y2)
        {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }
        float a = (float)(color >> 24 & 0xff) / 255F;
        float r = (float)(color >> 16 & 0xff) / 255F;
        float g = (float)(color >> 8 & 0xff) / 255F;
        float b = (float)(color & 0xff) / 255F;
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.blendFunc(net.minecraft.client.renderer.GlStateManager.SourceFactor.SRC_ALPHA, net.minecraft.client.renderer.GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(r, g, b, a);
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION);
        buffer.pos(x1, y2, zLevel).endVertex();
        buffer.pos(x2, y2, zLevel).endVertex();
        buffer.pos(x2, y1, zLevel).endVertex();
        buffer.pos(x1, y1, zLevel).endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
	
	public void drawSizedTexturedModalRect(int x, int y, int u, int v, int width, int height, float texW, 
            float texH)
    {
        float texU = 1.0F / texW;
        float texV = 1.0F / texH;
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(x + 0, y + height, zLevel).tex((float)(u + 0) * texU, (float)(v + height) * texV).endVertex();
        buffer.pos(x + width, y + height, zLevel).tex((float)(u + width) * texU, (float)(v + height) * texV).endVertex();
        buffer.pos(x + width, y + 0, zLevel).tex((float)(u + width) * texU, (float)(v + 0) * texV).endVertex();
        buffer.pos(x + 0, y + 0, zLevel).tex((float)(u + 0) * texU, (float)(v + 0) * texV).endVertex();
        Tessellator.getInstance().draw();
    }

	/*protected void drawSelectedStack(int x, int y, EnumRenderType layer) {
		if (layer == EnumRenderType.BACKGROUND) {
			x += guiLeft;
			y += guiTop;
		}
		builder.drawSelectedStack(this, x, y);
	}*/

	protected void drawGuiContainerBackgroundLayer(float partialTick, int x, int y) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, x, y);
        mouseX = x;
        mouseY = y;
        GlStateManager.pushMatrix();
        GlStateManager.translate(guiLeft, guiTop, 0.0F);
        GlStateManager.popMatrix();
	}
	
	public void bindTexture(ResourceLocation texture)
    {
        mc.renderEngine.bindTexture(texture);
    }

	public boolean drawPlayerSlots() {
		return true;
	}

	public boolean tryAddUpgrades() {
		return true;
	}

	protected void drawGuiContainerForegroundLayer(int x, int y) {
		if(drawTitle & (name != null))
            fontRenderer.drawString(StringHelper.localize(name), getCenteredOffset(StringHelper.localize(name)), 6, 0x404040);
        if(drawInventory)
            fontRenderer.drawString(I18n.translateToLocal("container.inventory"), 8, (ySize - 96) + 3, 0x404040);
        this.builder.drawPlayerSlots(this, guiLeft - 64, guiTop + 49, true);
	}
	
	protected int getCenteredOffset(String string)
    {
        return getCenteredOffset(string, xSize);
    }

    protected int getCenteredOffset(String string, int xWidth)
    {
        return (xWidth - fontRenderer.getStringWidth(string)) / 2;
    }

	protected void drawCentredString(String string, int y, int colour, EnumRenderType layer) {
		drawString(string, (xSize / 2 - mc.fontRenderer.getStringWidth(string) / 2), y, colour, layer);
	}

	protected void drawCentredString(String string, int y, int colour, int modifier, EnumRenderType layer) {
		drawString(string, (xSize / 2 - (mc.fontRenderer.getStringWidth(string)) / 2) + modifier, y, colour, layer);
	}

	protected void drawString(String string, int x, int y, int colour, EnumRenderType layer) {
		int factorX = 0;
		int factorY = 0;
		if (layer == EnumRenderType.BACKGROUND) {
			factorX = guiLeft;
			factorY = guiTop;
		}
		mc.fontRenderer.drawString(string, x + factorX, y + factorY, colour);
		GlStateManager.color(1, 1, 1, 1);
	}
	
	public FontRenderer getFontRenderer()
    {
        return fontRenderer;
    }
	
}
