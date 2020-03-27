package henryrichard.epicwasteoftime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings("deprecation")
public class PinkiteMetalBlock extends MetalBlock implements IPinkiteBlock {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public PinkiteMetalBlock(Block.Properties builder, int hL, ToolType hT) {
        super(builder, hL, hT);
        this.setDefaultState(this.getDefaultState().with(POWERED, false));
    }

    public PinkiteMetalBlock(Block.Properties builder, int hL) {
        this(builder, hL, ToolType.PICKAXE);
    }

    @Override
    public int getLightValue(BlockState state) {
        return state.get(POWERED) ? super.getLightValue(state) : 0;
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(POWERED, context.getWorld().isBlockPowered(context.getPos()));
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote) {
            boolean on = state.get(POWERED);
            if (on != worldIn.isBlockPowered(pos)) {
                if (on) {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                } else {
                    worldIn.setBlockState(pos, state.cycle(POWERED), 2);
                }
            }

        }
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (state.get(POWERED) && !worldIn.isBlockPowered(pos)) {
            worldIn.setBlockState(pos, state.cycle(POWERED), 2);
        }

    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
        return true;
    }

    public boolean isPowered(BlockState state, World worldIn, BlockPos pos) {
        return state.get(POWERED);
    }
}
