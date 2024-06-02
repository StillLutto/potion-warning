package me.lutto.config;

import eu.midnightdust.lib.config.MidnightConfig;
import me.lutto.PotionWarning;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PotionWarningConfig extends MidnightConfig {

    // General options
    public static boolean enabled = true;
    public static int warnTime = 0;

    // Text options
    public static String text = "$effect has expired!";
    public static String textColor = TextColor.fromFormatting(Formatting.RED).getHexCode();
    public static double textScale = 100;
    public static double textPosX = 2;
    public static double textPosY = 2;
    public static boolean centeredText = false;
    public static boolean textShadow = true;

    // Status effects
    public static Map<Identifier, Boolean> statusEffects = new HashMap<>();

    public static void save() {
        Properties properties = new Properties();
        writeTo(properties);
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("potion-warning.properties");
        if(!Files.exists(configPath)) {
            try {
                Files.createFile(configPath);
            } catch (IOException e) {
                PotionWarning.getLogger().error("Failed to create configuration file!");
                e.printStackTrace();
                return;
            }
        }
        try {
            properties.store(Files.newOutputStream(configPath), "Configuration file for Enhanced Block Entities");
        } catch (IOException e) {
            PotionWarning.getLogger().error("Failed to write to configuration file!");
            e.printStackTrace();
        }
    }

    public static void writeTo(Properties properties) {
        properties.setProperty("enabled", Boolean.toString(enabled));
        properties.setProperty("warnTime", Integer.toString(warnTime));

        properties.setProperty("text", text);
        properties.setProperty("textColor", textColor);
        properties.setProperty("textScale", Double.toString(textScale));
        properties.setProperty("textPosX", Double.toString(textPosX));
        properties.setProperty("textPosY", Double.toString(textPosY));
        properties.setProperty("textPosY", Boolean.toString(centeredText));
        properties.setProperty("textPosY", Boolean.toString(textShadow));

        for (Identifier id : statusEffects.keySet()) {
            properties.setProperty(id.getPath(), Boolean.toString(textShadow));
        }
    }

}
