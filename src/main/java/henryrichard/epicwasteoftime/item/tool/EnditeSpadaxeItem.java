package henryrichard.epicwasteoftime.item.tool;

import henryrichard.epicwasteoftime.item.EwotItemTier;
import henryrichard.epicwasteoftime.util.LoreMaster;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class EnditeSpadaxeItem extends SpadaxeItem implements IEnditeTool  {
    public EnditeSpadaxeItem(IItemTier tier, float damage, Properties builder) {
        super(tier, damage, builder);
    }

    public EnditeSpadaxeItem(IItemTier tier, Properties builder) {
        super(tier, builder);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent(LoreMaster.getUnlocalizedLore("endite_tool")).applyTextStyle(TextFormatting.YELLOW));
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
