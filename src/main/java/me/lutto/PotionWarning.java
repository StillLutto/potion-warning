package me.lutto;

import me.lutto.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PotionWarning implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("PotionWarning");

    private static final ConfigManager configManager = new ConfigManager();

    @Override
    public void onInitialize() {
        configManager.loadConfig();
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

}
