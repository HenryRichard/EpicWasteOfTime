package henryrichard.epicwasteoftime.item.tool;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.common.ToolType;

import java.util.Collections;
import java.util.Set;

public class SpadaxeItem extends ToolItem {

    public SpadaxeItem(IItemTier tier, Item.Properties builder) {
        super(1.1f, -3.0f, tier, Collections.emptySet(), builder);
    }

    public SpadaxeItem(IItemTier tier, float damage, Item.Properties builder) {
        super(damage, -3.0f, tier, Collections.emptySet(), builder);
    }

    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        return ImmutableSet.of(ToolType.SHOVEL, ToolType.PICKAXE);
    }

    @Override
    public boolean canHarvestBlock(BlockState state) {
        Material material = state.getMaterial();
        if(state.getHarvestTool() == ToolType.PICKAXE || state.getHarvestTool() == ToolType.SHOVEL ||material ==  Material.ROCK || material == Material.IRON || material == Material.ANVIL) {
            return state.getHarvestLevel() <= this.getTier().getHarvestLevel();
        }
        return false;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) { return Items.IRON_SHOVEL.onItemUse(context);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
    }
}
