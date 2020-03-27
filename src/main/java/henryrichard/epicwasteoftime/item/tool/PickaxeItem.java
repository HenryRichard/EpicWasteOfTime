package henryrichard.epicwasteoftime.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class PickaxeItem extends net.minecraft.item.PickaxeItem {

    public PickaxeItem(IItemTier tier, Item.Properties builder) {
        super(tier, 1, -2.8f, builder);
    }

    public PickaxeItem(IItemTier tier, int damage, Item.Properties builder) {
        super(tier, damage, -2.8f, builder);
    }

}
