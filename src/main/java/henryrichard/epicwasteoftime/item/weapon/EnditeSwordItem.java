package henryrichard.epicwasteoftime.item.weapon;

import henryrichard.epicwasteoftime.item.tool.IEnditeTool;
import henryrichard.epicwasteoftime.util.LoreMaster;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class EnditeSwordItem extends SwordItem implements IEnditeTool {
    public EnditeSwordItem(IItemTier tier, int speed, float attackDamage, Properties builder) {
        super(tier, speed, attackDamage, builder);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent(LoreMaster.getUnlocalizedLore("endite_weapon")).mergeStyle(TextFormatting.YELLOW));
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }


}
