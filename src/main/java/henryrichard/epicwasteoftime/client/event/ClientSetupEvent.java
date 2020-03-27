package henryrichard.epicwasteoftime.client.event;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static henryrichard.epicwasteoftime.init.EwotBlocks.*;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public abstract class ClientSetupEvent {

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void doClientStuff(final FMLClientSetupEvent event) {
        //Since I'm overlaying a texture for these they have to be cutouts, even though they're opaque. Sadness.
        RenderTypeLookup.setRenderLayer(amethyst_ore, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(endust_ore, RenderType.getCutoutMipped());

        RenderTypeLookup.setRenderLayer(slime_ore, RenderType.getTranslucent());

        RenderTypeLookup.setRenderLayer(lavaleaves, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(lavalogged_lavaleaves, RenderType.getCutoutMipped());

        RenderTypeLookup.setRenderLayer(lavaleaf_sapling, RenderType.getCutout());
    }
}
