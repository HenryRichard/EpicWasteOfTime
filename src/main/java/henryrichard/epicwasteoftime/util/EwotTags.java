package henryrichard.epicwasteoftime.util;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public abstract class EwotTags {

    public static abstract class Items {

        public static final Tag<Item> LAVALEAF_LOGS = tag(EwotMain.MODID, "lavaleaf_logs");

        public static final Tag<Item> ORE_AMETHYST = tag("ore/amethyst");
        public static final Tag<Item> ORE_ALUMINUM = tag("ore/aluminum");
        public static final Tag<Item> ORE_ENDUST = tag("ore/endust");
        public static final Tag<Item> ORE_PINKITE = tag("ore/pinkite");

        public static final Tag<Item> STORAGE_BLOCKS_AMETHYST = tag("storage_blocks/amethyst");
        public static final Tag<Item> STORAGE_BLOCKS_ALUMINUM = tag("storage_blocks/aluminum");
        public static final Tag<Item> STORAGE_BLOCKS_ENDITE = tag("storage_blocks/endite");
        public static final Tag<Item> STORAGE_BLOCKS_UNALLOYED_ENDITE = tag("storage_blocks/unalloyed_endite");
        public static final Tag<Item> STORAGE_BLOCKS_ENDUST = tag("storage_blocks/endust");
        public static final Tag<Item> STORAGE_BLOCKS_PINKITE = tag("storage_blocks/pinkite");
        
        public static final Tag<Item> GEMS_AMETHYST = tag("gems/amethyst");

        public static final Tag<Item> DUST_ENDUST = tag("dusts/endust");

        public static final Tag<Item> INGOTS_ALUMINUM = tag("ingots/aluminum");
        public static final Tag<Item> INGOTS_ENDITE = tag("ingots/endite");
        public static final Tag<Item> INGOTS_UNALLOYED_ENDITE = tag("ingots/unalloyed_endite");
        public static final Tag<Item> INGOTS_PINKITE = tag("ingots/pinkite");

        public static final Tag<Item> NUGGETS_AMETHYST = tag("nuggets/amethyst");
        public static final Tag<Item> NUGGETS_ALUMINUM = tag("nuggets/aluminum");
        public static final Tag<Item> NUGGETS_ENDITE = tag("nuggets/endite");
        public static final Tag<Item> NUGGETS_PINKITE = tag("nuggets/pinkite");

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
