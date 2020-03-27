package henryrichard.epicwasteoftime.item.tool;

import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;

public class MattockItem extends AxeItem {
    public MattockItem(IItemTier tier, float damage, float attackSpeed, Item.Properties builder) {
        super(tier, damage, attackSpeed, builder);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        ActionResultType result = super.onItemUse(context);
        if(result != ActionResultType.SUCCESS) {
            return Items.IRON_HOE.onItemUse(context);
        } else {
            return result;
        }
    }
}
