package henryrichard.epicwasteoftime.client.render.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.block.tileentity.AluminumTankTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

@OnlyIn(Dist.CLIENT)
public class AluminumTankTileEntityRenderer extends TileEntityRenderer<AluminumTankTileEntity> {

    public AluminumTankTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    private static void addVertex(IVertexBuilder builder, MatrixStack matrixStack, float x, float y, float z, float u, float v, float r, float g, float b, float a) {
        builder.pos(matrixStack.getLast().getMatrix(), x, y, z)
                .color(r, g, b, a)
                .tex(u, v)
                .lightmap(0, 240)
                .normal(1, 0, 0)
                .endVertex();
    }

    private static void addVerticalQuad(IVertexBuilder builder, MatrixStack matrixStack, float x1, float x2, float y1, float y2, float z1, float z2, float u1, float u2, float v1, float v2, float r, float g, float b, float a) {
        addVertex(builder, matrixStack, x1, y1, z1, u1, v1, r, g, b, a);
        addVertex(builder, matrixStack, x2, y1, z2, u2, v1, r, g, b, a);
        addVertex(builder, matrixStack, x2, y2, z2, u2, v2, r, g, b, a);
        addVertex(builder, matrixStack, x1, y2, z1, u1, v2, r, g, b, a);
    }

    private static void addHorizontalQuad(IVertexBuilder builder, MatrixStack matrixStack, float x1, float x2, float y, float z1, float z2, float u1, float u2, float v1, float v2, float r, float g, float b, float a) {
        addVertex(builder, matrixStack, x1, y, z1, u1, v1, r, g, b, a);
        addVertex(builder, matrixStack, x1, y, z2, u2, v1, r, g, b, a);
        addVertex(builder, matrixStack, x2, y, z2, u2, v2, r, g, b, a);
        addVertex(builder, matrixStack, x2, y, z1, u1, v2, r, g, b, a);
    }

    private static final float x1 = 1/16f;
    private static final float x2 = 15/16f;
    //private final float y1 = 15/16f;
    private static final float y2 = 2/16f;
    private static final float z1 = 1/16f;
    private static final float z2 = 15/16f;

    @Override
    public void render(AluminumTankTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        LazyOptional<IFluidHandler> cap = tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
        FluidStack fluid = cap.map(h -> h.getFluidInTank(0)).orElse(FluidStack.EMPTY);
        if(!fluid.isEmpty()) {
            float fluidAmount = cap.map(h -> 13f * (float )h.getFluidInTank(0).getAmount() / (float)h.getTankCapacity(0)).orElse(0f);
            TextureAtlasSprite fluidSprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(fluid.getFluid().getAttributes().getStillTexture(fluid));
            matrixStack.push();
            matrixStack.translate(0.5f, 0f, 0.5f);
            matrixStack.scale(0.999f, 1f, 0.999f);
            matrixStack.translate(-0.5f, 0f, -0.5f);
            IVertexBuilder builder = buffer.getBuffer(RenderType.getTranslucentNoCrumbling());

            final float y1 = (2f + fluidAmount)/16f;

            final float u1 = fluidSprite.getInterpolatedU(1d);
            final float u2 = fluidSprite.getInterpolatedU(15d);
            final float hv1 = fluidSprite.getInterpolatedV(1d);
            final float hv2 = fluidSprite.getInterpolatedV(14d);
            final float vv1 = fluidSprite.getInterpolatedV(14d - fluidAmount);
            final float vv2 = fluidSprite.getInterpolatedV(14d);

            int col = fluid.getFluid().getAttributes().getColor(fluid);

            final float r = ((col >> 16) % 256) / 255f;
            final float g = ((col >> 8) % 256) / 255f;
            final float b = (col % 256) / 255f;
            final float a = ((col >> 24) % 256) / 255f;

            addHorizontalQuad(builder, matrixStack, x1, x2, y1, z1, z2, u1, u2, hv1, hv2, r, g, b, a);
            addVerticalQuad(builder, matrixStack, x1, x2, y1, y2, z1, z1, u1, u2, vv1, vv2, r, g, b, a);
            addVerticalQuad(builder, matrixStack, x2, x1, y1, y2, z2, z2, u1, u2, vv1, vv2, r, g, b, a);
            addVerticalQuad(builder, matrixStack, x2, x2, y1, y2, z1, z2, u1, u2, vv1, vv2, r, g, b, a);
            addVerticalQuad(builder, matrixStack, x1, x1, y1, y2, z2, z1, u1, u2, vv1, vv2, r, g, b, a);

            matrixStack.pop();
        }
    }
}
