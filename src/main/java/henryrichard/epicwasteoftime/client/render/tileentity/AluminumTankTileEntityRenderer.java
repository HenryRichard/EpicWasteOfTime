package henryrichard.epicwasteoftime.client.render.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import henryrichard.epicwasteoftime.block.tileentity.AluminumTankTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluids;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

@OnlyIn(Dist.CLIENT)
public class AluminumTankTileEntityRenderer extends TileEntityRenderer<AluminumTankTileEntity> {

    private final ModelRenderer fluidModel;

    public AluminumTankTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
        fluidModel = new ModelRenderer(16, 16, 0,0);
        fluidModel.addBox(1f, 2f, 1f, 14f, 1f, 14f);
    }

    @Override
    public void render(AluminumTankTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        LazyOptional<IFluidHandler> cap = tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
        float fluidAmount = cap.map(h -> 13f * h.getFluidInTank(0).getAmount() / h.getTankCapacity(0)).orElse(0f);
        if(fluidAmount > 0f) {
            Material fluidTexture = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, cap.map(h -> h.getFluidInTank(0).getFluid().getAttributes().getStillTexture(h.getFluidInTank(0))).orElse(Fluids.WATER.getAttributes().getStillTexture()));
            IVertexBuilder vertexBuilder = fluidTexture.getBuffer(buffer, p -> RenderType.getTranslucent());
            fluidModel.render(matrixStack, vertexBuilder, combinedLight, combinedOverlay);
        }
    }
}
