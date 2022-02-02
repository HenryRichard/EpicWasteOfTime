package henryrichard.epicwasteoftime.client.event;

//@OnlyIn(Dist.CLIENT)
//@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public abstract class ClientSetupEvent {

    /*
    //This whole thing may be unnecessary now?
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
        //ModelLoaderRegistry.registerLoader(new ResourceLocation(EwotMain.MOD_ID, "masked_tool"), MaskedToolModel.Loader.INSTANCE);
    }

     */
}
