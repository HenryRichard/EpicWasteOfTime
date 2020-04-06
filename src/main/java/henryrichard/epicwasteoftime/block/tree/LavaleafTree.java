package henryrichard.epicwasteoftime.block.tree;

import henryrichard.epicwasteoftime.world.feature.EwotFeatureConfigs;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class LavaleafTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean flowerFlag) {
        return Feature.NORMAL_TREE.withConfiguration(EwotFeatureConfigs.LAVALEAF_TREE_CONFIG);
    }
}
