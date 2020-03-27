package henryrichard.epicwasteoftime.block;

import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class LavaloggedLeavesBlock extends LeavesBlock {

    public LavaloggedLeavesBlock(Block.Properties properties) {
        super(properties);
    }

    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0 && !(stack.getItem() instanceof ShearsItem)) {
            worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState().with(FlowingFluidBlock.LEVEL, 3));
        }

    }
}
