package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EwotMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class EwotLoot {

    @SubscribeEvent
    public static void injectLoot(LootTableLoadEvent event) {
        String prefix = "minecraft:";
        String name = event.getName().toString();

        if(name.startsWith(prefix)) {
            String file = name.substring(name.indexOf(prefix) + prefix.length());
            switch (file) {
                case "chests/nether_bridge":
                    event.getTable().addPool(getInjectPool(file));
                default:
                    break;
            }
        }

    }

    private static LootPool getInjectPool(String entryName) {
        return LootPool.builder()
                .addEntry(getInjectEntry(entryName, 1))
                .bonusRolls(0, 1)
                .build();
    }

    private static LootEntry.Builder getInjectEntry(String name, int weight) {
        ResourceLocation table = new ResourceLocation(EwotMain.MOD_ID, "inject/" + name);
        return TableLootEntry.builder(table)
                .weight(weight);
    }
}
