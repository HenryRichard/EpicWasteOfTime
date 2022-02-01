package henryrichard.epicwasteoftime.event.armor;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.init.EwotArmor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.ExplosionContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = {Dist.CLIENT})
public abstract class ExplodingPantsEvent {

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void checkShouldDoKaboom(final LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        if(!livingEntity.getEntityWorld().isRemote && !(livingEntity instanceof PlayerEntity && ((PlayerEntity)(livingEntity)).isCreative())) {
            ItemStack legs = livingEntity.getItemStackFromSlot(EquipmentSlotType.LEGS);
            if (legs.getItem() == EwotArmor.exploding_pants) {
                event.setCanceled(true);
                legs.damageItem(Integer.MAX_VALUE, livingEntity, e -> e.sendBreakAnimation(EquipmentSlotType.LEGS));
                Vector3d pos = livingEntity.getPositionVec();
                Explosion explosion = new Explosion(livingEntity.getEntityWorld(), null, null, null, pos.x, pos.y, pos.z, 8F, false, Explosion.Mode.BREAK);
                explosion.doExplosionA();
                livingEntity.attackEntityFrom(DamageSource.causeExplosionDamage(explosion), Float.MAX_VALUE);
                explosion.doExplosionB(true);
                
            }
        }
    }

}
