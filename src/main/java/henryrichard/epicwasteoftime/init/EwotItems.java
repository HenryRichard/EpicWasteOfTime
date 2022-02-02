package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

//@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//@ObjectHolder(EwotMain.MOD_ID)
public abstract class EwotItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EwotMain.MOD_ID);

    public static final RegistryObject<Item> AMETHYST_ORE = registerBlock("amethyst_ore", EwotBlocks.AMETHYST_ORE);
    public static final RegistryObject<Item> ENDUST_ORE = registerBlock("endust_ore", EwotBlocks.ENDUST_ORE);

    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)));
    public static final RegistryObject<Item> AMETHYST_SHARD = ITEMS.register("amethyst_shard", () -> new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)));;

    public static final RegistryObject<Item> ENDUST = ITEMS.register("endust", () -> new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)));;
    /*
    public static final Item ALUMINUM_INGOT = null;
    public static final Item ALUMINUM_NUGGET = null;
    public static final Item PINKITE_INGOT = null;
    public static final Item PINKITE_NUGGET = null;
    public static final Item UNALLOYED_ENDITE_INGOT = null;
    public static final Item ENDITE_INGOT = null;
    public static final Item ENDITE_NUGGET = null;

    public static final Item IRON_CHAINMAIL = null;
    public static final Item GOLDEN_CHAINMAIL = null;
    public static final Item ENDITE_CHAINMAIL = null;
    public static final Item PINKITE_CHAINMAIL = null;

    public static final Item ZWAMMAH_HEAD = null;
    public static final Item ZWAMMAH_ROD = null;
    public static final Item ZWAMMAH_HILT = null;

    public static final Item MUSIC_DISC_STRAD_REMIX = null;

     */

    /*
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)).setRegistryName("amethyst"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)).setRegistryName("amethyst_shard"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)).setRegistryName("aluminum_ingot"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)).setRegistryName("aluminum_nugget"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)).setRegistryName("pinkite_ingot"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)).setRegistryName("pinkite_nugget"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)).setRegistryName("unalloyed_endite_ingot"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS).rarity(RARE)).setRegistryName("endite_ingot"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS).rarity(RARE)).setRegistryName("endite_nugget"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)).setRegistryName("endust"),

                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)).setRegistryName("iron_chainmail"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)).setRegistryName("golden_chainmail"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS).rarity(RARE)).setRegistryName("endite_chainmail"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS)).setRegistryName("pinkite_chainmail"),

                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS).maxStackSize(1)).setRegistryName("zwammah_head"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS).maxStackSize(1)).setRegistryName("zwammah_rod"),
                new Item(new Item.Properties().tab(EwotCreativeTabs.EWOT_ITEMS).maxStackSize(1)).setRegistryName("zwammah_hilt"),

                new MusicDiscItem(9, () -> EwotSoundEvents.music_disc_strad_remix, (new Item.Properties()).tab(EwotCreativeTabs.EWOT_ITEMS).maxStackSize(1).rarity(RARE)).setRegistryName("music_disc_strad_remix")
        );
    }

     */

    private static RegistryObject<Item> registerBlock(String name, RegistryObject<Block> theBlock, CreativeModeTab theTab) {
        return ITEMS.register(name, () -> new BlockItem(theBlock.get(), new Item.Properties().tab(theTab)));
    }

    private static RegistryObject<Item> registerBlock(String name, RegistryObject<Block> theBlock) {
        return registerBlock(name, theBlock, EwotCreativeTabs.EWOT_BLOCKS);
    }


}
