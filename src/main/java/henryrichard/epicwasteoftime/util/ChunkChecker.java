package henryrichard.epicwasteoftime.util;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.BiomeManager;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public abstract class ChunkChecker {

    public static boolean isSlimeChunk(IWorld world, ChunkPos pos) {
        return SharedSeedRandom.seedSlimeChunk(pos.x, pos.z, ((ISeedReader)world).getSeed(), 987234911L).nextInt(10) == 0;
    }

}
