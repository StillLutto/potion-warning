package me.lutto.mixin;

import me.lutto.PotionWarning;
import me.lutto.client.PotionWarningClient;
import me.lutto.config.PotionWarningConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstanceMixin {

    @Shadow
    private int duration;

    @Final
    @Shadow
    private RegistryEntry<StatusEffect> type;

    @Inject(at = @At("TAIL"), method = "update")
    private void injectUpdate(LivingEntity entity, Runnable overwriteCallback, CallbackInfoReturnable<Boolean> cir) {
        if (duration != PotionWarningConfig.warnTime) return;

        if (!(entity instanceof PlayerEntity)) return;
        PlayerEntity thisEntity = (PlayerEntity) entity;
        PlayerEntity localPlayer = MinecraftClient.getInstance().player;
        if (!thisEntity.equals(localPlayer)) return;

        if (!PotionWarningConfig.statusEffects.get(Registries.STATUS_EFFECT.getId(type.value()))) return;

        PotionWarningClient.getStatusEffectHudManager().triggerHudDisplay(type.value());
    }

}
