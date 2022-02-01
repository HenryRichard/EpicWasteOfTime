package henryrichard.epicwasteoftime.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class StrippableLogBlock extends RotatedPillarBlock {

    public final LazyValue<RotatedPillarBlock> stripResult;

    public StrippableLogBlock(LazyValue<RotatedPillarBlock> stripResult, Properties properties) {
        super(properties);
        this.stripResult = stripResult;
    }

    @Override
    @SuppressWarnings({"deprecation"})
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        ItemStack stack = player.getHeldItem(hand);
        if(stack.getToolTypes().contains(ToolType.AXE)) {
            if(!world.isRemote) {
                world.playSound(null, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.setBlockState(pos, stripResult.getValue().getDefaultState().with(RotatedPillarBlock.AXIS, state.get(RotatedPillarBlock.AXIS)));
                stack.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(hand));
            }
            return ActionResultType.SUCCESS;
        } else {
            return super.onBlockActivated(state, world, pos, player, hand, rayTraceResult);
        }
    }
}
