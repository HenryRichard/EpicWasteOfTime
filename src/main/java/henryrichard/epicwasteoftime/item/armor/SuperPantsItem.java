package henryrichard.epicwasteoftime.item.armor;

import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

import java.util.UUID;

public class SuperPantsItem extends ArmorItem {

    public SuperPantsItem(IArmorMaterial material, Properties builder) {
        super(material, EquipmentSlotType.LEGS, builder);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
        if (equipmentSlot == this.slot) {
            multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID.fromString("B9AF74B5-9312-4DD7-AF15-B8B2B98C6002"), "Movement speed", 1.5f, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        return multimap;
    }
}
