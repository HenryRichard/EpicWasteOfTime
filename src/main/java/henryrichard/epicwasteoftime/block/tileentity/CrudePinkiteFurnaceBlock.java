package henryrichard.epicwasteoftime.block.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

import javax.annotation.Nullable;

public class CrudePinkiteFurnaceBlock extends ContainerBlock {

    public static final BooleanProperty ON = BooleanProperty.create("on");

    public CrudePinkiteFurnaceBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(ON, false));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.get(ON) ? super.getLightValue(state, world, pos) : 0;
    }

    @SuppressWarnings({"NullableProblems", "deprecation"})
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(HORIZONTAL_FACING, ON));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new CrudePinkiteFurnaceTileEntity();
    }
}
