package henryrichard.epicwasteoftime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class FlammableBlock extends Block {

    private final int flammability;
    private final int spreadSpeed;

    public FlammableBlock(Properties properties, int flammability, int spreadSpeed) {
        super(properties);
        this.flammability = flammability;
        this.spreadSpeed = spreadSpeed;
    }

    /**
     * Chance that fire will spread and consume this block.
     * 300 being a 100% chance, 0, being a 0% chance.
     *
     * @param state The current state
     * @param world The current world
     * @param pos Block position in world
     * @param face The face that the fire is coming from
     * @return A number ranging from 0 to 300 relating used to determine if the block will be consumed by fire
     */
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face)
    {
        return flammability;
    }

    /**
     * Called when fire is updating, checks if a block face can catch fire.
     *
     *
     * @param state The current state
     * @param world The current world
     * @param pos Block position in world
     * @param face The face that the fire is coming from
     * @return True if the face can be on fire, false otherwise.
     */
    public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face)
    {
        return state.getFlammability(world, pos, face) > 0;
    }

    /**
     * Called when fire is updating on a neighbor block.
     * The higher the number returned, the faster fire will spread around this block.
     *
     * @param state The current state
     * @param world The current world
     * @param pos Block position in world
     * @param face The face that the fire is coming from
     * @return A number that is used to determine the speed of fire growth around the block
     */
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face)
    {
        return spreadSpeed;
    }

}
