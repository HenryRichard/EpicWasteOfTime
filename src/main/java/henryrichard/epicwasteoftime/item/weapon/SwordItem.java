package henryrichard.epicwasteoftime.item.weapon;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class SwordItem extends net.minecraft.item.SwordItem {

    public SwordItem(IItemTier tier, Item.Properties builder) {
        super(tier, 3, -2.4f, builder);
    }
    public SwordItem(IItemTier tier, int damage, Item.Properties builder) {
        super(tier, damage, -2.4f, builder);
    }
}
