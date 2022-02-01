package henryrichard.epicwasteoftime.fluid;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.StateContainer;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import javax.annotation.Nullable;
import java.util.function.Function;

public abstract class FlowingFluid extends ForgeFlowingFluid {

    private final Function<IWorldReader, Integer> tickRateMap;

    public FlowingFluid(ForgeFlowingFluid.Properties properties, @Nullable Function<IWorldReader, Integer> tickRateMap) {
        super(properties);
        this.tickRateMap = tickRateMap;
    }

    @Override
    public int getTickRate(IWorldReader world) {
        return tickRateMap == null ? super.getTickRate(world) : tickRateMap.apply(world);
    }

    public static class Source extends FlowingFluid {

        public Source(Properties properties, @Nullable Function<IWorldReader, Integer> tickRateMap) {
            super(properties, tickRateMap);
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }

        @Override
        public int getLevel(FluidState state) {
            return 8;
        }
    }

    public static class Flowing extends FlowingFluid {

        public Flowing(Properties properties, @Nullable Function<IWorldReader, Integer> tickRateMap) {
            super(properties, tickRateMap);
        }

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL_1_8);
        }
    }
}
