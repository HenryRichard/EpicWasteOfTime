package henryrichard.epicwasteoftime.util;

import henryrichard.epicwasteoftime.EwotMain;
import henryrichard.epicwasteoftime.init.EwotBlocks;
import henryrichard.epicwasteoftime.init.EwotItems;
import henryrichard.epicwasteoftime.init.EwotRecipes;
import henryrichard.epicwasteoftime.init.EwotTools;
import net.minecraft.advancements.criterion.EnterBlockTrigger;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.util.Iterator;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = EwotMain.MOD_ID)
public abstract class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new Recipes(generator));
    }

    @SuppressWarnings("ConstantConditions")
    private static class Recipes extends RecipeProvider {

        private static final String ORE_PATH = "ores/";
        private static final String MATERIALS_PATH = "materials/";
        private static final String WOOD_PATH = "wood/";

        public Recipes(DataGenerator generatorIn) {
            super(generatorIn);
        }

        /**
         * Registers all recipes to the given consumer.
         */
        @Override
        protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

            ShapedRecipeBuilder.shapedRecipe(EwotBlocks.glowwool_carpet, 3)
                    .patternLine("XX")
                    .key('X', EwotBlocks.glowwool)
                    .addCriterion("has_glowwool", hasItem(EwotBlocks.glowwool))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, "glowwool_carpet"));

            ShapedRecipeBuilder.shapedRecipe(EwotBlocks.jeb_carpet, 3)
                    .patternLine("XX")
                    .key('X', EwotBlocks.jeb_wool)
                    .addCriterion("has_jeb_wool", hasItem(EwotBlocks.jeb_wool))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, "jeb_carpet"));

            CustomRecipeBuilder.customRecipe((SpecialRecipeSerializer<?>) EwotRecipes.crafting_special_lava_from_lavaleaves).build(consumer, "epicwasteoftime:lava_from_lavaleaves");



            ShapelessRecipeBuilder.shapelessRecipe(EwotBlocks.lavaleaf_planks)
                    .addIngredient(EwotTags.Items.LAVALEAF_LOGS)
                    .addCriterion("has_lavaleaf_logs", hasItem(EwotTags.Items.LAVALEAF_LOGS))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, WOOD_PATH + "lavaleaf/lavaleaf_planks"));

            this.addSlabAndStairs(consumer, "lavaleaf", Ingredient.fromItems(EwotBlocks.lavaleaf_planks),
                    "lavaleaf_planks", Ingredient.fromItems(EwotBlocks.lavaleaf_planks),"wooden",
                    WOOD_PATH + "lavaleaf/", EwotBlocks.lavaleaf_slab, EwotBlocks.lavaleaf_stairs
            );

            ShapedRecipeBuilder.shapedRecipe(EwotBlocks.lavaleaf_wood)
                    .patternLine("XX")
                    .patternLine("XX")
                    .key('X', EwotBlocks.lavaleaf_log)
                    .addCriterion("has_lavaleaf_log", hasItem(EwotBlocks.lavaleaf_log))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, WOOD_PATH + "lavaleaf/lavaleaf_wood"));

            ShapedRecipeBuilder.shapedRecipe(EwotBlocks.stripped_lavaleaf_wood)
                    .patternLine("XX")
                    .patternLine("XX")
                    .key('X', EwotBlocks.stripped_lavaleaf_log)
                    .addCriterion("has_stripped_lavaleaf_log", hasItem(EwotBlocks.stripped_lavaleaf_log))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, WOOD_PATH + "lavaleaf/strippped_lavaleaf_wood"));



            //These will need to have their furnace recipe count changed; to make the code generate that would be too much work and I'm lazy lol
            //Commenting these out because they broke
            //addChainmailRecipe(EwotItems.endite_chainmail, EwotItems.endite_nugget, Ingredient.fromTag(EwotTags.Items.NUGGETS_ENDITE), IngredientHelper.fromTags(EwotTags.Items.INGOTS_ENDITE, EwotTags.Items.NUGGETS_ENDITE), "endite", consumer);
            //addChainmailRecipe(EwotItems.pinkite_chainmail, EwotItems.pinkite_nugget, Ingredient.fromTag(EwotTags.Items.NUGGETS_PINKITE), IngredientHelper.fromTags(EwotTags.Items.INGOTS_PINKITE, EwotTags.Items.NUGGETS_PINKITE), "pinkite", consumer);
            //addChainmailRecipe(EwotItems.iron_chainmail, Items.IRON_NUGGET, Ingredient.fromTag(Tags.Items.NUGGETS_IRON), IngredientHelper.fromTags(Tags.Items.INGOTS_IRON, Tags.Items.NUGGETS_IRON), "iron", consumer);
            //addChainmailRecipe(EwotItems.golden_chainmail, Items.GOLD_NUGGET, Ingredient.fromTag(Tags.Items.NUGGETS_GOLD), IngredientHelper.fromTags(Tags.Items.INGOTS_GOLD, Tags.Items.NUGGETS_GOLD), "gold", consumer);

            addOreRecipes("aluminum",
                    Ingredient.fromTag(EwotTags.Items.ORE_ALUMINUM),
                    Ingredient.fromTag(EwotTags.Items.NUGGETS_ALUMINUM),
                    Ingredient.fromTag(EwotTags.Items.INGOTS_ALUMINUM),
                    Ingredient.fromTag(EwotTags.Items.STORAGE_BLOCKS_ALUMINUM),
                    EwotItems.aluminum_nugget,
                    EwotItems.aluminum_ingot,
                    EwotBlocks.aluminum_block.asItem(),
                    consumer);

            addOreRecipes("amethyst",
                    Ingredient.fromTag(EwotTags.Items.ORE_AMETHYST),
                    Ingredient.fromTag(EwotTags.Items.NUGGETS_AMETHYST),
                    Ingredient.fromTag(EwotTags.Items.GEMS_AMETHYST),
                    Ingredient.fromTag(EwotTags.Items.STORAGE_BLOCKS_AMETHYST),
                    EwotItems.amethyst_shard,
                    EwotItems.amethyst,
                    EwotBlocks.amethyst_block.asItem(),
                    consumer);

            addOreRecipes("pinkite",
                    Ingredient.fromTag(EwotTags.Items.ORE_PINKITE),
                    Ingredient.fromTag(EwotTags.Items.NUGGETS_PINKITE),
                    Ingredient.fromTag(EwotTags.Items.INGOTS_PINKITE),
                    Ingredient.fromTag(EwotTags.Items.STORAGE_BLOCKS_PINKITE),
                    EwotItems.pinkite_nugget,
                    EwotItems.pinkite_ingot,
                    EwotBlocks.pinkite_block.asItem(),
                    consumer);

            addOreRecipes("endust",
                    Ingredient.fromTag(EwotTags.Items.ORE_ENDUST),
                    Ingredient.fromTag(EwotTags.Items.DUST_ENDUST),
                    Ingredient.fromTag(EwotTags.Items.STORAGE_BLOCKS_ENDUST),
                    EwotItems.endust,
                    EwotBlocks.endust_block.asItem(),
                    consumer);


            ShapedRecipeBuilder.shapedRecipe(EwotBlocks.endite_block)
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .key('X', EwotTags.Items.INGOTS_ENDITE)
                    .addCriterion("has_endite_ingot", hasItem(EwotTags.Items.INGOTS_ENDITE))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + "endite/endite_block_from_ingots"));

            ShapelessRecipeBuilder.shapelessRecipe(EwotItems.endite_ingot, 9)
                    .addIngredient(EwotTags.Items.STORAGE_BLOCKS_ENDITE)
                    .addCriterion("has_endite_block", hasItem(EwotTags.Items.STORAGE_BLOCKS_ENDITE))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + "endite/endite_ingot_from_block"));

            ShapedRecipeBuilder.shapedRecipe(EwotItems.endite_ingot)
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .key('X', EwotTags.Items.NUGGETS_ENDITE)
                    .addCriterion("has_endite_nugget", hasItem(EwotTags.Items.NUGGETS_ENDITE))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + "endite/endite_ingot_from_nuggets"));

            ShapelessRecipeBuilder.shapelessRecipe(EwotItems.endite_nugget, 9)
                    .addIngredient(EwotTags.Items.INGOTS_ENDITE)
                    .addCriterion("has_endite_ingot", hasItem(EwotTags.Items.INGOTS_ENDITE))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + "endite/endite_nugget"));

            ShapedRecipeBuilder.shapedRecipe(EwotBlocks.unalloyed_endite_block)
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .key('X', EwotTags.Items.INGOTS_UNALLOYED_ENDITE)
                    .addCriterion("has_unalloyed_endite_ingot", hasItem(EwotTags.Items.INGOTS_UNALLOYED_ENDITE))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + "endite/unalloyed_endite_block"));

            ShapelessRecipeBuilder.shapelessRecipe(EwotItems.unalloyed_endite_ingot, 9)
                    .addIngredient(EwotTags.Items.STORAGE_BLOCKS_UNALLOYED_ENDITE)
                    .addCriterion("has_unalloyed_endite_block", hasItem(EwotTags.Items.STORAGE_BLOCKS_UNALLOYED_ENDITE))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + "endite/unalloyed_endite_ingot_from_block"));

            ShapelessRecipeBuilder.shapelessRecipe(EwotItems.unalloyed_endite_ingot, 1)
                    .addIngredient(EwotTags.Items.DUST_ENDUST)
                    .addIngredient(EwotTags.Items.DUST_ENDUST)
                    .addIngredient(EwotTags.Items.DUST_ENDUST)
                    .addIngredient(EwotTags.Items.DUST_ENDUST)
                    .addIngredient(EwotTags.Items.DUST_ENDUST)
                    .addIngredient(EwotTags.Items.DUST_ENDUST)
                    .addIngredient(EwotTags.Items.INGOTS_PINKITE)
                    .addIngredient(Tags.Items.INGOTS_IRON)
                    .addIngredient(Tags.Items.INGOTS_GOLD)
                    .addCriterion("has_gone_to_outer_end", EnterBlockTrigger.Instance.forBlock(Blocks.END_GATEWAY))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + "endite/unalloyed_endite_ingot"));

            CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EwotTags.Items.INGOTS_UNALLOYED_ENDITE), EwotItems.endite_ingot, 4f, 200).addCriterion("has_unalloyed_endite", hasItem(EwotTags.Items.INGOTS_UNALLOYED_ENDITE))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + "endite/endite_ingot_from_blasting"));

            CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EwotTags.Items.STORAGE_BLOCKS_UNALLOYED_ENDITE), EwotBlocks.endite_block, 32f, 1000).addCriterion("has_unalloyed_endite", hasItem(EwotTags.Items.INGOTS_UNALLOYED_ENDITE))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + "endite/endite_block_from_blasting"));

            this.addFullToolSet(consumer, "amethyst", Ingredient.fromTag(EwotTags.Items.GEMS_AMETHYST),
                    EwotTools.amethyst_sword,
                    EwotTools.amethyst_shovel,
                    EwotTools.amethyst_pickaxe,
                    EwotTools.amethyst_axe,
                    EwotTools.amethyst_hoe,
                    EwotTools.amethyst_spadaxe,
                    EwotTools.amethyst_mattock
            );

            this.addFullToolSet(consumer, "endite", Ingredient.fromTag(EwotTags.Items.INGOTS_ENDITE),
                    EwotTools.endite_sword,
                    EwotTools.endite_shovel,
                    EwotTools.endite_pickaxe,
                    EwotTools.endite_axe,
                    EwotTools.endite_hoe,
                    EwotTools.endite_spadaxe,
                    EwotTools.endite_mattock
            );

            this.addFullToolSet(consumer, "pinkite", Ingredient.fromTag(EwotTags.Items.INGOTS_PINKITE),
                    EwotTools.pinkite_sword,
                    EwotTools.pinkite_shovel,
                    EwotTools.pinkite_pickaxe,
                    EwotTools.pinkite_axe,
                    EwotTools.pinkite_hoe,
                    EwotTools.pinkite_spadaxe,
                    EwotTools.pinkite_mattock
            );

            this.addFullToolSet(consumer, "snow", Ingredient.fromItems(Blocks.SNOW_BLOCK), "snowball", Ingredient.fromItems(Items.SNOWBALL),
                    EwotTools.snow_sword,
                    EwotTools.snow_shovel,
                    EwotTools.snow_pickaxe,
                    EwotTools.snow_axe,
                    EwotTools.snow_hoe,
                    EwotTools.snow_spadaxe,
                    EwotTools.snow_mattock
            );

            this.addNewToolSet(consumer, "wooden", Ingredient.fromTag(ItemTags.PLANKS), "stick", Ingredient.fromTag(Tags.Items.RODS_WOODEN),
                    Items.WOODEN_SHOVEL,
                    Items.WOODEN_PICKAXE,
                    Items.WOODEN_AXE,
                    Items.WOODEN_HOE,
                    EwotTools.wooden_spadaxe,
                    EwotTools.wooden_mattock
            );

            this.addNewToolSet(consumer, "stone", Ingredient.fromTag(Tags.Items.COBBLESTONE), "cobblestone", Ingredient.fromTag(Tags.Items.COBBLESTONE),
                    Items.STONE_SHOVEL,
                    Items.STONE_PICKAXE,
                    Items.STONE_AXE,
                    Items.STONE_HOE,
                    EwotTools.stone_spadaxe,
                    EwotTools.stone_mattock
            );

            this.addNewToolSet(consumer, "iron", Ingredient.fromTag(Tags.Items.INGOTS_IRON), "iron_ingot", Ingredient.fromTag(Tags.Items.INGOTS_IRON),
                    Items.IRON_SHOVEL,
                    Items.IRON_PICKAXE,
                    Items.IRON_AXE,
                    Items.IRON_HOE,
                    EwotTools.iron_spadaxe,
                    EwotTools.iron_mattock
            );

            this.addNewToolSet(consumer, "diamond", Ingredient.fromTag(Tags.Items.GEMS_DIAMOND),
                    Items.DIAMOND_SHOVEL,
                    Items.DIAMOND_PICKAXE,
                    Items.DIAMOND_AXE,
                    Items.DIAMOND_HOE,
                    EwotTools.diamond_spadaxe,
                    EwotTools.diamond_mattock
            );

            this.addNewToolSet(consumer, "golden", Ingredient.fromTag(Tags.Items.INGOTS_GOLD), "gold_ingot", Ingredient.fromTag(Tags.Items.INGOTS_GOLD),
                    Items.GOLDEN_SHOVEL,
                    Items.GOLDEN_PICKAXE,
                    Items.GOLDEN_AXE,
                    Items.GOLDEN_HOE,
                    EwotTools.golden_spadaxe,
                    EwotTools.golden_mattock
            );
            
        }

        private void addSlabAndStairs(Consumer<IFinishedRecipe> consumer, String name, Ingredient material, String triggerName, Ingredient trigger, String group, String path, IItemProvider slab, IItemProvider stairs) {
            ShapedRecipeBuilder.shapedRecipe(slab, 6).key('#', material).patternLine("###").setGroup(group + "_slab").addCriterion("has_" + triggerName, this.hasIngredient(trigger)).build(consumer, new ResourceLocation(EwotMain.MOD_ID, path + name + "_slab"));
            ShapedRecipeBuilder.shapedRecipe(stairs, 4).key('#', material).patternLine("#  ").patternLine("## ").patternLine("###").setGroup(group + "_stairs").addCriterion("has_" + triggerName, this.hasIngredient(trigger)).build(consumer, new ResourceLocation(EwotMain.MOD_ID, path + name + "_stairs"));
        }

        private static final String CHAINMAIL_PATH = MATERIALS_PATH + "chainmail/";
        private void addChainmailRecipe(Item chainmail, Item nugget, Ingredient material, Ingredient trigger, String name, Consumer<IFinishedRecipe> consumer) {
            ShapedRecipeBuilder.shapedRecipe(chainmail)
                    .patternLine("X X")
                    .patternLine(" X ")
                    .patternLine("X X")
                    .key('X', material)
                    .addCriterion("has_" + name, this.hasIngredient(trigger))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, CHAINMAIL_PATH + chainmail.getRegistryName().getPath()));

            CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(chainmail), nugget, 0.1f, 200).addCriterion("has_" + chainmail.getRegistryName().getPath(), this.hasItem(chainmail)).build(consumer, new ResourceLocation(EwotMain.MOD_ID, CHAINMAIL_PATH + nugget.getRegistryName().getPath() + "_from_chainmail_smelting"));
            CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(chainmail), nugget, 0.1f, 100).addCriterion("has_" + chainmail.getRegistryName().getPath(), this.hasItem(chainmail)).build(consumer, new ResourceLocation(EwotMain.MOD_ID, CHAINMAIL_PATH + nugget.getRegistryName().getPath() + "_from_chainmail_blasting"));
        }

        private void addOreRecipes(String name, Ingredient ore, Ingredient ingot, Ingredient block, Item ingotResult, Item blockResult, Consumer<IFinishedRecipe> consumer) {
            ShapedRecipeBuilder.shapedRecipe(blockResult)
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .key('X', ingot)
                    .addCriterion("has_" + ingotResult.getRegistryName().getPath(), hasIngredient(ingot))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + name + '/' + blockResult.getRegistryName().getPath()));

            ShapelessRecipeBuilder.shapelessRecipe(ingotResult, 9)
                    .addIngredient(block)
                    .addCriterion("has_" + blockResult.getRegistryName().getPath(), hasIngredient(block))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + name + '/' + ingotResult.getRegistryName().getPath() + "_from_block"));

            CookingRecipeBuilder.smeltingRecipe(ore, ingotResult, 0.7f, 200).addCriterion("has_" + name + "_ore", hasIngredient(ore))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + name + '/' + ingotResult.getRegistryName().getPath() + "_from_smelting"));

            CookingRecipeBuilder.blastingRecipe(ore, ingotResult, 0.7f, 100).addCriterion("has_" + name + "_ore", hasIngredient(ore))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + name + '/' + ingotResult.getRegistryName().getPath() + "_from_blasting"));
        }

        private void addOreRecipes(String name, Ingredient ore, Ingredient nugget, Ingredient ingot, Ingredient block, Item nuggetResult, Item ingotResult, Item blockResult, Consumer<IFinishedRecipe> consumer) {
            ShapedRecipeBuilder.shapedRecipe(ingotResult)
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .patternLine("XXX")
                    .key('X', nugget)
                    .addCriterion("has_" + nuggetResult.getRegistryName().getPath(), hasIngredient(nugget))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + name + '/' + ingotResult.getRegistryName().getPath() + "_from_nuggets"));

            ShapelessRecipeBuilder.shapelessRecipe(nuggetResult, 9)
                    .addIngredient(ingot)
                    .addCriterion("has_" + ingotResult.getRegistryName().getPath(), hasIngredient(ingot))
                    .build(consumer, new ResourceLocation(EwotMain.MOD_ID, ORE_PATH + name + '/' + nuggetResult.getRegistryName().getPath()));

            addOreRecipes(name, ore, ingot, block, ingotResult, blockResult, consumer);
        }
        
        //Adds all the tools - vanilla and new
        private void addFullToolSet(Consumer<IFinishedRecipe> consumer, String name, Ingredient material, IItemProvider sword, IItemProvider shovel, IItemProvider pickaxe, IItemProvider axe, IItemProvider hoe, IItemProvider spadaxe, IItemProvider mattock) {
            this.addFullToolSet(consumer, name, material, name, material, sword, shovel, pickaxe, axe, hoe, spadaxe, mattock);
        }

        //Adds all the tools - vanilla and new
        //material & trigger should be an IItemProvider, Tag<Item>, or Ingredient
        private void addFullToolSet(Consumer<IFinishedRecipe> consumer, String name, Ingredient material, String triggerName, Ingredient trigger, IItemProvider sword, IItemProvider shovel, IItemProvider pickaxe, IItemProvider axe, IItemProvider hoe, IItemProvider spadaxe, IItemProvider mattock) {
            ShapedRecipeBuilder.shapedRecipe(sword)
                    .key('X', material)
                    .key('#', Tags.Items.RODS_WOODEN)

                    .patternLine("X")
                    .patternLine("X")
                    .patternLine("#")

                    .addCriterion("has_" + triggerName, this.hasIngredient(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MOD_ID, "tools/" + name + "/" + name + "_sword"));

            ShapedRecipeBuilder.shapedRecipe(shovel)
                    .key('X', material)
                    .key('#', Tags.Items.RODS_WOODEN)

                    .patternLine("X")
                    .patternLine("#")
                    .patternLine("#")

                    .addCriterion("has_" + triggerName, this.hasIngredient(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MOD_ID, "tools/" + name + "/" + name + "_shovel"));

            ShapedRecipeBuilder.shapedRecipe(pickaxe)
                    .key('X', material)
                    .key('#', Tags.Items.RODS_WOODEN)

                    .patternLine("XXX")
                    .patternLine(" # ")
                    .patternLine(" # ")

                    .addCriterion("has_" + triggerName, this.hasIngredient(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MOD_ID, "tools/" + name + "/" + name + "_pickaxe"));

            ShapedRecipeBuilder.shapedRecipe(pickaxe)
                    .key('X', material)
                    .key('#', Tags.Items.RODS_WOODEN)

                    .patternLine("XX")
                    .patternLine("X#")
                    .patternLine(" #")

                    .addCriterion("has_" + triggerName, this.hasIngredient(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MOD_ID, "tools/" + name + "/" + name + "_axe"));

            ShapedRecipeBuilder.shapedRecipe(hoe)
                    .key('X', material)
                    .key('#', Tags.Items.RODS_WOODEN)

                    .patternLine("XX")
                    .patternLine(" #")
                    .patternLine(" #")

                    .addCriterion("has_" + triggerName, this.hasIngredient(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MOD_ID, "tools/" + name + "/" + name + "_hoe"));

            this.addNewToolSet(consumer, name, material, triggerName, trigger, shovel, pickaxe, axe, hoe, spadaxe, mattock);
        }

        //Adds the new tools - a spadaxe and mattock
        private void addNewToolSet(Consumer<IFinishedRecipe> consumer, String name, Ingredient material, IItemProvider shovel, IItemProvider pickaxe, IItemProvider axe, IItemProvider hoe, IItemProvider spadaxe, IItemProvider mattock) {
            this.addNewToolSet(consumer, name, material, name, material, shovel, pickaxe, axe, hoe, spadaxe, mattock);
        }

        //Adds the new tools - a spadaxe and mattock
        private void addNewToolSet(Consumer<IFinishedRecipe> consumer, String name, Ingredient material, String triggerName, Ingredient trigger, IItemProvider shovel, IItemProvider pickaxe, IItemProvider axe, IItemProvider hoe, IItemProvider spadaxe, IItemProvider mattock) {
            ShapedRecipeBuilder.shapedRecipe(spadaxe)
                    .key('X', material)
                    .key('T', shovel)

                    .patternLine("XXX")
                    .patternLine(" T ")

                    .addCriterion("has_" + triggerName, this.hasIngredient(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MOD_ID, "tools/" + name + "/" + name + "_spadaxe_from_shovel"));

            ShapedRecipeBuilder.shapedRecipe(spadaxe)
                    .key('X', material)
                    .key('T', pickaxe)

                    .patternLine("X")
                    .patternLine("T")

                    .addCriterion("has_" + triggerName, this.hasIngredient(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MOD_ID, "tools/" + name + "/" + name + "_spadaxe_from_pickaxe"));

            ShapelessRecipeBuilder.shapelessRecipe(spadaxe)
                    .addIngredient(pickaxe)
                    .addIngredient(shovel)

                    .addCriterion("has_" + triggerName, this.hasIngredient(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MOD_ID, "tools/" + name + "/" + name + "_spadaxe_from_pickaxe_and_shovel"));

            ShapedRecipeBuilder.shapedRecipe(mattock)
                    .key('X', material)
                    .key('T', axe)

                    .patternLine("XX")
                    .patternLine(" T")

                    .addCriterion("has_" + triggerName, this.hasIngredient(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MOD_ID, "tools/" + name + "/" + name + "_mattock_from_axe"));

            ShapedRecipeBuilder.shapedRecipe(mattock)
                    .key('X', material)
                    .key('T', hoe)

                    .patternLine("XX")
                    .patternLine("XT")

                    .addCriterion("has_" + triggerName, this.hasIngredient(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MOD_ID, "tools/" + name + "/" + name + "_mattock_from_hoe"));

            ShapelessRecipeBuilder.shapelessRecipe(mattock)
                    .addIngredient(axe)
                    .addIngredient(hoe)

                    .addCriterion("has_" + triggerName, this.hasIngredient(trigger))

                    .build(consumer,  new ResourceLocation(EwotMain.MOD_ID, "tools/" + name + "/" + name + "_mattock_from_axe_and_hoe"));
        }

        private InventoryChangeTrigger.Instance hasIngredient(Ingredient ingredient) {
            final Ingredient.IItemList[] acceptedItems = ObfuscationReflectionHelper.getPrivateValue(Ingredient.class, ingredient, "acceptedItems");
            ItemPredicate.Builder builder = ItemPredicate.Builder.create();
            for(Ingredient.IItemList list : acceptedItems) {
                if(list instanceof Ingredient.SingleItemList) {
                    for (Iterator<Item> it = list.getStacks().stream().map(ItemStack::getItem).iterator(); it.hasNext();) {
                        builder.item(it.next());
                    }
                } else if(list instanceof Ingredient.TagList) {
                    Tag<Item> tag = ObfuscationReflectionHelper.getPrivateValue(Ingredient.TagList.class, (Ingredient.TagList) list, "tag");
                    builder.tag(tag);
                }
            }
            return this.hasItem(builder.build());
        }
    }
}
