package henryrichard.epicwasteoftime.item;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.init.EwotItems;
import henryrichard.epicwasteoftime.item.armor.IEnditeArmorMaterial;
import henryrichard.epicwasteoftime.util.EwotTags;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum EwotEnditeArmorMaterial implements IEnditeArmorMaterial {

    ENDITE("endite", 49, new int[] {1, 2, 3, 1}, new int[] {4, 8, 11, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.8F, () -> Ingredient.fromTag(EwotTags.Items.INGOTS_ENDITE)),
    ENDITE_CHAINMAIL("endite_chainmail", 27, new int[] {1, 2, 2, 1}, new int[] {3, 5, 7, 3}, 25, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0F, () -> Ingredient.fromItems(EwotItems.endite_chainmail));

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};

    private final int durabilityFactor;
    private final int[] normalDamageReduction;
    private final int[] endDamageReduction;
    private final int enchantability;
    private final SoundEvent sound;
    private final LazyValue<Ingredient> repairMaterial;
    private final String name;
    private final float toughness;

    EwotEnditeArmorMaterial(String name, int durabilityFactor, int[] normalDamageReduction, int[] endDamageReduction, int enchantability, SoundEvent sound, float toughness, Supplier<Ingredient> repairMaterial) {
        this.normalDamageReduction = normalDamageReduction;
        this.endDamageReduction = endDamageReduction;
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
        return normalDamageReduction[slot.getIndex()];
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
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public int getNormalDamageReduction(EquipmentSlotType slot) {
        return normalDamageReduction[slot.getIndex()];
    }

    @Override
    public int getEndDamageReduction(EquipmentSlotType slot) {
        return endDamageReduction[slot.getIndex()];
    }
}
