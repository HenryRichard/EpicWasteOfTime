package henryrichard.epicwasteoftime.event.armor;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.item.armor.IEnditeArmorMaterial;
import henryrichard.epicwasteoftime.item.armor.EnditeArmorItem;
import henryrichard.epicwasteoftime.util.NBTHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.DimensionType;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class EnditeArmorEvent {

    //TODO: Update for 1.16

    /*
    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    @SubscribeEvent
    public static void equipmentChange(final LivingEquipmentChangeEvent event) {
        if(event.getSlot().getSlotIndex() >= 1 && event.getSlot().getSlotIndex() <= 5) {
            Item newItem = event.getTo().getItem();
            if (newItem instanceof ArmorItem && ((ArmorItem)newItem).getArmorMaterial() instanceof IEnditeArmorMaterial) {
                setAttributeModifier(event.getTo(), event.getEntity().getEntityWorld().getDimensionType());
            }
        }
    }

    @SubscribeEvent
    public static void dimensionChange(final EntityTravelToDimensionEvent event) {
        if(event.getEntity() instanceof LivingEntity) {
            for (ItemStack stack : event.getEntity().getArmorInventoryList()) {
                if (stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getArmorMaterial() instanceof IEnditeArmorMaterial) {
                    setAttributeModifier(stack, event.getDimension());
                }
            }
        }
    }

    private static void setAttributeModifier(ItemStack armorStack, DimensionType dim) {

        ArmorItem armorItem = ((EnditeArmorItem)armorStack.getItem());
        int newProtection;
        if(dim == DimensionType.THE_END) {
            newProtection = ((IEnditeArmorMaterial)armorItem.getArmorMaterial()).getEndDamageReduction(armorItem.getEquipmentSlot());
        } else {
            newProtection = ((IEnditeArmorMaterial)armorItem.getArmorMaterial()).getNormalDamageReduction(armorItem.getEquipmentSlot());
        }

        CompoundNBT newTag = armorStack.getOrCreateTag();
        if(!newTag.contains("DisableDimensionalSwitch", NBTHelper.TAG_BYTE)) {

            ListNBT newAttributeTagList = new ListNBT();

            CompoundNBT compound = Attributes.writeAttributeModifier(new AttributeModifier(ARMOR_MODIFIERS[armorItem.getEquipmentSlot().getIndex()], "Armor modifier", newProtection, AttributeModifier.Operation.ADDITION));
            compound.putString("AttributeName", Attributes.ARMOR);
            compound.putString("Slot", armorItem.getEquipmentSlot().getName());
            newAttributeTagList.add(compound);

            newTag.put("AttributeModifiers", newAttributeTagList);
            armorStack.setTag(newTag);
        }
    }

     */
}
