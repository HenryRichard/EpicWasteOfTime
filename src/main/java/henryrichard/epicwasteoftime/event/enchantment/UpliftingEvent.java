package henryrichard.epicwasteoftime.event.enchantment;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.init.EwotEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class UpliftingEvent {

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void upliftingKnockback(final LivingKnockBackEvent event) {
        if(
                !event.getAttacker().getEntityWorld().isRemote &&
                event.getAttacker() instanceof LivingEntity &&
                EnchantmentHelper.getEnchantmentLevel(EwotEnchantments.uplifting, ((LivingEntity) event.getAttacker()).getHeldItemMainhand()) >= 1
        ) {
            event.getEntityLiving().onGround = false;
            double speedY = EnchantmentHelper.getEnchantmentLevel(EwotEnchantments.uplifting, ((LivingEntity) event.getAttacker()).getHeldItemMainhand());
            //x and z are going to be reset so their values don't matter
            event.getEntityLiving().setMotion(0d, speedY, 0d);
        }
    }
}
