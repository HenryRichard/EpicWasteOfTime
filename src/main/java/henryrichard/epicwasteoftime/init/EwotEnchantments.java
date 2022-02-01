package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.enchantment.BluntCurseEnchantment;
import henryrichard.epicwasteoftime.enchantment.UpliftingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static net.minecraft.enchantment.Enchantment.Rarity.*;

@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EwotMain.MOD_ID)
public abstract class EwotEnchantments {

    public static final Enchantment uplifting = null;
    public static final Enchantment blunt_curse = null;

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(
            new UpliftingEnchantment(VERY_RARE).setRegistryName("uplifting"),
            new BluntCurseEnchantment(VERY_RARE).setRegistryName("blunt_curse")
        );
    }
}
