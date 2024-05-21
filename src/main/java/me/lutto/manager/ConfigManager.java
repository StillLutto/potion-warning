package me.lutto.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.lutto.PotionWarning;
import me.lutto.instance.Config;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class ConfigManager {
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "potion-warning-config.json");
    private Config config;

    public void loadConfig() {
        try (Reader reader = new FileReader(CONFIG_FILE)) {
            config = GSON.fromJson(reader, Config.class);
        } catch (IOException e) {
            config = new Config();
        }
    }

    public void saveConfig() {
        PotionWarning.getLogger().debug("Saving config...");
        try (Writer writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(config, writer);
        } catch (IOException e) {
            PotionWarning.getLogger().error("Could not save config to file '" + CONFIG_FILE.getAbsolutePath() + "'", e);
        }
    }

    public Config getConfig() {
        return config;
    }
}

