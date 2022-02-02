package henryrichard.epicwasteoftime;

import henryrichard.epicwasteoftime.init.EwotBlocks;
import henryrichard.epicwasteoftime.init.EwotItems;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EwotMain.MOD_ID)
@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EwotMain {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "epicwasteoftime";

    public EwotMain() {
        EwotBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EwotItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event) {
        //Setup time!

        //Changing the repair material of vanilla chain armor
        //ObfuscationReflectionHelper.setPrivateValue(ArmorMaterial.class, ArmorMaterial.CHAIN, new LazyValue<>(() -> Ingredient.fromItems(EwotItems.iron_chainmail)), "repairMaterial");

        //Make things compostable
        /*
        ComposterBlock.COMPOSTABLES.put(EwotItems.lavaleaves, 0.3f);
        ComposterBlock.COMPOSTABLES.put(EwotItems.lavalogged_lavaleaves, 0.3f);
        ComposterBlock.COMPOSTABLES.put(EwotItems.lavaleaf_sapling, 0.3f);
         */

        //Register all the new ore generation
        //EwotWorldGen.addOreGen();

        //Initialize the feature configs
        //EwotFeatureConfigs.init();

        //Finish registering the new records
        //for(MusicDiscItem i : MusicDiscItem.NEW_RECORDS) {
        //    net.minecraft.item.MusicDiscItem.RECORDS.put(i.getSoundEvent(), i);
        //}
    }

    public static void enqueueIMC(final InterModEnqueueEvent event) {
        //IMC stuff
    }

    public static void processIMC(final InterModProcessEvent event) {
        //IMC stuff 2: Attack of the Clones
    }
}
