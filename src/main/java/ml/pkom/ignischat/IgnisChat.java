package ml.pkom.ignischat;

import net.fabricmc.api.ModInitializer;
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
	}

    public static void log(Level level, String message){
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }
}
