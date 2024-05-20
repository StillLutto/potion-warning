package me.lutto;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PotionWarning implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("PotionWarning");

    @Override
    public void onInitialize() {
        // On Mod initialize
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}
