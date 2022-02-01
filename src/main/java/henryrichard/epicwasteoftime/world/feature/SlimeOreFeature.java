package henryrichard.epicwasteoftime.world.feature;

import com.mojang.serialization.Codec;
import henryrichard.epicwasteoftime.util.ChunkChecker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class SlimeOreFeature extends OreFeature {
    public SlimeOreFeature(Codec<OreFeatureConfig> p_i231976_1_) {
        super(p_i231976_1_);
    }

    /*
    public SlimeOreFeature(Function<Dynamic<?>, ? extends OreFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, OreFeatureConfig config) {
        ChunkPos chunk = new ChunkPos(pos);
        if(ChunkChecker.isSlimeChunk(worldIn, chunk)) {
            return super.place(worldIn, generator, rand, pos, config);
        } else {
            return false;
        }
    }
     */
}
