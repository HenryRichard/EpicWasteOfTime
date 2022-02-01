package henryrichard.epicwasteoftime.block.tree;

import henryrichard.epicwasteoftime.world.feature.EwotFeatureConfigs;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

import javax.annotation.Nullable;
import java.util.Random;

public class LavaleafTree extends Tree {

    //TODO: Update for 1.16

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random random, boolean b) {
        return null;
    }

    /*
    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean flowerFlag) {
        return Feature.NORMAL_TREE.withConfiguration(EwotFeatureConfigs.LAVALEAF_TREE_CONFIG);
    }

     */
}
