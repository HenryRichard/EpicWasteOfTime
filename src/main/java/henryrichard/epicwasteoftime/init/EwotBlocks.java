package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.block.*;
import henryrichard.epicwasteoftime.block.CarpetBlock;
import henryrichard.epicwasteoftime.block.OreBlock;
import henryrichard.epicwasteoftime.block.SaplingBlock;
import henryrichard.epicwasteoftime.block.tileentity.AluminumTankBlock;
import henryrichard.epicwasteoftime.block.tileentity.CrudePinkiteFurnaceBlock;
import henryrichard.epicwasteoftime.block.tree.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.util.LazyValue;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EwotMain.MODID)
public abstract class EwotBlocks {


    //Ore Blocks
    public static final Block amethyst_ore = null;
    public static final Block endust_ore = null;
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

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                new OreBlock(Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).hardnessAndResistance(3.0F), 2, 3, 6).setRegistryName("amethyst_ore"),
                new OreBlock(Block.Properties.create(Material.ROCK, MaterialColor.SAND).hardnessAndResistance(5.0F, 9.0F), 1, 6).setRegistryName("endust_ore"),

                new MetalBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F), 1).setRegistryName("aluminum_ore"),
                new PinkiteMetalBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).lightValue(9), 1).setRegistryName("pinkite_ore"),

                new SlimeOreBlock(EntityType.SLIME, Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).notSolid()).setRegistryName("slime_ore"),


                new MetalBlock(Block.Properties.create(Material.IRON, MaterialColor.PURPLE).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL), 2).setRegistryName("amethyst_block"),
                new MetalBlock(Block.Properties.create(Material.IRON, MaterialColor.CYAN).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL), 2).setRegistryName("endust_block"),
                new MetalBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL), 1).setRegistryName("aluminum_block"),
                new PinkiteMetalBlock(Block.Properties.create(Material.IRON, MaterialColor.PINK).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).lightValue(9), 1).setRegistryName("pinkite_block"),
                new MetalBlock(Block.Properties.create(Material.IRON, MaterialColor.LIGHT_BLUE_TERRACOTTA).hardnessAndResistance(4.0F, 5.0F).sound(SoundType.METAL), 2).setRegistryName("unalloyed_endite_block"),
                new MetalBlock(Block.Properties.create(Material.IRON, MaterialColor.CYAN).hardnessAndResistance(6.0F, 10.0F).sound(SoundType.METAL), 3).setRegistryName("endite_block"),


                new StrippableLogBlock(new LazyValue<>(() -> (LogBlock) stripped_lavaleaf_log), MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)).setRegistryName("lavaleaf_log"),
                new LogBlock(MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)).setRegistryName("stripped_lavaleaf_log"),
                new StrippableLogBlock(new LazyValue<>(() -> (LogBlock) stripped_lavaleaf_wood), MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)).setRegistryName("lavaleaf_wood"),
                new LogBlock(MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)).setRegistryName("stripped_lavaleaf_wood"),
                new Block(Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("lavaleaf_planks"),
                new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("lavaleaf_slab"),
                new StairsBlock(() -> lavaleaf_planks.getDefaultState(), Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("lavaleaf_stairs"),
                new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid()).setRegistryName("lavaleaves"),
                new LavaloggedLeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).lightValue(15).notSolid()).setRegistryName("lavalogged_lavaleaves"),
                new SaplingBlock(new LavaleafTree(), PlantType.Nether, Tags.Blocks.NETHERRACK::contains, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0f).sound(SoundType.PLANT)).setRegistryName("lavaleaf_sapling"),


                new Block(Block.Properties.create(Material.WOOL).hardnessAndResistance(0.8F).sound(SoundType.CLOTH).lightValue(15)).setRegistryName("glowwool"),
                new CarpetBlock(DyeColor.YELLOW, Block.Properties.create(Material.CARPET, MaterialColor.SNOW).hardnessAndResistance(0.1F).sound(SoundType.CLOTH).lightValue(15)).setRegistryName("glowwool_carpet"),
                new Block(Block.Properties.create(Material.WOOL, MaterialColor.BROWN).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)).setRegistryName("jeb_wool"),
                new CarpetBlock(DyeColor.BROWN, Block.Properties.create(Material.CARPET, MaterialColor.BROWN).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)).setRegistryName("jeb_carpet"),

                new AluminumTankBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).notSolid()).setRegistryName("aluminum_tank"),

                new MetalBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL), 1).setRegistryName("crude_pinkite_machine_base"),
                new CrudePinkiteFurnaceBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)).setRegistryName("crude_pinkite_furnace"),


                new FlowingFluidBlock(() -> (FlowingFluid) EwotFluids.molten_pinkite, Block.Properties.create(Material.LAVA).doesNotBlockMovement().tickRandomly().hardnessAndResistance(100.0F).lightValue(5).noDrops()).setRegistryName("molten_pinkite"),


                new FakeAnvilBlock(Block.Properties.create(Material.ANVIL, MaterialColor.IRON).hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL)).setRegistryName("fake_anvil")
        );
    }

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

                new BlockItem(aluminum_tank, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("aluminum_tank"),

                new BlockItem(crude_pinkite_machine_base, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("crude_pinkite_machine_base"),
                new BlockItem(crude_pinkite_furnace, new Item.Properties().group(EwotItemGroups.EWOT_BLOCKS)).setRegistryName("crude_pinkite_furnace")
        );
    }

}
