package henryrichard.epicwasteoftime.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;

public interface IEnditeArmorMaterial extends IArmorMaterial {

    int getNormalDamageReduction(EquipmentSlotType slot);

    int getEndDamageReduction(EquipmentSlotType slot);

}
