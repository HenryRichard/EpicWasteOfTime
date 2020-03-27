package henryrichard.epicwasteoftime.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPinkiteBlock {

    boolean isPowered(BlockState state, World worldIn, BlockPos pos);
}
