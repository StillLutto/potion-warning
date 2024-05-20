package me.lutto;

import net.minecraft.registry.Registries;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.server.MinecraftServer;

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

    public void triggerHudDisplay(MinecraftServer server,  StatusEffect effect) {
        showHudStatusEffect = effect;
        ((SchedulerAccessor) server).runTaskLater(40L, () -> showHudStatusEffect = null);
    }

    public StatusEffect getShowHudStatusEffect() {
        return showHudStatusEffect;
    }

}
