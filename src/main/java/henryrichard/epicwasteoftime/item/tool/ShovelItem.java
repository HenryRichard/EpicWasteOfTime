package henryrichard.epicwasteoftime.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class ShovelItem extends net.minecraft.item.ShovelItem {

    public ShovelItem(IItemTier tier, Item.Properties builder) {
        super(tier, 1.5F, -3.0f, builder);
    }
    public ShovelItem(IItemTier tier, float damage, Item.Properties builder) {
        super(tier, damage, -3.0f, builder);
    }

}
