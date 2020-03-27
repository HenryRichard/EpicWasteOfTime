package henryrichard.epicwasteoftime.util;

import henryrichard.epicwasteoftime.EwotMain;

import java.util.ArrayList;
import java.util.List;

public abstract class LoreMaster {
    private static final String prefix = "item." + EwotMain.MODID + ".";
    private static final String suffix = ".desc";

    public static String getUnlocalizedLore(String name) {
        return prefix + name + suffix;
    }

    public static String getUnlocalizedLore(String name, int index) {
        return getUnlocalizedLore(name) + '.' + index;
    }

    public static List<String> getUnlocalizedLoreList(String name, int amount) {
        List<String> list = new ArrayList<>(amount);

        for(int i = 1; i <= amount; i++) {
            list.set(i, getUnlocalizedLore(name, i));
        }

        return list;
    }
}
