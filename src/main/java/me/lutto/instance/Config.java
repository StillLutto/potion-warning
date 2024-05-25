package me.lutto.instance;

import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public class Config {
    // General options
    public boolean enabled = true;

    // Text options
    public String text = "$effect has expired!";
    public TextColor textColor = TextColor.fromFormatting(Formatting.RED);
    public boolean centeredText = false;
    public int textPosX = 2;
    public int textPosY = 2;

    // Effect options
    public boolean SPEED = true;
    public boolean SLOWNESS = true;
    public boolean HASTE = true;
    public boolean MINING_FATIGUE = true;
    public boolean STRENGTH = true;
    public boolean INSTANT_HEALTH = true;
    public boolean INSTANT_DAMAGE = true;
    public boolean JUMP_BOOST = true;
    public boolean NAUSEA = true;
    public boolean REGENERATION = true;
    public boolean RESISTANCE = true;
    public boolean FIRE_RESISTANCE = true;
    public boolean WATER_BREATHING = true;
    public boolean INVISIBILITY = true;
    public boolean BLINDNESS = true;
    public boolean NIGHT_VISION = true;
    public boolean HUNGER = true;
    public boolean WEAKNESS = true;
    public boolean POISON = true;
    public boolean WITHER = true;
    public boolean HEALTH_BOOST = true;
    public boolean ABSORPTION = true;
    public boolean SATURATION = true;
    public boolean GLOWING = true;
    public boolean LEVITATION = true;
    public boolean LUCK = true;
    public boolean SLOW_FALLING = true;
    public boolean CONDUIT_POWER = true;
    public boolean DOLPHINS_GRACE = true;
    public boolean BAD_OMEN = true;
    public boolean HERO_OF_THE_VILLAGE = true;
    public boolean DARKNESS = true;
}
