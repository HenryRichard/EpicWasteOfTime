package henryrichard.epicwasteoftime.init;

import com.google.common.collect.ImmutableMap;
import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.item.EwotArmorMaterial;
import henryrichard.epicwasteoftime.item.EwotEnditeArmorMaterial;
import henryrichard.epicwasteoftime.item.armor.EnditeArmorItem;
import henryrichard.epicwasteoftime.item.armor.IEnditeArmorMaterial;
import henryrichard.epicwasteoftime.item.armor.SuperPantsItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static net.minecraft.inventory.EquipmentSlotType.FEET;
import static net.minecraft.inventory.EquipmentSlotType.LEGS;
import static net.minecraft.inventory.EquipmentSlotType.CHEST;
import static net.minecraft.inventory.EquipmentSlotType.HEAD;

@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EwotMain.MODID)
public abstract class EwotArmor {

    private static final Map<EquipmentSlotType, String> DEFAULT_PART_NAMES = ImmutableMap.of(HEAD, "helmet", CHEST, "chestplate", LEGS, "leggings", FEET, "boots");

    public static final Item amethyst_boots = null;
    public static final Item amethyst_leggings = null;
    public static final Item amethyst_chestplate = null;
    public static final Item amethyst_helmet = null;

    public static final Item ancient_amethyst_boots = null;
    public static final Item ancient_amethyst_leggings = null;
    public static final Item ancient_amethyst_chestplate = null;
    public static final Item ancient_amethyst_crown = null;

    public static final Item golden_chainmail_boots = null;
    public static final Item golden_chainmail_leggings = null;
    public static final Item golden_chainmail_chestplate = null;
    public static final Item golden_chainmail_helmet = null;

    public static final Item pinkite_boots = null;
    public static final Item pinkite_leggings = null;
    public static final Item pinkite_chestplate = null;
    public static final Item pinkite_helmet = null;

    public static final Item pinkite_chainmail_boots = null;
    public static final Item pinkite_chainmail_leggings = null;
    public static final Item pinkite_chainmail_chestplate = null;
    public static final Item pinkite_chainmail_helmet = null;

    public static final Item endite_boots = null;
    public static final Item endite_leggings = null;
    public static final Item endite_chestplate = null;
    public static final Item endite_helmet = null;

    public static final Item endite_chainmail_boots = null;
    public static final Item endite_chainmail_leggings = null;
    public static final Item endite_chainmail_chestplate = null;
    public static final Item endite_chainmail_helmet = null;


    public static final Item exploding_pants = null;
    public static final Item legendary_amethyst_super_pants = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        //Registering full sets
        registerArmorSet(event, EwotArmorMaterial.AMETHYST, "amethyst");
        registerArmorSet(event, EwotArmorMaterial.ANCIENT_AMETHYST, "ancient_amethyst", ImmutableMap.of(HEAD, "crown"));
        registerArmorSet(event, EwotArmorMaterial.GOLDEN_CHAINMAIL, "golden_chainmail");
        registerArmorSet(event, EwotArmorMaterial.PINKITE, "pinkite");
        registerArmorSet(event, EwotArmorMaterial.PINKITE_CHAINMAIL, "pinkite_chainmail");

        registerEnditeArmorSet(event, EwotEnditeArmorMaterial.ENDITE, "endite");
        registerEnditeArmorSet(event, EwotEnditeArmorMaterial.ENDITE_CHAINMAIL, "endite_chainmail");

        //Registering one-off pieces
        event.getRegistry().registerAll(
                new ArmorItem(EwotArmorMaterial.EXPLODING_PANTS, LEGS, new Item.Properties().group(EwotItemGroups.EWOT_ARMOR)).setRegistryName("exploding_pants"),
                new SuperPantsItem(EwotArmorMaterial.SUPER_PANTS, new Item.Properties().group(EwotItemGroups.EWOT_ARMOR)).setRegistryName("legendary_amethyst_super_pants")
        );
    }

    private static void registerArmorSet(RegistryEvent.Register<Item> event, IArmorMaterial material, String materialName) {
        registerArmorSet(event, material, materialName, null);
    }

    private static void registerArmorSet(RegistryEvent.Register<Item> event, IArmorMaterial material, String materialName, @Nullable Map<EquipmentSlotType, String> nameOverride) {
        Map<EquipmentSlotType, String> partNames = new LinkedHashMap<>(DEFAULT_PART_NAMES);
        if(nameOverride != null) { partNames.putAll(nameOverride); }

        partNames.forEach((k, v) -> {
            event.getRegistry().register(new ArmorItem(material, k, new Item.Properties().group(EwotItemGroups.EWOT_ARMOR)).setRegistryName(materialName + "_" + v));
        });
    }

    private static void registerEnditeArmorSet(RegistryEvent.Register<Item> event, IEnditeArmorMaterial material, String materialName) {
        DEFAULT_PART_NAMES.forEach((k, v) -> {
            event.getRegistry().register(new EnditeArmorItem(material, k, new Item.Properties().group(EwotItemGroups.EWOT_ARMOR)).setRegistryName(materialName + "_" + v));
        });
    }
}
