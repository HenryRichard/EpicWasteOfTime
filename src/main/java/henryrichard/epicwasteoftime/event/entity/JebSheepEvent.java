package henryrichard.epicwasteoftime.event.entity;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.init.EwotBlocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = EwotMain.MODID)
public abstract class JebSheepEvent {

    @SubscribeEvent
    public static void playerInteract(final PlayerInteractEvent.EntityInteract event) {
        if(
                !event.getWorld().isRemote()
                && event.getTarget().getCustomName() != null
                && event.getTarget().getCustomName().getUnformattedComponentText().equals("jeb_")
                && event.getTarget() instanceof SheepEntity
                && !((SheepEntity)event.getTarget()).getSheared()
                && !((SheepEntity)event.getTarget()).isChild()
                && event.getPlayer().getHeldItem(event.getHand()).getItem() instanceof ShearsItem
        ) {
            SheepEntity target = ((SheepEntity)event.getTarget());
            target.setSheared(true);

            List<ItemStack> drops = new ArrayList<>();

            Random rand = new Random();
            int i = 1 + rand.nextInt(3);

            for(int j = 0; j < i; ++j) { drops.add(new ItemStack(EwotBlocks.jeb_wool)); }

            drops.forEach(d -> {
                ItemEntity ent = target.entityDropItem(d, 1.0F);
                if(ent != null) {
                    ent.setMotion(ent.getMotion().add((double) ((rand.nextFloat() - rand.nextFloat()) * 0.1F), (double) (rand.nextFloat() * 0.05F), (double) ((rand.nextFloat() - rand.nextFloat()) * 0.1F)));
                }
            });

            target.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
            event.getItemStack().damageItem(1, event.getPlayer(), e -> e.sendBreakAnimation(event.getHand()));

            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void sheepDrops(final LivingDropsEvent event) {
        if(
                !event.getEntity().getEntityWorld().isRemote &&
                event.getEntity().getCustomName() != null &&
                event.getEntity().getCustomName().getUnformattedComponentText().equals("jeb_") &&
                event.getEntity() instanceof SheepEntity
        ) {
            event.getDrops().forEach( d -> {
                if(ItemTags.WOOL.contains(d.getItem().getItem())) {
                    d.setItem(new ItemStack(EwotBlocks.jeb_wool, d.getItem().getCount(), d.getItem().getTag()));
                }
            });
        }
    }
}
