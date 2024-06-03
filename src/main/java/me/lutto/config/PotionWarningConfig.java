package me.lutto.config;

import com.google.gson.*;
import eu.midnightdust.lib.config.MidnightConfig;
import me.lutto.PotionWarning;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.Registries;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class PotionWarningConfig extends MidnightConfig {

    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("potion-warning.json");

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

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

    public static void load() {
        if (!Files.exists(PATH)) {
            save();
        }

        try {
            BufferedReader bufferedReader = Files.newBufferedReader(PATH);
            JsonObject json = new JsonParser().parse(bufferedReader).getAsJsonObject();

            enabled = json.get("enabled").getAsBoolean();
            warnTime = json.get("warnTime").getAsInt();

            text = json.get("text").getAsString();
            textColor = json.get("textColor").getAsString();
            textScale = json.get("textScale").getAsInt();
            textPosX = json.get("textPosX").getAsInt();
            textPosY = json.get("textPosY").getAsInt();
            centeredText = json.get("centeredText").getAsBoolean();
            textShadow = json.get("textShadow").getAsBoolean();

            if (json.get("status-effects") == null) return;
            Map<String, JsonElement> statusEffectsMap = json.get("status-effects").getAsJsonObject().asMap();
            for (String id : statusEffectsMap.keySet()) {
                System.out.println(Identifier.tryParse(id));
                System.out.println(statusEffectsMap.get(id).getAsBoolean());
                statusEffects.put(Identifier.tryParse(id), statusEffectsMap.get(id).getAsBoolean());
            }
            System.out.println(statusEffects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save() {
        JsonObject jsonConfig = new JsonObject();

        jsonConfig.addProperty("enabled", enabled);
        jsonConfig.addProperty("warnTime", warnTime);

        jsonConfig.addProperty("text", text);
        jsonConfig.addProperty("textColor", textColor);
        jsonConfig.addProperty("textScale", textScale);
        jsonConfig.addProperty("textPosX", textPosX);
        jsonConfig.addProperty("textPosY", textPosY);
        jsonConfig.addProperty("centeredText", centeredText);
        jsonConfig.addProperty("textShadow", textShadow);

        JsonObject statusEffectsJsonArray = new JsonObject();
        for (Map.Entry<Identifier, Boolean> entry : statusEffects.entrySet()) {
            statusEffectsJsonArray.addProperty(entry.getKey().toString(), entry.getValue());
        }
        jsonConfig.add("status-effects", statusEffectsJsonArray);

        try (BufferedWriter fileWriter = Files.newBufferedWriter(PATH)) {
            fileWriter.write(GSON.toJson(jsonConfig));
        } catch (IOException e) {
            PotionWarning.getLogger().error("Couldn't save Potion Warning configuration file");
            e.printStackTrace();
        }
    }

}
