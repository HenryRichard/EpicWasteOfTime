package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.potion.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EwotMain.MOD_ID)
public abstract class EwotEffects {

    public static final Effect vertigo = null;

    @SubscribeEvent
    public static void registerEffects(RegistryEvent.Register<Effect> event) {
        event.getRegistry().registerAll(
                new SimpleEffect("vertigo", EffectType.HARMFUL, 0xA29CB5)
        );
    }


}
