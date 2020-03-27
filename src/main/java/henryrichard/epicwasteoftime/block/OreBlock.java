package henryrichard.epicwasteoftime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ToolType;

public class OreBlock extends Block {

    private int harvestLevel;
    private ToolType harvestTool;

    private int minXp;
    private int maxXp;

    public OreBlock(Block.Properties builder, int hL, ToolType hT, int minXp, int maxXp) {
        super(builder);
        this.harvestLevel = hL;
        this.harvestTool = hT;

        this.minXp = minXp;
        this.maxXp = maxXp;
    }

    public OreBlock(Block.Properties builder, int hL, int minXp, int maxXp) {
        this(builder, hL, ToolType.PICKAXE, minXp, maxXp);
    }

    public OreBlock(Block.Properties builder, ToolType hT, int minXp, int maxXp) {
        this(builder, 0, hT, minXp, maxXp);
    }

    public OreBlock(Block.Properties builder, int minXp, int maxXp) {
        this(builder, 0, ToolType.PICKAXE, minXp, maxXp);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(RANDOM, minXp, maxXp) : 0;
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return harvestLevel;
    }

    @Override
    public ToolType getHarvestTool(BlockState state) {
        return harvestTool;
    }
}
