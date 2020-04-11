package henryrichard.epicwasteoftime.client.event;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.client.model.MaskedToolModel;
import henryrichard.epicwasteoftime.client.render.tileentity.AluminumTankTileEntityRenderer;
import henryrichard.epicwasteoftime.init.EwotTileEntities;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static henryrichard.epicwasteoftime.init.EwotBlocks.*;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public abstract class ClientSetupEvent {

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void doClientStuff(final FMLClientSetupEvent event) {
        //Block render types

        //Since I'm overlaying a texture for these they have to be cutouts, even though they're opaque. Sadness.
        RenderTypeLookup.setRenderLayer(amethyst_ore, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(endust_ore, RenderType.getCutoutMipped());

        RenderTypeLookup.setRenderLayer(slime_ore, RenderType.getTranslucent());

        RenderTypeLookup.setRenderLayer(lavaleaves, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(lavalogged_lavaleaves, RenderType.getCutoutMipped());

        RenderTypeLookup.setRenderLayer(lavaleaf_sapling, RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(aluminum_tank, RenderType.getCutoutMipped());

        //Tile Entities
        ClientRegistry.bindTileEntityRenderer(EwotTileEntities.aluminum_tank, AluminumTankTileEntityRenderer::new);
        
        //Model Loaders
        ModelLoaderRegistry.registerLoader(new ResourceLocation(EwotMain.MODID, "masked_tool"), MaskedToolModel.Loader.INSTANCE);
    }
}
