package ml.pkom.ignischat.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IgnisChat implements ModInitializer {

    public static final String MOD_ID = "ignischat";
    public static final String MOD_NAME = "IgnisChat";
    public static IgnisChat instance;
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        instance = this;

        if (FabricLoader.getInstance().getAllMods()
                .stream()
                .noneMatch(it -> it.getMetadata().getId().startsWith("ignischat-mc"))) {
            throw new IllegalStateException("IgnisChat is not working...");
        }
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }
}
