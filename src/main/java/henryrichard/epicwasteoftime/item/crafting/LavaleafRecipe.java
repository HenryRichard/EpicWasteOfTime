package henryrichard.epicwasteoftime.item.crafting;

import henryrichard.epicwasteoftime.init.EwotBlocks;
import henryrichard.epicwasteoftime.init.EwotRecipes;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import static net.minecraftforge.fluids.FluidAttributes.BUCKET_VOLUME;

public class LavaleafRecipe extends SpecialRecipe {

    public LavaleafRecipe(ResourceLocation idIn) {
        super(idIn);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        int leaves = 0;
        ItemStack fluidContainer = ItemStack.EMPTY;

        for(int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i).copy();
            if (!stack.isEmpty()) {
                if (Block.getBlockFromItem(stack.getItem()) == EwotBlocks.lavalogged_lavaleaves) {
                    leaves++;
                } else {
                    stack.setCount(1);
                    boolean validContainer = FluidUtil.getFluidHandler(stack).map(handler -> handler.fill(new FluidStack(Fluids.LAVA, BUCKET_VOLUME), IFluidHandler.FluidAction.SIMULATE) == BUCKET_VOLUME).orElse(false);
                    if(validContainer && fluidContainer.isEmpty()) { //note to self: isEmpty() refers to an empty itemstack, not empty fluid container
                        fluidContainer = stack;
                    } else {
                        return false;
                    }
                }
            }
        }
        return !fluidContainer.isEmpty() && leaves == 8;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        for(int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i).copy();
            if (!stack.isEmpty() && Block.getBlockFromItem(stack.getItem()) != EwotBlocks.lavalogged_lavaleaves) {
                stack.setCount(1);
                return FluidUtil.getFluidHandler(stack).map(handler -> {
                    handler.fill(new FluidStack(Fluids.LAVA, BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE);
                    return handler.getContainer();
                }).orElse(ItemStack.EMPTY);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 9;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return EwotRecipes.crafting_special_lava_from_lavaleaves;
    }
}
