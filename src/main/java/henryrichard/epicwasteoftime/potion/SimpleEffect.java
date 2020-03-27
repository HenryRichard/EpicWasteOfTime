package henryrichard.epicwasteoftime.potion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import javax.annotation.Nullable;

//An effect that does nothing
public class SimpleEffect extends Effect {

    public SimpleEffect(String name, EffectType type, int liquidColor) {
        super(type, liquidColor);
        this.setRegistryName(name);
    }


    @Override
    public void performEffect(LivingEntity entity, int amplifier) {

    };

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) {

    }

    /**
     * checks if Potion effect is ready to be applied this tick.
     */
    @Override
    public boolean isReady(int duration, int amplifier) {
        return false;
    };
}
