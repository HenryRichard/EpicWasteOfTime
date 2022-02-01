package henryrichard.epicwasteoftime.event.entity;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.init.EwotTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID)
public abstract class SpawnTweakEvent {

    private final static String SPAWN_TAG = EwotMain.MOD_ID + ":spawned";

    @SubscribeEvent
    public static void onLivingSpawn(LivingSpawnEvent.SpecialSpawn event) {
        event.getEntityLiving().addTag(SPAWN_TAG);
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if(entity instanceof LivingEntity && entity.getTags().contains(SPAWN_TAG)) {
            entity.removeTag(SPAWN_TAG);

            Random random = ((LivingEntity) entity).getRNG();
            if(entity instanceof WitherSkeletonEntity) {
                if (random.nextInt(20) == 1) {
                    if (random.nextInt(20) == 1) {
                        entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(EwotTools.golden_bow));
                    } else {
                        entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
                    }
                }
            }
        }
    }
}
