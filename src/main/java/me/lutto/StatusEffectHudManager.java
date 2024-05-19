package me.lutto;

import net.minecraft.registry.Registries;
import net.minecraft.entity.effect.StatusEffect;

import java.util.*;

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
        TimerTask task = new TimerTask() {
            public void run() {
                showHudStatusEffect = null;
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 5000L;
        timer.schedule(task, delay);
    }

    public StatusEffect getShowHudStatusEffect() {
        return showHudStatusEffect;
    }

}
