package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.fluid.FlowingFluid;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EwotMain.MOD_ID)
public abstract class EwotFluids {

    public static final Fluid molten_pinkite = null;
    public static final Fluid flowing_molten_pinkite = null;
    public static final Fluid molten_endite = null;
    public static final Fluid flowing_molten_endite = null;

    public static final Fluid pinktrol = null;
    public static final Fluid flowing_pinktrol = null;

    @SubscribeEvent
    public static void registerFluids(RegistryEvent.Register<Fluid> event) {
            registerFluid(
                    event,
                    "molten_pinkite",
                    new ForgeFlowingFluid.Properties(
                            () -> molten_pinkite,
                            () -> flowing_molten_pinkite,
                            FluidAttributes.builder(
                                    new ResourceLocation(EwotMain.MOD_ID, "fluid/molten_pinkite"),
                                    new ResourceLocation(EwotMain.MOD_ID,"fluid/flowing_molten_pinkite")
                            )
                                .density(7000)
                                .luminosity(5)
                                .temperature(940)
                                .viscosity(6000)
                                .sound(SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundEvents.ITEM_BUCKET_EMPTY_LAVA)
                                .translationKey("block." + EwotMain.MOD_ID + ".molten_pinkite")
                    )

                        .levelDecreasePerBlock(2)
                        .explosionResistance(100)
                        .block(() -> (FlowingFluidBlock) EwotBlocks.molten_pinkite)
                        .bucket(() -> EwotTools.molten_pinkite_bucket),
                    (world) -> world.getDimensionType().isUltrawarm() ? 10 : 30
            );
    }

    private static void registerFluid(RegistryEvent.Register<Fluid> event, String name, ForgeFlowingFluid.Properties properties, @Nullable Function<IWorldReader, Integer> tickRateMap) {
        event.getRegistry().registerAll(
                new FlowingFluid.Source(properties, tickRateMap).setRegistryName(name),
                new FlowingFluid.Flowing(properties, tickRateMap).setRegistryName("flowing_" + name)
        );
    }
}
