package henryrichard.epicwasteoftime.item.weapon;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GoldenBowItem extends BowItem {
    public GoldenBowItem(Properties builder) {
        super(builder);
    }

    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, 2*timeLeft - this.getUseDuration(stack));
    }
}
