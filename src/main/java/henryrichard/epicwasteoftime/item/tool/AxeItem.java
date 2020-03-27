package henryrichard.epicwasteoftime.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class AxeItem extends net.minecraft.item.AxeItem {

    public AxeItem(IItemTier tier, Item.Properties builder) {
        super(tier, 6.0f, -3.0f, builder);
    }

    public AxeItem(IItemTier tier, float damage, float attackSpeed, Item.Properties builder) {
        super(tier, damage, attackSpeed, builder);
    }

}
