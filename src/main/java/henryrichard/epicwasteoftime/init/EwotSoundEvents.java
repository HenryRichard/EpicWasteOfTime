package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EwotMain.MODID)
public abstract class EwotSoundEvents {

    public static final SoundEvent music_disc_strad_remix = null;

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(
                new SoundEvent(new ResourceLocation(EwotMain.MODID, "music_disc.strad_remix")).setRegistryName("music_disc_strad_remix")
        );
    }

}
