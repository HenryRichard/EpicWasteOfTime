package henryrichard.epicwasteoftime.init;

import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class EwotSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EwotMain.MOD_ID);

    public static final RegistryObject<SoundEvent> PYRIVATHITE_BLOCK_BREAK = register("block.pyrivathite_block.break");
    public static final RegistryObject<SoundEvent> PYRIVATHITE_BLOCK_FALL = register("block.pyrivathite_block.fall");
    public static final RegistryObject<SoundEvent> PYRIVATHITE_BLOCK_HIT = register("block.pyrivathite_block.hit");
    public static final RegistryObject<SoundEvent> PYRIVATHITE_BLOCK_PLACE = register("block.pyrivathite_block.place");
    public static final RegistryObject<SoundEvent> PYRIVATHITE_BLOCK_STEP = register("block.pyrivathite_block.step");

    public static final RegistryObject<SoundEvent> MUSIC_DISC_STRAD_REMIX = register("music_disc.strad_remix");

    public abstract class Types {
        public static final SoundType PYRIVATHITE = new ForgeSoundType(1.0F, 1.0F, PYRIVATHITE_BLOCK_BREAK, PYRIVATHITE_BLOCK_STEP, PYRIVATHITE_BLOCK_PLACE, PYRIVATHITE_BLOCK_HIT, PYRIVATHITE_BLOCK_FALL);
    }

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(EwotMain.MOD_ID, name)));
    }


}
