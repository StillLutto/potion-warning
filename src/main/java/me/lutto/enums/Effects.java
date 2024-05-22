package me.lutto.enums;

import me.lutto.PotionWarning;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;

public enum Effects {

    SPEED(StatusEffects.SPEED, PotionWarning.getConfigManager().getConfig().SPEED),
    SLOWNESS(StatusEffects.SLOWNESS, PotionWarning.getConfigManager().getConfig().SLOWNESS),
    HASTE(StatusEffects.HASTE, PotionWarning.getConfigManager().getConfig().HASTE),
    MINING_FATIGUE(StatusEffects.MINING_FATIGUE, PotionWarning.getConfigManager().getConfig().MINING_FATIGUE),
    STRENGTH(StatusEffects.MINING_FATIGUE, PotionWarning.getConfigManager().getConfig().STRENGTH),
    INSTANT_HEALTH(StatusEffects.INSTANT_HEALTH, PotionWarning.getConfigManager().getConfig().INSTANT_HEALTH),
    INSTANT_DAMAGE(StatusEffects.INSTANT_DAMAGE, PotionWarning.getConfigManager().getConfig().INSTANT_DAMAGE),
    JUMP_BOOST(StatusEffects.JUMP_BOOST, PotionWarning.getConfigManager().getConfig().JUMP_BOOST),
    NAUSEA(StatusEffects.NAUSEA, PotionWarning.getConfigManager().getConfig().NAUSEA),
    REGENERATION(StatusEffects.REGENERATION, PotionWarning.getConfigManager().getConfig().REGENERATION),
    RESISTANCE(StatusEffects.RESISTANCE, PotionWarning.getConfigManager().getConfig().RESISTANCE),
    FIRE_RESISTANCE(StatusEffects.FIRE_RESISTANCE, PotionWarning.getConfigManager().getConfig().FIRE_RESISTANCE),
    WATER_BREATHING(StatusEffects.WATER_BREATHING, PotionWarning.getConfigManager().getConfig().WATER_BREATHING),
    INVISIBILITY(StatusEffects.INVISIBILITY, PotionWarning.getConfigManager().getConfig().INVISIBILITY),
    BLINDNESS(StatusEffects.BLINDNESS, PotionWarning.getConfigManager().getConfig().BLINDNESS),
    NIGHT_VISION(StatusEffects.NIGHT_VISION, PotionWarning.getConfigManager().getConfig().NIGHT_VISION),
    HUNGER(StatusEffects.HUNGER, PotionWarning.getConfigManager().getConfig().HUNGER),
    WEAKNESS(StatusEffects.WEAKNESS, PotionWarning.getConfigManager().getConfig().WEAKNESS),
    POISON(StatusEffects.POISON, PotionWarning.getConfigManager().getConfig().POISON),
    WITHER(StatusEffects.WITHER, PotionWarning.getConfigManager().getConfig().WITHER),
    HEALTH_BOOST(StatusEffects.HEALTH_BOOST, PotionWarning.getConfigManager().getConfig().HEALTH_BOOST),
    ABSORPTION(StatusEffects.ABSORPTION, PotionWarning.getConfigManager().getConfig().ABSORPTION),
    SATURATION(StatusEffects.SATURATION, PotionWarning.getConfigManager().getConfig().SATURATION),
    GLOWING(StatusEffects.GLOWING, PotionWarning.getConfigManager().getConfig().GLOWING),
    LEVITATION(StatusEffects.LEVITATION, PotionWarning.getConfigManager().getConfig().LEVITATION),
    LUCK(StatusEffects.LUCK, PotionWarning.getConfigManager().getConfig().LUCK),
    SLOW_FALLING(StatusEffects.SLOW_FALLING, PotionWarning.getConfigManager().getConfig().SLOW_FALLING),
    CONDUIT_POWER(StatusEffects.CONDUIT_POWER, PotionWarning.getConfigManager().getConfig().CONDUIT_POWER),
    DOLPHINS_GRACE(StatusEffects.DOLPHINS_GRACE, PotionWarning.getConfigManager().getConfig().DOLPHINS_GRACE),
    BAD_OMEN(StatusEffects.BAD_OMEN, PotionWarning.getConfigManager().getConfig().BAD_OMEN),
    HERO_OF_THE_VILLAGE(StatusEffects.HERO_OF_THE_VILLAGE, PotionWarning.getConfigManager().getConfig().HERO_OF_THE_VILLAGE),
    DARKNESS(StatusEffects.DARKNESS, PotionWarning.getConfigManager().getConfig().DARKNESS);

    private StatusEffect statusEffect;
    private boolean activated;

    Effects(StatusEffect statusEffect, boolean activated) {
        this.statusEffect = statusEffect;
        this.activated = activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public StatusEffect getStatusEffect() { return statusEffect; }
    public boolean isActivated() { return activated; }

}
