package henryrichard.epicwasteoftime;

import henryrichard.epicwasteoftime.init.EwotBlocks;
import henryrichard.epicwasteoftime.init.EwotItems;
import henryrichard.epicwasteoftime.init.EwotWorldGen;
import henryrichard.epicwasteoftime.item.MusicDiscItem;
import henryrichard.epicwasteoftime.world.feature.EwotFeatureConfigs;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Mod(EwotMain.MODID)
@Mod.EventBusSubscriber(modid = EwotMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EwotMain {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "epicwasteoftime";

    public EwotMain() {
        //I should probably be doing something here
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event) {
        //Setup time!

        //Changing the repair material of vanilla chain armor
        ObfuscationReflectionHelper.setPrivateValue(ArmorMaterial.class, ArmorMaterial.CHAIN, new LazyValue<>(() -> Ingredient.fromItems(EwotItems.iron_chainmail)), "repairMaterial");

        //Make things burn
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFireInfo(EwotBlocks.jeb_wool, 30, 60);
        fire.setFireInfo(EwotBlocks.jeb_carpet, 60, 20);

        //Make things compostable
        ComposterBlock.CHANCES.put(EwotBlocks.lavaleaves, 0.3f);
        ComposterBlock.CHANCES.put(EwotBlocks.lavalogged_lavaleaves, 0.3f);
        ComposterBlock.CHANCES.put(EwotBlocks.lavaleaf_sapling, 0.3f);

        //Register all the new ore generation
        EwotWorldGen.addOreGen();

        //Initialize the feature configs
        EwotFeatureConfigs.init();

        //Finish registering the new records
        for(MusicDiscItem i : MusicDiscItem.NEW_RECORDS) {
            net.minecraft.item.MusicDiscItem.RECORDS.put(i.getSoundEvent(), i);
        }
    }

    public static void enqueueIMC(final InterModEnqueueEvent event) {
        //IMC stuff
    }

    public static void processIMC(final InterModProcessEvent event) {
        //IMC stuff 2: Attack of the Clones
    }
}
