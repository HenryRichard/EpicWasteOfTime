package henryrichard.epicwasteoftime.item;

import henryrichard.epicwasteoftime.init.EwotItems;
import henryrichard.epicwasteoftime.util.EwotTags;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum EwotItemTier implements IItemTier {
    AMETHYST(678, (float)Math.PI*3, 3.5f, 3, 18, () -> Ingredient.fromTag(EwotTags.Items.GEMS_AMETHYST)),
    PINKITE(1200, 6.0f, 0.8f, 1, 4, () -> Ingredient.fromTag(EwotTags.Items.INGOTS_PINKITE)),
    SNOW(24, 1.1f, -0.5f, 0, 1, () -> Ingredient.fromItems(Blocks.SNOW_BLOCK)),
    ENDITE(2048, 3.25f, 1f, 3, 20, () -> Ingredient.fromTag(EwotTags.Items.INGOTS_ENDITE)),
    ZWAMMAH(1717, 8.0f, 13.0f, 0, 17, () -> Ingredient.fromItems(EwotItems.zwammah_head, EwotItems.zwammah_rod, EwotItems.zwammah_hilt));

    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int harvestLevel;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    EwotItemTier(int use, float eff, float dmg, int hL, int ench, Supplier<Ingredient> rM) {
        this.maxUses = use;
        this.efficiency = eff;
        this.attackDamage = dmg;
        this.harvestLevel = hL;
        this.enchantability = ench;
        this.repairMaterial = new LazyValue<>(rM);
    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }
}
