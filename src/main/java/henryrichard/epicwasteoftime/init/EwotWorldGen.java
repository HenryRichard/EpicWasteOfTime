package henryrichard.epicwasteoftime.init;


import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.world.feature.SlimeOreFeature;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.TheEndBiome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EwotMain.MODID)
public abstract class EwotWorldGen {

    public static final Feature<OreFeatureConfig> slime_ore = null;

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().registerAll(
                new SlimeOreFeature(OreFeatureConfig::deserialize).setRegistryName("slime_ore")
        );
    }

    public static void addOreGen() {
        FillerBlockType END_STONE = FillerBlockType.create("end_stone", "end_stone", new BlockMatcher(Blocks.END_STONE));

        for(Biome b : ForgeRegistries.BIOMES.getValues()) {
            if(b.getCategory() == Biome.Category.NETHER) {
                //Amethyst
                b.addFeature(
                    GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(new OreFeatureConfig(FillerBlockType.NETHERRACK, EwotBlocks.amethyst_ore.getDefaultState(), 4))
                        .withPlacement(
                            Placement.COUNT_RANGE.configure(
                            new CountRangeConfig(4, 0, 0, 128)
                        )
                    )
                );
            } else if (b.getCategory() == Biome.Category.THEEND && !(b instanceof TheEndBiome)) {
                //Endust
                b.addFeature(
                        GenerationStage.Decoration.UNDERGROUND_ORES,
                        Feature.ORE.withConfiguration(new OreFeatureConfig(END_STONE, EwotBlocks.endust_ore.getDefaultState(), 7))
                                .withPlacement(
                                        Placement.COUNT_DEPTH_AVERAGE.configure(
                                                new DepthAverageConfig(2, 30, 16)
                                        )
                                )
                );
            } else if(b.getCategory() != Biome.Category.NONE) {
                //Aluminum
                b.addFeature(
                        GenerationStage.Decoration.UNDERGROUND_ORES,
                        Feature.ORE.withConfiguration(new OreFeatureConfig(FillerBlockType.NATURAL_STONE, EwotBlocks.aluminum_ore.getDefaultState(), 9))
                                .withPlacement(
                                        Placement.COUNT_RANGE.configure(
                                                new CountRangeConfig(6, 16, 0, 32)
                                        )
                                )
                );

                //Pinkite
                b.addFeature(
                        GenerationStage.Decoration.UNDERGROUND_ORES,
                        Feature.ORE.withConfiguration(new OreFeatureConfig(FillerBlockType.NATURAL_STONE, EwotBlocks.pinkite_ore.getDefaultState(), 13))
                                .withPlacement(
                                        Placement.COUNT_RANGE.configure(
                                                new CountRangeConfig(6, 32, 0, 32)
                                        )
                                )
                );

                //Slime
                b.addFeature(
                        GenerationStage.Decoration.UNDERGROUND_ORES,
                        slime_ore.withConfiguration(new OreFeatureConfig(FillerBlockType.NATURAL_STONE, EwotBlocks.slime_ore.getDefaultState(), 8))
                                .withPlacement(
                                        Placement.COUNT_RANGE.configure(
                                                new CountRangeConfig(8, 0, 0, 40)
                                        )
                                )
                );
            }
        }
    }
}
