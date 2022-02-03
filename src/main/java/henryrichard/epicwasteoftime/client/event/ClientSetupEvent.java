package henryrichard.epicwasteoftime.client.event;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.init.EwotBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public abstract class ClientSetupEvent {

    @SubscribeEvent
    public static void doClientStuff(final FMLClientSetupEvent event) {
        //Block render types
        ItemBlockRenderTypes.setRenderLayer(EwotBlocks.PYRIVATHITE_BLOCK.get(), RenderType.translucent());

        //Tile Entities
        //ClientRegistry.bindTileEntityRenderer(EwotTileEntities.aluminum_tank, AluminumTankTileEntityRenderer::new);
        
        //Model Loaders
        //ModelLoaderRegistry.registerLoader(new ResourceLocation(EwotMain.MOD_ID, "masked_tool"), MaskedToolModel.Loader.INSTANCE);
    }
}
