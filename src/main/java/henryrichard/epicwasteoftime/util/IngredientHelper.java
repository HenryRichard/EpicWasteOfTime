package henryrichard.epicwasteoftime.util;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;

import java.util.Arrays;

public abstract class IngredientHelper {

    @SafeVarargs
    public static Ingredient fromTags(Tag<Item>... tags) {
        return Ingredient.fromItemListStream(Arrays.stream(tags).map(Ingredient.TagList::new));
    }
}
