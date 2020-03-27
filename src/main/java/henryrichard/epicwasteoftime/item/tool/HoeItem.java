package henryrichard.epicwasteoftime.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

public class HoeItem extends net.minecraft.item.HoeItem implements IEnditeTool{

    public HoeItem(IItemTier tier, float speed, Item.Properties builder) {
        super(tier, speed, builder);
    }

    public HoeItem(IItemTier tier, Item.Properties builder) { super(tier, (float)(-3 + tier.getHarvestLevel()), builder); }
}
