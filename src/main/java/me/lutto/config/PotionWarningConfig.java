package me.lutto.config;

import me.lutto.annotations.StatusEffects;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.*;

@Config(name = "potion-warning")
public class PotionWarningConfig implements ConfigData {

    // General options
    @ConfigEntry.Category("general")
    public boolean enabled = true;

    // Text options
    @ConfigEntry.Category("text")
    public String text = "$effect has expired!";

    @ConfigEntry.Category("text")
    @ConfigEntry.ColorPicker
    public int textColor = TextColor.fromFormatting(Formatting.RED).getRgb();

    @ConfigEntry.Category("text")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int textPosX = 2;

    @ConfigEntry.Category("text")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int textPosY = 2;

    @ConfigEntry.Category("text")
    public boolean centeredText = false;

    @ConfigEntry.Category("text")
    public boolean textShadow = true;

    // Status Effects
    @ConfigEntry.Category("status-effects")
    @StatusEffects
    public Set<Identifier> disabledStatusEffects = new HashSet<>();
}
