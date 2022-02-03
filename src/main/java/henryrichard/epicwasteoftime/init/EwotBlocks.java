package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//@ObjectHolder(EwotMain.MOD_ID)
public abstract class EwotBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EwotMain.MOD_ID);

    //Ore Blocks
    public static final RegistryObject<Block> PYRIVATHITE_ORE = BLOCKS.register("pyrivathite_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).requiresCorrectToolForDrops().strength(3.0F), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENDUST_ORE = BLOCKS.register("endust_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(5.0F, 9.0F), UniformInt.of(1, 6)));

    //Material Blocks
    public static final RegistryObject<Block> PYRIVATHITE_BLOCK = BLOCKS.register("pyrivathite_block", () -> new GlassBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE).sound(EwotSoundEvents.Types.PYRIVATHITE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).noOcclusion().isValidSpawn(EwotBlocks::never).isRedstoneConductor(EwotBlocks::never).isSuffocating(EwotBlocks::never).isViewBlocking(EwotBlocks::never)));
    public static final RegistryObject<Block> ENDUST_BLOCK = BLOCKS.register("endust_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_CYAN).sound(SoundType.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));

    /*

    public static final Block aluminum_ore = null;
    public static final Block pinkite_ore = null;
    public static final Block slime_ore = null;

    //Material Blocks
    public static final Block amethyst_block = null;
    public static final Block endust_block = null;
    public static final Block aluminum_block = null;
    public static final Block pinkite_block = null;
    public static final Block unalloyed_endite_block = null;
    public static final Block endite_block = null;

    //Natural Resources
    public static final Block lavaleaf_log = null;
    public static final Block stripped_lavaleaf_log = null;
    public static final Block lavaleaf_wood = null;
    public static final Block stripped_lavaleaf_wood = null;
    public static final Block lavaleaf_planks = null;
    public static final Block lavaleaf_slab = null;
    public static final Block lavaleaf_stairs = null;
    public static final Block lavaleaves = null;
    public static final Block lavalogged_lavaleaves = null;
    public static final Block lavaleaf_sapling = null;

    public static final Block glowwool = null;
    public static final Block glowwool_carpet = null;
    public static final Block jeb_wool = null;
    public static final Block jeb_carpet = null;

    //Machines
    public static final Block aluminum_tank = null;

    public static final Block crude_pinkite_machine_base = null;
    public static final Block crude_pinkite_furnace = null;

    //Fluids
    public static final Block molten_pinkite = null;

    //Technical Blocks
    public static final Block fake_anvil = null;
     */

    /*
    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(


                new MetalBlock(BlockBehaviour.Properties.of(Material.STONE).hardnessAndResistance(3.0F), 1).setRegistryName("aluminum_ore"),
                new PinkiteMetalBlock(BlockBehaviour.Properties.of(Material.STONE).hardnessAndResistance(3.0F).setLightLevel((state) -> 9), 1).setRegistryName("pinkite_ore"),

                new SlimeOreBlock(EntityType.SLIME, BlockBehaviour.Properties.of(Material.STONE).hardnessAndResistance(3.0F).notSolid()).setRegistryName("slime_ore"),


                new MetalBlock(BlockBehaviour.Properties.of(Material.IRON, MaterialColor.PURPLE).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL), 2).setRegistryName("amethyst_block"),
                new MetalBlock(BlockBehaviour.Properties.of(Material.IRON, MaterialColor.CYAN).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL), 2).setRegistryName("endust_block"),
                new MetalBlock(BlockBehaviour.Properties.of(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL), 1).setRegistryName("aluminum_block"),
                new PinkiteMetalBlock(BlockBehaviour.Properties.of(Material.IRON, MaterialColor.PINK).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).setLightLevel((state) -> 9), 1).setRegistryName("pinkite_block"),
                new MetalBlock(BlockBehaviour.Properties.of(Material.IRON, MaterialColor.LIGHT_BLUE_TERRACOTTA).hardnessAndResistance(4.0F, 5.0F).sound(SoundType.METAL), 2).setRegistryName("unalloyed_endite_block"),
                new MetalBlock(BlockBehaviour.Properties.of(Material.IRON, MaterialColor.CYAN).hardnessAndResistance(6.0F, 10.0F).sound(SoundType.METAL), 3).setRegistryName("endite_block"),


                new StrippableLogBlock(new LazyValue<>(() -> (RotatedPillarBlock) stripped_lavaleaf_log), BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)).setRegistryName("lavaleaf_log"),
                new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)).setRegistryName("stripped_lavaleaf_log"),
                new StrippableLogBlock(new LazyValue<>(() -> (RotatedPillarBlock) stripped_lavaleaf_wood), BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)).setRegistryName("lavaleaf_wood"),
                new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)).setRegistryName("stripped_lavaleaf_wood"),
                new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("lavaleaf_planks"),
                new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("lavaleaf_slab"),
                new StairsBlock(() -> lavaleaf_planks.getDefaultState(), BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("lavaleaf_stairs"),
                new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid()).setRegistryName("lavaleaves"),
                new LavaloggedLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).setLightLevel((state) -> 15).notSolid()).setRegistryName("lavalogged_lavaleaves"),
                new SaplingBlock(new LavaleafTree(), PlantType.NETHER, Tags.Blocks.NETHERRACK::contains, BlockBehaviour.Properties.of(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0f).sound(SoundType.PLANT)).setRegistryName("lavaleaf_sapling"),


                new Block(BlockBehaviour.Properties.of(Material.WOOL).hardnessAndResistance(0.8F).sound(SoundType.CLOTH).setLightLevel((state) -> 15)).setRegistryName("glowwool"),
                new CarpetBlock(DyeColor.YELLOW, BlockBehaviour.Properties.of(Material.CARPET, MaterialColor.SNOW).hardnessAndResistance(0.1F).sound(SoundType.CLOTH).setLightLevel((state) -> 15)).setRegistryName("glowwool_carpet"),
                new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.BROWN).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)).setRegistryName("jeb_wool"),
                new CarpetBlock(DyeColor.BROWN, BlockBehaviour.Properties.of(Material.CARPET, MaterialColor.BROWN).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)).setRegistryName("jeb_carpet"),

                new AluminumTankBlock(BlockBehaviour.Properties.of(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).notSolid()).setRegistryName("aluminum_tank"),

                new MetalBlock(BlockBehaviour.Properties.of(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL), 1).setRegistryName("crude_pinkite_machine_base"),
                new CrudePinkiteFurnaceBlock(BlockBehaviour.Properties.of(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)).setRegistryName("crude_pinkite_furnace"),


                new FlowingFluidBlock(() -> (FlowingFluid) EwotFluids.molten_pinkite, BlockBehaviour.Properties.of(Material.LAVA).doesNotBlockMovement().tickRandomly().hardnessAndResistance(100.0F).setLightLevel((state) -> 5).noDrops()).setRegistryName("molten_pinkite"),


                new FakeAnvilBlock(BlockBehaviour.Properties.of(Material.ANVIL, MaterialColor.IRON).hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL)).setRegistryName("fake_anvil")

        );

    }
    */

    /*
    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {

        event.getRegistry().registerAll(
                new BlockItem(amethyst_ore, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("amethyst_ore"),
                new BlockItem(endust_ore, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("endust_ore"),
                new BlockItem(aluminum_ore, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("aluminum_ore"),
                new BlockItem(pinkite_ore, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("pinkite_ore"),

                new BlockItem(slime_ore, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("slime_ore"),

                new BlockItem(amethyst_block, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("amethyst_block"),
                new BlockItem(endust_block, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("endust_block"),
                new BlockItem(aluminum_block, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("aluminum_block"),
                new BlockItem(pinkite_block, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("pinkite_block"),
                new BlockItem(unalloyed_endite_block, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("unalloyed_endite_block"),
                new BlockItem(endite_block, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS).rarity(Rarity.RARE)).setRegistryName("endite_block"),

                new BlockItem(lavaleaf_log, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("lavaleaf_log"),
                new BlockItem(stripped_lavaleaf_log, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("stripped_lavaleaf_log"),
                new BlockItem(lavaleaf_wood, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("lavaleaf_wood"),
                new BlockItem(stripped_lavaleaf_wood, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("stripped_lavaleaf_wood"),
                new BlockItem(lavaleaf_planks, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("lavaleaf_planks"),
                new BlockItem(lavaleaf_slab, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("lavaleaf_slab"),
                new BlockItem(lavaleaf_stairs, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("lavaleaf_stairs"),
                new BlockItem(lavaleaves, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("lavaleaves"),
                new BlockItem(lavalogged_lavaleaves, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("lavalogged_lavaleaves"),
                new BlockItem(lavaleaf_sapling, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("lavaleaf_sapling"),

                new BlockItem(glowwool, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("glowwool"),
                new BlockItem(glowwool_carpet, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("glowwool_carpet"),
                new BlockItem(jeb_wool, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("jeb_wool"),
                new BlockItem(jeb_carpet, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("jeb_carpet"),

                new AluminumTankBlockItem(aluminum_tank, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("aluminum_tank"),

                new BlockItem(crude_pinkite_machine_base, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("crude_pinkite_machine_base"),
                new BlockItem(crude_pinkite_furnace, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("crude_pinkite_furnace")
        );
    }
     */

    //Copy-pasted from Blocks.java; maybe an AT would've been better?
    private static Boolean never(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entityType) {
        return false;
    }

    private static Boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }

    private static Boolean always(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entityType) {
        return true;
    }

    private static Boolean always(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }

}
