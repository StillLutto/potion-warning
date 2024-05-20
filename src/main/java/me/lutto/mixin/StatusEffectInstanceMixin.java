package me.lutto.mixin;

import me.lutto.client.PotionWarningClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstanceMixin {

    @Shadow
    private int duration;

    @Shadow
    private StatusEffect type;

    @Inject(at = @At("TAIL"), method = "update")
    private void injectUpdate(LivingEntity entity, Runnable overwriteCallback, CallbackInfoReturnable<Boolean> cir) {
        if (duration != 0) return;

        PotionWarningClient.getStatusEffectHudManager().triggerHudDisplay(type);
    }

}
