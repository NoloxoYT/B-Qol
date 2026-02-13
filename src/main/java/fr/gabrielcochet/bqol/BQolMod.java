package fr.gabrielcochet.bqol;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BQolMod implements ModInitializer {
    public static final String MOD_ID = "bqol";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing B-Qol mod!");
    }
}