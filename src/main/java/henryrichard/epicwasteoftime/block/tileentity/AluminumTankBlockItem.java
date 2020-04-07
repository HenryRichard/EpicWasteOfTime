package henryrichard.epicwasteoftime.block.tileentity;

import henryrichard.epicwasteoftime.util.LoreMaster;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

public class AluminumTankBlockItem extends BlockItem {

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return null;
    }

    public AluminumTankBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        //CompoundNBT's ID is 10
        if(stack.getTag() != null && stack.getTag().contains("BlockEntityTag", 10) && stack.getTag().getCompound("BlockEntityTag").contains("Fluid", 10)) {
            FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.getTag().getCompound("BlockEntityTag").getCompound("Fluid"));
            if(!fluid.isEmpty()) {
                tooltip.add(fluid.getDisplayName());
                tooltip.add(new StringTextComponent(fluid.getAmount() + " mb"));
            } else {
                addEmpty(tooltip);
            }
        } else {
            addEmpty(tooltip);
        }
    }

    private void addEmpty(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent(LoreMaster.getUnlocalizedLore("block", this.getRegistryName().getPath(), "empty")));
    }

}
