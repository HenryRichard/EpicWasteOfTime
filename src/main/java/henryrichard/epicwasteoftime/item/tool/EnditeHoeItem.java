package henryrichard.epicwasteoftime.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

public class EnditeHoeItem extends net.minecraft.item.HoeItem implements IEnditeTool{

    public EnditeHoeItem(IItemTier tier, float speed, Item.Properties builder) {
        super(tier, speed, builder);
    }

    public EnditeHoeItem(IItemTier tier, Item.Properties builder) { super(tier, (float)(-3 + tier.getHarvestLevel()), builder); }
}
