package henryrichard.epicwasteoftime.item.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

import java.util.UUID;

public class SuperPantsItem extends ArmorItem {

    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public SuperPantsItem(IArmorMaterial material, Properties builder) {
        super(material, EquipmentSlotType.LEGS, builder);

        ImmutableMultimap.Builder<Attribute, AttributeModifier> aMB = ImmutableMultimap.builder();
        aMB.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("B9AF74B5-9312-4DD7-AF15-B8B2B98C6002"), "Movement speed", 1.5f, AttributeModifier.Operation.MULTIPLY_TOTAL));
        aMB.putAll(super.getAttributeModifiers(EquipmentSlotType.LEGS));
        attributeModifiers = aMB.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == this.slot ? attributeModifiers : super.getAttributeModifiers(equipmentSlot);
    }
}
