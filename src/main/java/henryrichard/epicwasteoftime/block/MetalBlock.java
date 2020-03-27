package henryrichard.epicwasteoftime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.ToolType;

public class MetalBlock extends Block {

    private int harvestLevel;
    private ToolType harvestTool;

    public MetalBlock(Block.Properties builder, int hL, ToolType hT) {
        super(builder);
        this.harvestLevel = hL;
        this.harvestTool = hT;
    }

    public MetalBlock(Block.Properties builder, int hL) {
        this(builder, hL, ToolType.PICKAXE);
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
