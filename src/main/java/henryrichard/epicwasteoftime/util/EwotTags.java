package henryrichard.epicwasteoftime.util;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public abstract class EwotTags {

    public static abstract class Items {

        public static final ITag.INamedTag<Item> LAVALEAF_LOGS = tag(EwotMain.MOD_ID, "lavaleaf_logs");

        public static final ITag.INamedTag<Item> ORE_AMETHYST = tag("ores/amethyst");
        public static final ITag.INamedTag<Item> ORE_ALUMINUM = tag("ores/aluminum");
        public static final ITag.INamedTag<Item> ORE_ENDUST = tag("ores/endust");
        public static final ITag.INamedTag<Item> ORE_PINKITE = tag("ores/pinkite");

        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_AMETHYST = tag("storage_blocks/amethyst");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_ALUMINUM = tag("storage_blocks/aluminum");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_ENDITE = tag("storage_blocks/endite");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_UNALLOYED_ENDITE = tag("storage_blocks/unalloyed_endite");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_ENDUST = tag("storage_blocks/endust");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_PINKITE = tag("storage_blocks/pinkite");
        
        public static final ITag.INamedTag<Item> GEMS_AMETHYST = tag("gems/amethyst");

        public static final ITag.INamedTag<Item> DUST_ENDUST = tag("dusts/endust");

        public static final ITag.INamedTag<Item> INGOTS_ALUMINUM = tag("ingots/aluminum");
        public static final ITag.INamedTag<Item> INGOTS_ENDITE = tag("ingots/endite");
        public static final ITag.INamedTag<Item> INGOTS_UNALLOYED_ENDITE = tag("ingots/unalloyed_endite");
        public static final ITag.INamedTag<Item> INGOTS_PINKITE = tag("ingots/pinkite");

        public static final ITag.INamedTag<Item> NUGGETS_AMETHYST = tag("nuggets/amethyst");
        public static final ITag.INamedTag<Item> NUGGETS_ALUMINUM = tag("nuggets/aluminum");
        public static final ITag.INamedTag<Item> NUGGETS_ENDITE = tag("nuggets/endite");
        public static final ITag.INamedTag<Item> NUGGETS_PINKITE = tag("nuggets/pinkite");

        private static ITag.INamedTag<Item> tag(String namespace, String name)
        {
            return ItemTags.makeWrapperTag(namespace + ':' + name);
        }

        private static ITag.INamedTag<Item> tag(String name)
        {
            return tag("forge", name);
        }
    }

    public static abstract class Blocks {

    }
}
