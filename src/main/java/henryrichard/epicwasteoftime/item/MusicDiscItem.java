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

    public static final List<MusicDiscItem> NEW_RECORDS = Lists.newArrayList();

    private final Supplier<SoundEvent> music;

    public MusicDiscItem(int comparatorValueIn, Supplier<SoundEvent> soundIn, Properties builder) {
        super(comparatorValueIn, null, builder);
        music = soundIn;
        RECORDS.remove(null); //Passing null up there adds null to the map; we don't want that
        NEW_RECORDS.add(this); //We'll properly add the record to the map during common setup
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent(LoreMaster.getUnlocalizedLore("music_disc", "video_stream_note")).applyTextStyle(TextFormatting.DARK_GRAY).applyTextStyle(TextFormatting.ITALIC));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public SoundEvent getSound() {
        return getSoundEvent();
    }

    public SoundEvent getSoundEvent() {
        return music.get();
    }
}
