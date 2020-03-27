package henryrichard.epicwasteoftime.event.item;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.item.tool.IEnditeTool;
import henryrichard.epicwasteoftime.item.weapon.EnditeSwordItem;
import henryrichard.epicwasteoftime.item.weapon.SwordItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class EnditeToolEvent {

    @SubscribeEvent
    public static void breakSpeed(final PlayerEvent.BreakSpeed event) {
        ItemStack stack = event.getPlayer().getHeldItemMainhand();
        Item item = stack.getItem();
        if(item instanceof TieredItem && item instanceof IEnditeTool && event.getEntity().dimension.equals(DimensionType.THE_END) && item.canHarvestBlock(stack, event.getState())) {
            event.setNewSpeed(event.getOriginalSpeed() * 4f);
        }
    }

    @SubscribeEvent
    public static void breakBlock(final BlockEvent.BreakEvent event) {
        ItemStack stack = event.getPlayer().getHeldItemMainhand();
        Item item = stack.getItem();
        if(item instanceof TieredItem && item instanceof IEnditeTool && !event.getPlayer().dimension.equals(DimensionType.THE_END)) {
            if(item instanceof SwordItem) {
                stack.damageItem(7, event.getPlayer(), e -> e.sendBreakAnimation(stack.getEquipmentSlot()));
            } else {
                stack.damageItem(3, event.getPlayer(), e -> e.sendBreakAnimation(stack.getEquipmentSlot()));
            }
        }
    }

    @SubscribeEvent
    public static void getDamage(final LivingHurtEvent event) {
        if(event.getSource() instanceof EntityDamageSource && event.getSource().getTrueSource() != null) {
            LivingEntity source = (LivingEntity) event.getSource().getTrueSource();
            ItemStack stack = source.getHeldItemMainhand();
            if(source.dimension == DimensionType.THE_END && (stack.getItem() instanceof IEnditeTool || stack.getItem().getToolTypes(stack).contains(ToolType.AXE))) {
                event.setAmount(event.getAmount() * 4f);
            }
        }
    }
}
