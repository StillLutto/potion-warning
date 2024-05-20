package me.lutto.manager;

import me.lutto.client.PotionWarningClient;
import net.minecraft.registry.Registries;
import net.minecraft.entity.effect.StatusEffect;

import java.util.HashMap;

public class StatusEffectHudManager {

    private HashMap<StatusEffect, Boolean> warnStatusEffects = new HashMap<>();
    private StatusEffect showHudStatusEffect;

    public StatusEffectHudManager() {
        for (StatusEffect effect : Registries.STATUS_EFFECT) {
            warnStatusEffects.put(effect, true);
        }
    }

    public void addStatusEffect(StatusEffect effect) {
        if (warnStatusEffects.containsKey(effect)) {
            warnStatusEffects.replace(effect, true);
        } else {
            warnStatusEffects.put(effect, true);
        }
    }

    public void triggerHudDisplay(StatusEffect effect) {
        showHudStatusEffect = effect;
        PotionWarningClient.getScheduler().schedule(100, () -> showHudStatusEffect = null);
    }

    public StatusEffect getShowHudStatusEffect() {
        return showHudStatusEffect;
    }

}
