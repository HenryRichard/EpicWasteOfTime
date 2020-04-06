package henryrichard.epicwasteoftime.block.tileentity;

import henryrichard.epicwasteoftime.init.EwotTileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AluminumTankTileEntity extends TileEntity implements ICapabilityProvider {

    private LazyOptional<FluidTank> fluidHandler = LazyOptional.of(this::createFluidHandler);

    public AluminumTankTileEntity() {
        super(EwotTileEntities.aluminum_tank);
    }

    private FluidTank createFluidHandler() {
        return new FluidTank(32 * FluidAttributes.BUCKET_VOLUME);
    }

    @Override
    public void read(CompoundNBT tag) {
        fluidHandler.ifPresent(handler -> handler.readFromNBT(tag.getCompound("fluid")));
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        fluidHandler.ifPresent(handler -> {
            CompoundNBT fluidTag = new CompoundNBT();
            handler.writeToNBT(fluidTag);
            tag.put("fluid", fluidTag);
        });
        return super.write(tag);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side) {
        if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return fluidHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }
}
