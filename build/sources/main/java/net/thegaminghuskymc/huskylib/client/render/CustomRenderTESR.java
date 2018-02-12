package net.thegaminghuskymc.huskylib.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.thegaminghuskymc.huskylib.tiles.TileEntityBase;

public class CustomRenderTESR extends TileEntitySpecialRenderer<TileEntityBase>{
	
	@Override
	public void render(TileEntityBase te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		renderItem(te);
	}
	
	private void renderItem(TileEntityBase te) {
        ItemStack stack = new ItemStack(te.getBlockType());
        if (stack != null) {
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableLighting();
            GlStateManager.pushMatrix();
            if (stack.getItem() instanceof ItemBlock) {
                GlStateManager.translate(0.5f, 0.475f, 0.5f);
                GlStateManager.rotate(-90, 1, 0, 0);
                GlStateManager.scale(0.4f, 0.4f, 0.4f);
            } else {
                GlStateManager.translate(0.5f, 0.275f, 0.5f);
                GlStateManager.rotate(90, 1, 0, 0);
                GlStateManager.scale(0.65f, 0.65f, 0.65f);
            }
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.popMatrix();
        }
    }

}
