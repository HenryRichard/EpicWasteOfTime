package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static net.minecraft.item.Rarity.*;

@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EwotMain.MODID)
public abstract class EwotItems {

    public static final Item amethyst = null;
    public static final Item amethyst_shard = null;
    public static final Item aluminum_ingot = null;
    public static final Item aluminum_nugget = null;
    public static final Item pinkite_ingot = null;
    public static final Item pinkite_nugget = null;
    public static final Item unalloyed_endite_ingot = null;
    public static final Item endite_ingot = null;
    public static final Item endite_nugget = null;
    public static final Item endust = null;

    public static final Item iron_chainmail = null;
    public static final Item golden_chainmail = null;
    public static final Item endite_chainmail = null;
    public static final Item pinkite_chainmail = null;

    public static final Item zwammah_head = null;
    public static final Item zwammah_rod = null;
    public static final Item zwammah_hilt = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS)).setRegistryName("amethyst"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS)).setRegistryName("amethyst_shard"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS)).setRegistryName("aluminum_ingot"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS)).setRegistryName("aluminum_nugget"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS)).setRegistryName("pinkite_ingot"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS)).setRegistryName("pinkite_nugget"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS)).setRegistryName("unalloyed_endite_ingot"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS).rarity(RARE)).setRegistryName("endite_ingot"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS).rarity(RARE)).setRegistryName("endite_nugget"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS)).setRegistryName("endust"),

                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS)).setRegistryName("iron_chainmail"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS)).setRegistryName("golden_chainmail"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS).rarity(RARE)).setRegistryName("endite_chainmail"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS)).setRegistryName("pinkite_chainmail"),

                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS).maxStackSize(1)).setRegistryName("zwammah_head"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS).maxStackSize(1)).setRegistryName("zwammah_rod"),
                new Item(new Item.Properties().group(EwotItemGroups.EWOT_ITEMS).maxStackSize(1)).setRegistryName("zwammah_hilt")
        );
    }


}
