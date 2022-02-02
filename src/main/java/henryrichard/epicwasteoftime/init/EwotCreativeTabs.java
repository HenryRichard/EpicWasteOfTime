package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class EwotCreativeTabs extends CreativeModeTab {

    private EwotCreativeTabs(String label) {
        super(EwotMain.MOD_ID + "." +label);
    }

    public static final CreativeModeTab EWOT_BLOCKS = new EwotCreativeTabs("blocks") {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(EwotBlocks.PYRIVATHITE_ORE.get());
        }
    };

    public static final CreativeModeTab EWOT_ITEMS = new EwotCreativeTabs("items") {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(EwotItems.PYRIVATHITE.get());
        }
    };
    /*
    public static final CreativeModeTab EWOT_TOOLS = new EwotCreativeTabs("tools") {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(EwotTools.endite_spadaxe);
        }
    };
    public static final CreativeModeTab EWOT_ARMOR = new EwotCreativeTabs("armor") {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(EwotArmor.exploding_pants);
        }
    };;
    */

}
