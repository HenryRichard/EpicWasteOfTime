package henryrichard.epicwasteoftime.block.tileentity;

import henryrichard.epicwasteoftime.init.EwotTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
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

    public static final int CAPACITY = 16 * FluidAttributes.BUCKET_VOLUME;

    private LazyOptional<FluidTank> fluidHandler = LazyOptional.of(this::createFluidHandler);

    public AluminumTankTileEntity() {
        super(EwotTileEntities.aluminum_tank);
    }

    private FluidTank createFluidHandler() {
        return new FluidTank(CAPACITY);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        fluidHandler.ifPresent(handler -> handler.readFromNBT(nbt.getCompound("Fluid")));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        fluidHandler.ifPresent(handler -> {
            CompoundNBT fluidTag = new CompoundNBT();
            handler.writeToNBT(fluidTag);
            tag.put("Fluid", fluidTag);
        });
        return super.write(tag);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT mainTag = super.getUpdateTag();
        CompoundNBT fluidTag = new CompoundNBT();
        fluidHandler.ifPresent(handler -> handler.writeToNBT(fluidTag));
        mainTag.put("Fluid", fluidTag);
        return mainTag;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        fluidHandler.ifPresent(handler -> handler.readFromNBT(tag.getCompound("Fluid")));
        super.handleUpdateTag(state, tag);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT fluidTag = new CompoundNBT();
        fluidHandler.ifPresent(handler -> handler.writeToNBT(fluidTag));
        return new SUpdateTileEntityPacket(pos, -49, fluidTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        fluidHandler.ifPresent(handler -> handler.readFromNBT(pkt.getNbtCompound()));
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
