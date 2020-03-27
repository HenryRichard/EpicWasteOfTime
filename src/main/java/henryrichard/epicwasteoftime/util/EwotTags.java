package henryrichard.epicwasteoftime.util;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public abstract class EwotTags {

    public static abstract class Items {

        public static final Tag<Item> LAVALEAF_LOGS = tag(EwotMain.MODID, "lavaleaf_logs");

        public static final Tag<Item> GEMS_AMETHYST = tag("gems/amethyst");

        public static final Tag<Item> INGOTS_PINKITE = tag("ingots/pinkite");
        public static final Tag<Item> INGOTS_ENDITE = tag("ingots/endite");

        private static Tag<Item> tag(String namespace, String name)
        {
            return new ItemTags.Wrapper(new ResourceLocation(namespace, name));
        }

        private static Tag<Item> tag(String name)
        {
            return tag("forge", name);
        }
    }

    public static abstract class Blocks {

    }
}
