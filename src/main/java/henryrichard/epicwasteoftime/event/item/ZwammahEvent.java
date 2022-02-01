package henryrichard.epicwasteoftime.event.item;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.block.FakeAnvilBlock;
import henryrichard.epicwasteoftime.init.EwotBlocks;
import henryrichard.epicwasteoftime.item.weapon.ZwammahItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class ZwammahEvent {

    @SubscribeEvent
    public static void zwammahAttack(final AttackEntityEvent event) {
        if(
                !event.getPlayer().getEntityWorld().isRemote &&
                event.getPlayer().getHeldItemMainhand().getItem() instanceof ZwammahItem &&
                event.getPlayer().getCooledAttackStrength(0f) >= 1f &&
                event.getTarget() instanceof LivingEntity
        ) {
            event.setCanceled(true);
            ((LivingEntity) event.getTarget()).setHealth(Math.min(5f, ((LivingEntity) event.getTarget()).getHealth()));
            event.getTarget().performHurtAnimation();
            event.getPlayer().getHeldItemMainhand().damageItem(1, event.getPlayer(), (holder) -> holder.sendBreakAnimation(EquipmentSlotType.MAINHAND));

            event.getPlayer().addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1, 4));
            //TODO: Fix
            //event.getPlayer().getEntityWorld().createExplosion(event.getTarget(), DamageSource.causeExplosionDamage(event.getPlayer()), event.getTarget().getPosX(), event.getTarget().getPosY(), event.getTarget().getPosZ(), 2f, true, Explosion.Mode.NONE);

            for(int x = -3; x <= 3; x++) {
                for(int z = -3; z <= 3; z++) {
                    if(Math.sqrt((x*x + z*z)) - event.getPlayer().getRNG().nextFloat() * 2.99f <= 0f) {
                        BlockPos position = event.getTarget().getPosition().add(x, 5, z);
                        //noinspection ConstantConditions
                        event.getTarget().getEntityWorld().setBlockState(position, EwotBlocks.fake_anvil.getDefaultState().with(FakeAnvilBlock.FACING, Direction.Plane.HORIZONTAL.random(event.getPlayer().getRNG())));
                    }
                }
            }

            event.getTarget().setFire(5);
            event.getTarget().setVelocity(0d,0d,0d);
            ((LivingEntity) event.getTarget()).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 40, 255));
        }
    }
}
