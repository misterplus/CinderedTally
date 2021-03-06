package plus.misterplus.cinderedtally.common.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.world.World;
import plus.misterplus.cinderedtally.helper.LifespanHelper;
import plus.misterplus.cinderedtally.registry.CinderedTallyRegistry;

/**
 * An effect that slows players' time.<br>
 * For each level of this effect, the affected player's time goes 20% slower.<br>
 * When a player reaches Time Dilation V, that player enters stasis.
 *
 * @see plus.misterplus.cinderedtally.common.effect.EffectStasis
 */
public class EffectTimeDilation extends Effect {
    public EffectTimeDilation() {
        super(EffectType.NEUTRAL, 16777088);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        World world = livingEntity.getCommandSenderWorld();
        if (!world.isClientSide() && livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;
            LifespanHelper.extendLifespan(player, 1);
            if (amplifier > 3)
                player.addEffect(new EffectInstance(CinderedTallyRegistry.STASIS, player.getEffect(CinderedTallyRegistry.TIME_DILATION).getDuration(), 0));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 < amplifier * 4 + 4;
    }
}
