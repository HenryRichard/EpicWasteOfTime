package henryrichard.epicwasteoftime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.PlantType;

import java.util.function.Predicate;

public class SaplingBlock extends net.minecraft.block.SaplingBlock {

    private final PlantType plantType;
    private final Predicate<Block> validBlock;

    public SaplingBlock(Tree treeIn, PlantType plantType, Predicate<Block> validBlock, Properties properties) {
        super(treeIn, properties);
        this.plantType = plantType;
        this.validBlock = validBlock;
    }

    public SaplingBlock(Tree treeIn, PlantType plantType, Properties properties) {
        this(treeIn, plantType, block -> false, properties);
    }

    public SaplingBlock(Tree treeIn, Predicate<Block> validBlock, Properties properties) {
        this(treeIn, PlantType.PLAINS, validBlock, properties);
    }

    public SaplingBlock(Tree treeIn, Properties properties) {
        this(treeIn, PlantType.PLAINS, properties);
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return validBlock.test(state.getBlock());
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return plantType;
    }
}
