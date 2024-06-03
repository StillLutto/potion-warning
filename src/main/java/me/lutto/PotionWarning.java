package me.lutto;

import me.lutto.config.PotionWarningConfig;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PotionWarning implements ModInitializer {

    public static final String MOD_ID = "potionwarning";
    public static final Logger LOGGER = LogManager.getLogger("potionwarning");

    @Override
    public void onInitialize() {
        PotionWarningConfig.load();
    }

}
