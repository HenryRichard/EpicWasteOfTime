package henryrichard.epicwasteoftime.item;

import com.google.common.collect.Lists;
import henryrichard.epicwasteoftime.util.LoreMaster;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class MusicDiscItem extends net.minecraft.item.MusicDiscItem {

    @SuppressWarnings("ConstantConditions")
    public MusicDiscItem(int comparatorValueIn, Supplier<SoundEvent> soundIn, Properties builder) {
        super(comparatorValueIn, soundIn, builder);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent(LoreMaster.getUnlocalizedLore("music_disc", "video_stream_note")).mergeStyle(TextFormatting.DARK_GRAY).mergeStyle(TextFormatting.ITALIC));
    }
}
