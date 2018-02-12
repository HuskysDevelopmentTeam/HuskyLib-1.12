package net.thegaminghuskymc.huskylib2.lib.rendering_module.new_renders;

import net.minecraft.client.renderer.BufferBuilder;

public class CustomBlockRenderer extends BufferBuilder {

    public CustomBlockRenderer(int bufferSizeIn) {
        super(bufferSizeIn);
        finishDrawing();
    }

}
