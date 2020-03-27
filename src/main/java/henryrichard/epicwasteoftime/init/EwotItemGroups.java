package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class EwotItemGroups extends ItemGroup {

    private EwotItemGroups(String label) {
        super(EwotMain.MODID + "." +label);
    }

    public static final ItemGroup EWOT_BLOCKS = new EwotItemGroups("blocks") {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack createIcon() {
            return new ItemStack(EwotBlocks.pinkite_block);
        }
    };

    public static final ItemGroup EWOT_ITEMS = new EwotItemGroups("items") {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack createIcon() {
            return new ItemStack(EwotItems.amethyst);
        }
    };

    public static final ItemGroup EWOT_TOOLS = new EwotItemGroups("tools") {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack createIcon() {
            return new ItemStack(EwotTools.endite_spadaxe);
        }
    };
    public static final ItemGroup EWOT_ARMOR = new EwotItemGroups("armor") {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack createIcon() {
            return new ItemStack(EwotArmor.exploding_pants);
        }
    };;


}
