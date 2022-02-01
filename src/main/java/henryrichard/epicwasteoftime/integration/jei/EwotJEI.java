package henryrichard.epicwasteoftime.integration.jei;

import henryrichard.epicwasteoftime.EwotMain;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

import java.util.*;
import java.util.stream.Collectors;

@JeiPlugin
public class EwotJEI implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(EwotMain.MOD_ID, "ewotjei");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        EwotMain.LOGGER.info("EWOT JEI plugin is working!");

        /*
        //Anvil stuff - kinda janky, not a big fan of how it turned out
        //Will probably rework later
        List<Object> anvilRecipes = new ArrayList<>();
        Map<List<ItemStack>, List<ItemStack>> anvilRepairItems = new HashMap<>();

        anvilRepairItems.put(
                listFromTag(EwotTags.Items.GEMS_AMETHYST),

                Lists.newArrayList(
                        new ItemStack(EwotTools.amethyst_sword),
                        new ItemStack(EwotTools.amethyst_shovel),
                        new ItemStack(EwotTools.amethyst_pickaxe),
                        new ItemStack(EwotTools.amethyst_axe),
                        new ItemStack(EwotTools.amethyst_spadaxe),
                        new ItemStack(EwotTools.amethyst_mattock),
                        new ItemStack(EwotTools.amethyst_hoe),

                        new ItemStack(EwotArmor.amethyst_boots),
                        new ItemStack(EwotArmor.amethyst_leggings),
                        new ItemStack(EwotArmor.amethyst_chestplate),
                        new ItemStack(EwotArmor.amethyst_helmet),

                        new ItemStack(EwotArmor.ancient_amethyst_boots),
                        new ItemStack(EwotArmor.ancient_amethyst_leggings),
                        new ItemStack(EwotArmor.ancient_amethyst_chestplate),
                        new ItemStack(EwotArmor.ancient_amethyst_crown),

                        new ItemStack(EwotArmor.legendary_amethyst_super_pants)
                )
        );

        anvilRepairItems.put(
                listFromTag(EwotTags.Items.INGOTS_PINKITE),

                Lists.newArrayList(
                        new ItemStack(EwotTools.pinkite_sword),
                        new ItemStack(EwotTools.pinkite_shovel),
                        new ItemStack(EwotTools.pinkite_pickaxe),
                        new ItemStack(EwotTools.pinkite_axe),
                        new ItemStack(EwotTools.pinkite_spadaxe),
                        new ItemStack(EwotTools.pinkite_mattock),
                        new ItemStack(EwotTools.pinkite_hoe),

                        new ItemStack(EwotArmor.pinkite_boots),
                        new ItemStack(EwotArmor.pinkite_leggings),
                        new ItemStack(EwotArmor.pinkite_chestplate),
                        new ItemStack(EwotArmor.pinkite_helmet)
                )
        );

        anvilRepairItems.put(
                listFromTag(EwotTags.Items.INGOTS_ENDITE),

                Lists.newArrayList(
                        new ItemStack(EwotTools.endite_sword),
                        new ItemStack(EwotTools.endite_shovel),
                        new ItemStack(EwotTools.endite_pickaxe),
                        new ItemStack(EwotTools.endite_axe),
                        new ItemStack(EwotTools.endite_spadaxe),
                        new ItemStack(EwotTools.endite_mattock),
                        new ItemStack(EwotTools.endite_hoe),

                        new ItemStack(EwotArmor.endite_boots),
                        new ItemStack(EwotArmor.endite_leggings),
                        new ItemStack(EwotArmor.endite_chestplate),
                        new ItemStack(EwotArmor.endite_helmet)
                )
        );

        anvilRepairItems.put(
                Collections.singletonList(new ItemStack(Blocks.SNOW_BLOCK)),

                Lists.newArrayList(
                        new ItemStack(EwotTools.snow_sword),
                        new ItemStack(EwotTools.snow_shovel),
                        new ItemStack(EwotTools.snow_pickaxe),
                        new ItemStack(EwotTools.snow_axe),
                        new ItemStack(EwotTools.snow_spadaxe),
                        new ItemStack(EwotTools.snow_mattock),
                        new ItemStack(EwotTools.snow_hoe)
                )
        );

        anvilRepairItems.put(
                listFromTag(ItemTags.PLANKS),
                Lists.newArrayList(new ItemStack(EwotTools.wooden_spadaxe), new ItemStack(EwotTools.wooden_mattock))
        );

        anvilRepairItems.put(
                listFromTag(Tags.Items.COBBLESTONE),
                Lists.newArrayList(new ItemStack(EwotTools.stone_spadaxe), new ItemStack(EwotTools.stone_mattock))
        );

        anvilRepairItems.put(
                listFromTag(Tags.Items.INGOTS_IRON),
                Lists.newArrayList(new ItemStack(EwotTools.iron_spadaxe), new ItemStack(EwotTools.iron_mattock))
        );

        anvilRepairItems.put(
                listFromTag(Tags.Items.GEMS_DIAMOND),
                Lists.newArrayList(new ItemStack(EwotTools.diamond_spadaxe), new ItemStack(EwotTools.diamond_mattock))
        );

        anvilRepairItems.put(
                listFromTag(Tags.Items.INGOTS_GOLD),
                Lists.newArrayList(new ItemStack(EwotTools.golden_spadaxe), new ItemStack(EwotTools.golden_mattock))
        );

        anvilRepairItems.put(
                Collections.singletonList(new ItemStack(EwotItems.pinkite_chainmail)),

                Lists.newArrayList(
                        new ItemStack(EwotArmor.pinkite_chainmail_boots),
                        new ItemStack(EwotArmor.pinkite_chainmail_leggings),
                        new ItemStack(EwotArmor.pinkite_chainmail_chestplate),
                        new ItemStack(EwotArmor.pinkite_chainmail_helmet)
                )
        );

        anvilRepairItems.put(
                Collections.singletonList(new ItemStack(EwotItems.golden_chainmail)),

                Lists.newArrayList(
                        new ItemStack(EwotArmor.golden_chainmail_boots),
                        new ItemStack(EwotArmor.golden_chainmail_leggings),
                        new ItemStack(EwotArmor.golden_chainmail_chestplate),
                        new ItemStack(EwotArmor.golden_chainmail_helmet)
                )
        );

        anvilRepairItems.put(
                Collections.singletonList(new ItemStack(EwotItems.endite_chainmail)),

                Lists.newArrayList(
                        new ItemStack(EwotArmor.endite_chainmail_boots),
                        new ItemStack(EwotArmor.endite_chainmail_leggings),
                        new ItemStack(EwotArmor.endite_chainmail_chestplate),
                        new ItemStack(EwotArmor.endite_chainmail_helmet)
                )
        );

        anvilRepairItems.put(
                Lists.newArrayList(
                        new ItemStack(Items.LEATHER),
                        new ItemStack(Blocks.TNT),
                        new ItemStack(Items.CREEPER_SPAWN_EGG)
                ),
                Collections.singletonList(new ItemStack(EwotArmor.exploding_pants))
        );

        for (Map.Entry<List<ItemStack>, List<ItemStack>> entry : anvilRepairItems.entrySet()) {
            List<ItemStack> repairMaterials = entry.getKey();
            for (ItemStack undamaged : entry.getValue()) {
                ItemStack damaged1 = undamaged.copy();
                ItemStack damaged2 = undamaged.copy();
                ItemStack damaged3 = undamaged.copy();
                damaged1.setDamage(damaged1.getMaxDamage());
                damaged2.setDamage(damaged2.getMaxDamage() * 3 / 4);
                damaged3.setDamage(damaged3.getMaxDamage() * 2 / 4);
                Object repairWithMaterial = registration.getVanillaRecipeFactory().createAnvilRecipe(damaged1, repairMaterials, Collections.singletonList(damaged2));
                Object repairWithSame = registration.getVanillaRecipeFactory().createAnvilRecipe(damaged2, Collections.singletonList(damaged2), Collections.singletonList(damaged3));
                anvilRecipes.add(repairWithMaterial);
                anvilRecipes.add(repairWithSame);
            }
        }

        registration.addRecipes(anvilRecipes, VanillaRecipeCategoryUid.ANVIL);
         */
    }

    private List<ItemStack> listFromTag(Tag<Item> tag) {
        return tag.getAllElements().stream().map(ItemStack::new).collect(Collectors.toList());
    }
}
