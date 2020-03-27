package henryrichard.epicwasteoftime.util;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;

public abstract class ChunkChecker {

    public static boolean isSlimeChunk(IWorld world, ChunkPos pos) {
        return SharedSeedRandom.seedSlimeChunk(pos.x, pos.z, world.getSeed(), 987234911L).nextInt(10) == 0;
    }

}
