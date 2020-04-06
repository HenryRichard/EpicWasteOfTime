package henryrichard.epicwasteoftime.block.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

@SuppressWarnings("deprecation")
public class AluminumTankBlock extends ContainerBlock {

    private static final VoxelShape SHAPE_TANK = Block.makeCuboidShape(1d, 1d, 1d, 15d, 16d, 15d);
    private static final VoxelShape SHAPE_FOOT_1 = Block.makeCuboidShape(0d, 0d, 0d, 4d, 1d, 4d);
    private static final VoxelShape SHAPE_FOOT_2 = Block.makeCuboidShape(12d, 0d, 0d, 16d, 1d, 4d);
    private static final VoxelShape SHAPE_FOOT_3 = Block.makeCuboidShape(0d, 0d, 12d, 4d, 1d, 16d);
    private static final VoxelShape SHAPE_FOOT_4 = Block.makeCuboidShape(12d, 0d, 12d, 16d, 1d, 16d);
    private static final VoxelShape SHAPE_FOOT_SIMPLIFIED = Block.makeCuboidShape(0d, 0d, 0d, 16d, 1d, 16d);
    private static final VoxelShape SHAPE_SPIGOT_NORTH = Block.makeCuboidShape(7d, 2d, 0d, 9d, 4d, 1d);
    private static final VoxelShape SHAPE_HANDLE_NORTH = Block.makeCuboidShape(6d, 4d, 0d, 8d, 5d, 1d);
    private static final VoxelShape SHAPE_SPIGOT_SOUTH = Block.makeCuboidShape(7d, 2d, 15d, 9d, 4d, 16d);
    private static final VoxelShape SHAPE_HANDLE_SOUTH = Block.makeCuboidShape(8d, 4d, 15d, 10d, 5d, 16d);
    private static final VoxelShape SHAPE_SPIGOT_EAST = Block.makeCuboidShape(15d, 2d, 7d, 16d, 4d, 9d);
    private static final VoxelShape SHAPE_HANDLE_EAST = Block.makeCuboidShape(15d, 4d, 6d, 16d, 5d, 8d);
    private static final VoxelShape SHAPE_SPIGOT_WEST = Block.makeCuboidShape(0d, 2d, 7d, 1d, 4d, 9d);
    private static final VoxelShape SHAPE_HANDLE_WEST = Block.makeCuboidShape(0d, 4d, 8d, 1d, 5d, 10d);

    private static final VoxelShape SHAPE_COLLISION = VoxelShapes.or(SHAPE_TANK, SHAPE_FOOT_SIMPLIFIED);

    private static final VoxelShape BB_BASE = VoxelShapes.or(SHAPE_TANK, SHAPE_FOOT_1, SHAPE_FOOT_2, SHAPE_FOOT_3, SHAPE_FOOT_4);
    private static final VoxelShape BB_NORTH = VoxelShapes.or(BB_BASE, SHAPE_SPIGOT_NORTH, SHAPE_HANDLE_NORTH);
    private static final VoxelShape BB_SOUTH = VoxelShapes.or(BB_BASE, SHAPE_SPIGOT_SOUTH, SHAPE_HANDLE_SOUTH);
    private static final VoxelShape BB_EAST = VoxelShapes.or(BB_BASE, SHAPE_SPIGOT_EAST, SHAPE_HANDLE_EAST);
    private static final VoxelShape BB_WEST = VoxelShapes.or(BB_BASE, SHAPE_SPIGOT_WEST, SHAPE_HANDLE_WEST);

    public AluminumTankBlock(Properties builder) {
        super(builder);
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.get(HORIZONTAL_FACING))
        {
            case NORTH:
                return BB_NORTH;
            case SOUTH:
                return BB_SOUTH;
            case EAST:
                return BB_EAST;
            case WEST:
            default:
                return BB_WEST;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_COLLISION;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof AluminumTankTileEntity) {
            return tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).map(handler -> handler.getFluidInTank(0).getFluid().getAttributes().getLuminosity())
                    .orElse(0);
        } else {
            return super.getLightValue(state, world, pos);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new AluminumTankTileEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        ItemStack stack = player.getHeldItem(handIn);
        if(stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent() && tileEntity instanceof AluminumTankTileEntity) {
            return tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).map(blockHandler -> {
                FluidActionResult result = FluidUtil.tryFillContainerAndStow(stack, blockHandler, null, Integer.MAX_VALUE, player, true);
                if(result.isSuccess()) {
                    player.setHeldItem(handIn, result.getResult());
                    worldIn.notifyBlockUpdate(pos, state, state, 2);
                    return ActionResultType.SUCCESS;
                } else {
                    result = FluidUtil.tryEmptyContainerAndStow(stack, blockHandler, null, Integer.MAX_VALUE, player, true);
                    if(result.isSuccess()) {
                        player.setHeldItem(handIn, result.getResult());
                        worldIn.notifyBlockUpdate(pos, state, state, 2);
                        return ActionResultType.SUCCESS;
                    }
                }
                return ActionResultType.CONSUME;
            }).orElse(ActionResultType.CONSUME);
        } else {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }
}
