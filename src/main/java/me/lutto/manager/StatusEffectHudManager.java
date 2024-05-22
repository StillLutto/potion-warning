package me.lutto.manager;

import me.lutto.client.PotionWarningClient;
import net.minecraft.entity.effect.StatusEffect;

public class StatusEffectHudManager {

    private StatusEffect showHudStatusEffect;

    public void triggerHudDisplay(StatusEffect effect) {
        showHudStatusEffect = effect;
        PotionWarningClient.getScheduler().schedule(100, () -> showHudStatusEffect = null);
    }

    public StatusEffect getShowHudStatusEffect() {
        return showHudStatusEffect;
    }

}
