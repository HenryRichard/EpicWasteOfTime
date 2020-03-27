package henryrichard.epicwasteoftime.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

public class EnditeHoeItem extends HoeItem {
    public EnditeHoeItem(IItemTier tier, float speed, Properties builder) {
        super(tier, speed, builder);
    }

    public EnditeHoeItem(IItemTier tier, Properties builder) {
        super(tier, builder);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
