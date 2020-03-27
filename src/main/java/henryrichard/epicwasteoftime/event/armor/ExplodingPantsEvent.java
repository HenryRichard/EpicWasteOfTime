package henryrichard.epicwasteoftime.event.armor;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.init.EwotArmor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = {Dist.CLIENT})
public abstract class ExplodingPantsEvent {

    @SubscribeEvent
    public static void checkShouldDoKaboom(final LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        if(!livingEntity.getEntityWorld().isRemote && !(livingEntity instanceof PlayerEntity && ((PlayerEntity)(livingEntity)).isCreative())) {
            ItemStack legs = livingEntity.getItemStackFromSlot(EquipmentSlotType.LEGS);
            if (legs.getItem() == EwotArmor.exploding_pants) {
                event.setCanceled(true);
                legs.damageItem(Integer.MAX_VALUE, livingEntity, e -> e.sendBreakAnimation(EquipmentSlotType.LEGS));
                livingEntity.attackEntityFrom(DamageSource.FIREWORKS, Float.MAX_VALUE);
                Vec3d pos = livingEntity.getPositionVec();
                livingEntity.getEntityWorld().createExplosion(livingEntity, pos.x, pos.y, pos.z, 8F, Explosion.Mode.BREAK);
                
            }
        }
    }

}
