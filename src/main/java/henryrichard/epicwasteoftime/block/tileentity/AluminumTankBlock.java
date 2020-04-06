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
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nullable;
import javax.swing.*;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

@SuppressWarnings("deprecation")
public class AluminumTankBlock extends ContainerBlock {

    public AluminumTankBlock(Properties builder) {
        super(builder);
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof AluminumTankTileEntity) {
            return tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).map(handler -> handler.getFluidInTank(0).getFluid().getAttributes().getLuminosity())
                    .orElse(super.getLightValue(state, world, pos));
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
                    return ActionResultType.SUCCESS;
                } else {
                    result = FluidUtil.tryEmptyContainerAndStow(stack, blockHandler, null, Integer.MAX_VALUE, player, true);
                    if(result.isSuccess()) {
                        player.setHeldItem(handIn, result.getResult());
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
