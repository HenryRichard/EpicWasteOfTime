package henryrichard.epicwasteoftime.item;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.init.EwotItems;
import henryrichard.epicwasteoftime.util.EwotTags;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

/*
//protection gen Lua code:

value = 3

local function round(x)
return math.floor(x+0.5)
end

print("new int[] {" .. round(value*0.36 - 0.32) .. ", " .. round(0.6533*value + 0.4933) .. ", " .. round(0.8267*value + 0.7467) .. ", " .. round(0.32*value + 0.16) .. "}")
 */

public enum EwotArmorMaterial implements IArmorMaterial {

    AMETHYST("amethyst", 17, new int[] {2, 5, 7, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.5F, () -> Ingredient.fromTag(EwotTags.Items.GEMS_AMETHYST)),
    ANCIENT_AMETHYST("ancient_amethyst", AMETHYST.durabilityFactor, AMETHYST.damageReduction, AMETHYST.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, AMETHYST.toughness, () -> Ingredient.fromTag(EwotTags.Items.GEMS_AMETHYST)),
    PINKITE("pinkite", 17, new int[] {2, 5, 7, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, () -> Ingredient.fromTag(EwotTags.Items.INGOTS_PINKITE)),
    GOLDEN_CHAINMAIL("golden_chainmail", 5, new int[] {1, 3, 4, 1}, 18, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, () -> Ingredient.fromItems(EwotItems.golden_chainmail)),
    PINKITE_CHAINMAIL("pinkite_chainmail", 17, new int[] {1, 3, 4, 1}, 18, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, () -> Ingredient.fromItems(EwotItems.pinkite_chainmail)),

    EXPLODING_PANTS("exploding_pants", 1, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(Items.LEATHER, Blocks.TNT, Items.CREEPER_SPAWN_EGG)),
    SUPER_PANTS("super_pants", 17, new int[] {0, 7, 0, 0}, 1, SoundEvents.BLOCK_BEACON_ACTIVATE, 1.5F, () -> Ingredient.fromTag(EwotTags.Items.GEMS_AMETHYST));

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};

    private final int durabilityFactor;
    private final int[] damageReduction;
    private final int enchantability;
    private final SoundEvent sound;
    private final LazyValue<Ingredient> repairMaterial;
    private final String name;
    private final float toughness;

    EwotArmorMaterial(String name, int durabilityFactor, int[] damageReduction, int enchantability, SoundEvent sound, float toughness, Supplier<Ingredient> repairMaterial) {
        this.damageReduction = damageReduction;
        this.durabilityFactor = durabilityFactor;
        this.enchantability = enchantability;
        this.sound = sound;
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.name = EwotMain.MODID + ":" + name;
        this.toughness = toughness;
    }

    @Override
    public int getDurability(EquipmentSlotType slot) {
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * durabilityFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slot) {
        return damageReduction[slot.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return sound;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.getValue();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }
}
