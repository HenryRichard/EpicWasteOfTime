package henryrichard.epicwasteoftime.util;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.init.EwotBlocks;
import henryrichard.epicwasteoftime.init.EwotTools;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.util.function.Consumer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = EwotMain.MODID)
public abstract class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new Recipes(generator));
    }

    private static class Recipes extends RecipeProvider {

        public Recipes(DataGenerator generatorIn) {
            super(generatorIn);
        }

        /**
         * Registers all recipes to the given consumer.
         */
        @Override
        protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

            ShapelessRecipeBuilder.shapelessRecipe(EwotBlocks.lavaleaf_planks)
                    .addIngredient(EwotTags.Items.LAVALEAF_LOGS)
                    .addCriterion("has_lavaleaf_logs", hasItem(EwotTags.Items.LAVALEAF_LOGS))
                    .build(consumer, new ResourceLocation(EwotMain.MODID, "lavaleaf_planks"));

            this.addSlabAndStairs(consumer, "lavaleaf", EwotBlocks.lavaleaf_planks,
                    "lavaleaf_planks", EwotBlocks.lavaleaf_planks,
                    "wooden",
                    EwotBlocks.lavaleaf_slab, EwotBlocks.lavaleaf_stairs
            );

            this.addFullToolSet(consumer, "amethyst", EwotTags.Items.GEMS_AMETHYST,
                    EwotTools.amethyst_sword,
                    EwotTools.amethyst_shovel,
                    EwotTools.amethyst_pickaxe,
                    EwotTools.amethyst_axe,
                    EwotTools.amethyst_hoe,
                    EwotTools.amethyst_spadaxe,
                    EwotTools.amethyst_mattock
            );

            this.addFullToolSet(consumer, "endite", EwotTags.Items.INGOTS_ENDITE,
                    EwotTools.endite_sword,
                    EwotTools.endite_shovel,
                    EwotTools.endite_pickaxe,
                    EwotTools.endite_axe,
                    EwotTools.endite_hoe,
                    EwotTools.endite_spadaxe,
                    EwotTools.endite_mattock
            );

            this.addFullToolSet(consumer, "pinkite", EwotTags.Items.INGOTS_PINKITE,
                    EwotTools.pinkite_sword,
                    EwotTools.pinkite_shovel,
                    EwotTools.pinkite_pickaxe,
                    EwotTools.pinkite_axe,
                    EwotTools.pinkite_hoe,
                    EwotTools.pinkite_spadaxe,
                    EwotTools.pinkite_mattock
            );

            this.addFullToolSet(consumer, "snow", Blocks.SNOW_BLOCK, "snowball", Items.SNOWBALL,
                    EwotTools.snow_sword,
                    EwotTools.snow_shovel,
                    EwotTools.snow_pickaxe,
                    EwotTools.snow_axe,
                    EwotTools.snow_hoe,
                    EwotTools.snow_spadaxe,
                    EwotTools.snow_mattock
            );

            this.addNewToolSet(consumer, "wooden", ItemTags.PLANKS, "stick", Tags.Items.RODS_WOODEN,
                    Items.WOODEN_SHOVEL,
                    Items.WOODEN_PICKAXE,
                    Items.WOODEN_AXE,
                    Items.WOODEN_HOE,
                    EwotTools.wooden_spadaxe,
                    EwotTools.wooden_mattock
            );

            this.addNewToolSet(consumer, "stone", Tags.Items.COBBLESTONE, "cobblestone", Tags.Items.COBBLESTONE,
                    Items.STONE_SHOVEL,
                    Items.STONE_PICKAXE,
                    Items.STONE_AXE,
                    Items.STONE_HOE,
                    EwotTools.stone_spadaxe,
                    EwotTools.stone_mattock
            );

            this.addNewToolSet(consumer, "iron", Tags.Items.INGOTS_IRON, "iron_ingot", Tags.Items.INGOTS_IRON,
                    Items.IRON_SHOVEL,
                    Items.IRON_PICKAXE,
                    Items.IRON_AXE,
                    Items.IRON_HOE,
                    EwotTools.iron_spadaxe,
                    EwotTools.iron_mattock
            );

            this.addNewToolSet(consumer, "diamond", Tags.Items.GEMS_DIAMOND,
                    Items.DIAMOND_SHOVEL,
                    Items.DIAMOND_PICKAXE,
                    Items.DIAMOND_AXE,
                    Items.DIAMOND_HOE,
                    EwotTools.diamond_spadaxe,
                    EwotTools.diamond_mattock
            );

            this.addNewToolSet(consumer, "golden", Tags.Items.INGOTS_GOLD, "gold_ingot", Tags.Items.INGOTS_GOLD,
                    Items.GOLDEN_SHOVEL,
                    Items.GOLDEN_PICKAXE,
                    Items.GOLDEN_AXE,
                    Items.GOLDEN_HOE,
                    EwotTools.golden_spadaxe,
                    EwotTools.golden_mattock
            );
            
        }

        private void addSlabAndStairs(Consumer<IFinishedRecipe> consumer, String name, Object material, String triggerName, Object trigger, String group, IItemProvider slab, IItemProvider stairs) {
            ShapedRecipeBuilder.shapedRecipe(slab, 6).key('#', ingredientFromObject(material)).patternLine("###").setGroup(group + "_slab").addCriterion("has_" + triggerName, this.hasObject(trigger)).build(consumer, new ResourceLocation(EwotMain.MODID, name + "_slab"));
            ShapedRecipeBuilder.shapedRecipe(stairs, 4).key('#', ingredientFromObject(material)).patternLine("#  ").patternLine("## ").patternLine("###").setGroup(group + "_stairs").addCriterion("has_" + triggerName, this.hasObject(trigger)).build(consumer, new ResourceLocation(EwotMain.MODID, name + "_stairs"));
        }

        //Adds all the tools - vanilla and new
        //material & trigger should be an IItemProvider, Tag<Item>, or Ingredient
        private void addFullToolSet(Consumer<IFinishedRecipe> consumer, String name, Object material, IItemProvider sword, IItemProvider shovel, IItemProvider pickaxe, IItemProvider axe, IItemProvider hoe, IItemProvider spadaxe, IItemProvider mattock) {
            this.addFullToolSet(consumer, name, material, name, material, sword, shovel, pickaxe, axe, hoe, spadaxe, mattock);
        }

        //Adds all the tools - vanilla and new
        //material & trigger should be an IItemProvider, Tag<Item>, or Ingredient
        private void addFullToolSet(Consumer<IFinishedRecipe> consumer, String name, Object material, String triggerName, Object trigger, IItemProvider sword, IItemProvider shovel, IItemProvider pickaxe, IItemProvider axe, IItemProvider hoe, IItemProvider spadaxe, IItemProvider mattock) {
            ShapedRecipeBuilder.shapedRecipe(sword)
                    .key('X', ingredientFromObject(material))
                    .key('#', Tags.Items.RODS_WOODEN)

                    .patternLine("X")
                    .patternLine("X")
                    .patternLine("#")

                    .addCriterion("has_" + triggerName, this.hasObject(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MODID, "tools/" + name + "/" + name + "_sword"));

            ShapedRecipeBuilder.shapedRecipe(shovel)
                    .key('X', ingredientFromObject(material))
                    .key('#', Tags.Items.RODS_WOODEN)

                    .patternLine("X")
                    .patternLine("#")
                    .patternLine("#")

                    .addCriterion("has_" + triggerName, this.hasObject(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MODID, "tools/" + name + "/" + name + "_shovel"));

            ShapedRecipeBuilder.shapedRecipe(pickaxe)
                    .key('X', ingredientFromObject(material))
                    .key('#', Tags.Items.RODS_WOODEN)

                    .patternLine("XXX")
                    .patternLine(" # ")
                    .patternLine(" # ")

                    .addCriterion("has_" + triggerName, this.hasObject(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MODID, "tools/" + name + "/" + name + "_pickaxe"));

            ShapedRecipeBuilder.shapedRecipe(pickaxe)
                    .key('X', ingredientFromObject(material))
                    .key('#', Tags.Items.RODS_WOODEN)

                    .patternLine("XX")
                    .patternLine("X#")
                    .patternLine(" #")

                    .addCriterion("has_" + triggerName, this.hasObject(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MODID, "tools/" + name + "/" + name + "_axe"));

            ShapedRecipeBuilder.shapedRecipe(hoe)
                    .key('X', ingredientFromObject(material))
                    .key('#', Tags.Items.RODS_WOODEN)

                    .patternLine("XX")
                    .patternLine(" #")
                    .patternLine(" #")

                    .addCriterion("has_" + triggerName, this.hasObject(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MODID, "tools/" + name + "/" + name + "_hoe"));

            this.addNewToolSet(consumer, name, material, triggerName, trigger, shovel, pickaxe, axe, hoe, spadaxe, mattock);
        }

        //Adds the new tools - a spadaxe and mattock
        //material should be an IItemProvider, Tag<Item>, or Ingredient
        private void addNewToolSet(Consumer<IFinishedRecipe> consumer, String name, Object material, IItemProvider shovel, IItemProvider pickaxe, IItemProvider axe, IItemProvider hoe, IItemProvider spadaxe, IItemProvider mattock) {
            this.addNewToolSet(consumer, name, material, name, material, shovel, pickaxe, axe, hoe, spadaxe, mattock);
        }

        //Adds the new tools - a spadaxe and mattock
        //material & trigger should be an IItemProvider, Tag<Item>, or Ingredient
        private void addNewToolSet(Consumer<IFinishedRecipe> consumer, String name, Object material, String triggerName, Object trigger, IItemProvider shovel, IItemProvider pickaxe, IItemProvider axe, IItemProvider hoe, IItemProvider spadaxe, IItemProvider mattock) {
            ShapedRecipeBuilder.shapedRecipe(spadaxe)
                    .key('X', ingredientFromObject(material))
                    .key('T', shovel)

                    .patternLine("XXX")
                    .patternLine(" T ")

                    .addCriterion("has_" + triggerName, this.hasObject(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MODID, "tools/" + name + "/" + name + "_spadaxe_from_shovel"));

            ShapedRecipeBuilder.shapedRecipe(spadaxe)
                    .key('X', ingredientFromObject(material))
                    .key('T', pickaxe)

                    .patternLine("X")
                    .patternLine("T")

                    .addCriterion("has_" + triggerName, this.hasObject(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MODID, "tools/" + name + "/" + name + "_spadaxe_from_pickaxe"));

            ShapelessRecipeBuilder.shapelessRecipe(spadaxe)
                    .addIngredient(pickaxe)
                    .addIngredient(shovel)

                    .addCriterion("has_" + triggerName, this.hasObject(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MODID, "tools/" + name + "/" + name + "_spadaxe_from_pickaxe_and_shovel"));

            ShapedRecipeBuilder.shapedRecipe(mattock)
                    .key('X', ingredientFromObject(material))
                    .key('T', axe)

                    .patternLine("XX")
                    .patternLine(" T")

                    .addCriterion("has_" + triggerName, this.hasObject(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MODID, "tools/" + name + "/" + name + "_mattock_from_axe"));

            ShapedRecipeBuilder.shapedRecipe(mattock)
                    .key('X', ingredientFromObject(material))
                    .key('T', hoe)

                    .patternLine("XX")
                    .patternLine("XT")

                    .addCriterion("has_" + triggerName, this.hasObject(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MODID, "tools/" + name + "/" + name + "_mattock_from_hoe"));

            ShapelessRecipeBuilder.shapelessRecipe(mattock)
                    .addIngredient(axe)
                    .addIngredient(hoe)

                    .addCriterion("has_" + triggerName, this.hasObject(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MODID, "tools/" + name + "/" + name + "_mattock_from_axe_and_hoe"));
        }

        @SuppressWarnings("all")
        private Ingredient ingredientFromObject(Object obj) {
            if(obj instanceof Tag<?>) {
                try {
                    //yes i know this is very bad I'm sorry
                    //But it works for this use case and is only a called when I generate data, so it's OK
                    //don't ever do this in your own work; put time in to make a better solution
                    return Ingredient.fromTag((Tag<Item>) obj);
                } catch(Exception e) {
                    EwotMain.LOGGER.error("You shall not pass a non-item tag!");
                    e.printStackTrace();
                }
            } else if(obj instanceof IItemProvider) {
                return Ingredient.fromItems((IItemProvider) obj);
            } else if(obj instanceof Ingredient) {
                return (Ingredient) obj;
            }
            EwotMain.LOGGER.error("Can't make " + obj.toString() + " into an Ingredient!");
            return Ingredient.EMPTY;
        }

        @SuppressWarnings("all")
        private InventoryChangeTrigger.Instance hasObject(Object obj) {
            if(obj instanceof Tag<?>) {
                try {
                    return this.hasItem((Tag<Item>) obj);
                } catch(Exception e) {
                    EwotMain.LOGGER.error("You shall not pass a non-item tag!");
                    e.printStackTrace();
                }
            } else if(obj instanceof IItemProvider) {
                return this.hasItem((IItemProvider) obj);
            }
            EwotMain.LOGGER.error("Can't make " + obj.toString() + " into a valid trigger!");
            return this.hasItem(Items.AIR);
        }
    }
}
