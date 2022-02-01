package henryrichard.epicwasteoftime.block.tileentity;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.init.EwotTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class CrudePinkiteFurnaceTileEntity extends TileEntity implements ITickableTileEntity, ICapabilityProvider {

    private LazyOptional<ItemStackHandler> itemHandler = LazyOptional.of(this::createItemHandler);

    public CrudePinkiteFurnaceTileEntity() {
        super(EwotTileEntities.crude_pinkite_furnace);
    }

    @Override
    public void tick() {
        if(!world.isRemote) {
            itemHandler.ifPresent(handler -> {
                for (int i = 0; i < 3; i++)
                    EwotMain.LOGGER.info(handler.getStackInSlot(i).getDisplayName() + ": " + handler.getStackInSlot(i).getCount());
            });
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        CompoundNBT inventoryCompound = nbt.getCompound("inventory");
        itemHandler.ifPresent(handler -> handler.deserializeNBT(inventoryCompound));

        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        itemHandler.ifPresent(handler -> compound.put("inventory", (handler.serializeNBT())));
        return super.write(compound);
    }

    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }

    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(3) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch(slot) {
                    case 0:
                        return super.isItemValid(slot, stack);

                    case 1:
                        for(ResourceLocation location : stack.getItem().getTags()) {
                            if(location.getPath().contains("pinkite")) {
                                return super.isItemValid(slot, stack);
                            }
                        }
                        return false;

                    case 2:
                    default:
                    return false;
                }
            }
        };
    }
}
