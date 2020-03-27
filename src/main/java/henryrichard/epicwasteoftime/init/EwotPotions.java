package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EwotMain.MODID)
public abstract class EwotPotions {

    public static final Potion vertigo = null;
    public static final Potion vertigo_long = null;
    public static final Potion vertigo_strong = null;

    public static final Potion extreme_nausea = null;

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {
        event.getRegistry().registerAll(
            new Potion(EwotMain.MODID + ".vertigo", new EffectInstance(EwotEffects.vertigo, 600, 0)).setRegistryName("vertigo"),
            new Potion(EwotMain.MODID + ".vertigo", new EffectInstance(EwotEffects.vertigo, 1200, 0)).setRegistryName("vertigo_long"),
            new Potion(EwotMain.MODID + ".vertigo", new EffectInstance(EwotEffects.vertigo, 400, 1)).setRegistryName("vertigo_strong"),

            new Potion(EwotMain.MODID + ".extreme_nausea", new EffectInstance(Effects.NAUSEA, 1200, 0), new EffectInstance(EwotEffects.vertigo, 1200, 2)).setRegistryName("extreme_nausea")
        );
    }


}
