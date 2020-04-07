package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.item.EwotItemTier;
import henryrichard.epicwasteoftime.item.tool.*;
import henryrichard.epicwasteoftime.item.weapon.*;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EwotMain.MODID)
public abstract class EwotTools {

    //Original
    public static final Item golden_bow = null;

    public static final Item amethyst_sword = null;
    public static final Item amethyst_shovel = null;
    public static final Item amethyst_pickaxe = null;
    public static final Item amethyst_axe = null;
    public static final Item amethyst_spadaxe = null;
    public static final Item amethyst_mattock = null;
    public static final Item amethyst_hoe = null;

    public static final Item pinkite_sword = null;
    public static final Item pinkite_shovel = null;
    public static final Item pinkite_pickaxe = null;
    public static final Item pinkite_axe = null;
    public static final Item pinkite_spadaxe = null;
    public static final Item pinkite_mattock = null;
    public static final Item pinkite_hoe = null;

    public static final Item snow_sword = null;
    public static final Item snow_shovel = null;
    public static final Item snow_pickaxe = null;
    public static final Item snow_axe = null;
    public static final Item snow_spadaxe = null;
    public static final Item snow_mattock = null;
    public static final Item snow_hoe = null;

    public static final Item endite_sword = null;
    public static final Item endite_shovel = null;
    public static final Item endite_pickaxe = null;
    public static final Item endite_axe = null;
    public static final Item endite_spadaxe = null;
    public static final Item endite_mattock = null;
    public static final Item endite_hoe = null;

    //Vanilla
    public static final Item wooden_spadaxe = null;
    public static final Item wooden_mattock = null;

    public static final Item stone_spadaxe = null;
    public static final Item stone_mattock = null;

    public static final Item iron_spadaxe = null;
    public static final Item iron_mattock = null;

    public static final Item golden_spadaxe = null;
    public static final Item golden_mattock = null;

    public static final Item diamond_spadaxe = null;
    public static final Item diamond_mattock = null;

    public static final Item zwammah = null;

    public static final Item book_of_teleportation = null;

    public static final Item molten_pinkite_bucket = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        event.getRegistry().register(new GoldenBowItem(new Item.Properties().group(EwotItemGroups.EWOT_TOOLS).maxDamage(64)).setRegistryName("golden_bow"));

        registerFullToolSet(event, EwotItemTier.AMETHYST, 7.0f, -2.9f, "amethyst");
        registerFullToolSet(event, EwotItemTier.PINKITE, 6.0f,-3.2f,"pinkite");
        registerFullToolSet(event, EwotItemTier.SNOW, 5.0f, -3.2f, "snow");

        event.getRegistry().registerAll(
                new EnditeSwordItem(EwotItemTier.ENDITE, 3, -2.4f, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName("endite_sword"),
                new EnditeShovelItem(EwotItemTier.ENDITE, 1.5F, -3.0f, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName("endite_shovel"),
                new EnditePickaxeItem(EwotItemTier.ENDITE, 1, -2.8f, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName("endite_pickaxe"),
                new EnditeAxeItem(EwotItemTier.ENDITE, 2.0f, -3.0f, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName("endite_axe"),
                new EnditeHoeItem(EwotItemTier.ENDITE, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName("endite_hoe"),
                new EnditeSpadaxeItem(EwotItemTier.ENDITE, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName("endite_spadaxe"),
                new EnditeMattockItem(EwotItemTier.ENDITE, 2.0f, -3.0f, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName("endite_mattock")
        );

        registerNewToolSet(event, ItemTier.WOOD, 6.0f, -3.2f, "wooden");
        registerNewToolSet(event, ItemTier.STONE, 7.0f, -3.2f, "stone");
        registerNewToolSet(event, ItemTier.IRON, 6.0f, -3.1f, "iron");
        registerNewToolSet(event, ItemTier.DIAMOND, 5.0f, -3.0f, "diamond");
        registerNewToolSet(event, ItemTier.GOLD, 6.0f, -3.0f, "golden");

        event.getRegistry().registerAll(
                new ZwammahItem(new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName("zwammah"),
                new TeleportingBookItem(new Item.Properties().group(EwotItemGroups.EWOT_TOOLS).maxStackSize(1).maxDamage(128)).setRegistryName("book_of_teleportation"),

                new BucketItem(() -> EwotFluids.molten_pinkite, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS).maxStackSize(1)).setRegistryName("molten_pinkite_bucket")
        );
    }

    private static void registerFullToolSet(RegistryEvent.Register<Item> event, IItemTier tier, float axeDamage, float axeSpeed, String name) {
        event.getRegistry().registerAll(
                new SwordItem(tier, 3, -2.4f, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName(name + "_sword"),
                new ShovelItem(tier, 1.5F, -3.0f, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName(name + "_shovel"),
                new PickaxeItem(tier, 1, -2.8f, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName(name + "_pickaxe"),
                new AxeItem(tier, axeDamage, axeSpeed, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName(name + "_axe"),
                new HoeItem(tier, (float)(-3 + tier.getHarvestLevel()), new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName(name + "_hoe")
        );
        registerNewToolSet(event, tier, axeDamage, axeSpeed, name);
    }

    private static void registerNewToolSet(RegistryEvent.Register<Item> event, IItemTier tier, float axeDamage, float axeSpeed, String name) {
        event.getRegistry().registerAll(
                new SpadaxeItem(tier, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName(name + "_spadaxe"),
                new MattockItem(tier, axeDamage, axeSpeed, new Item.Properties().group(EwotItemGroups.EWOT_TOOLS)).setRegistryName(name + "_mattock")
        );
    }

}
