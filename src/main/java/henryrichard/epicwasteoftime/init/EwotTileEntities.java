package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.block.tileentity.AluminumTankTileEntity;
import henryrichard.epicwasteoftime.block.tileentity.CrudePinkiteFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EwotMain.MODID)
public abstract class EwotTileEntities {

    public static final TileEntityType<AluminumTankTileEntity> aluminum_tank = null;

    public static final TileEntityType<CrudePinkiteFurnaceTileEntity> crude_pinkite_furnace = null;

    @SubscribeEvent
    public static void registerTileEntityTypes(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                TileEntityType.Builder.create(AluminumTankTileEntity::new, EwotBlocks.aluminum_tank).build(null).setRegistryName("aluminum_tank"),
                TileEntityType.Builder.create(CrudePinkiteFurnaceTileEntity::new, EwotBlocks.crude_pinkite_furnace).build(null).setRegistryName("crude_pinkite_furnace")
        );
    }

}
