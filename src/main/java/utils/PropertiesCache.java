package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

/**
 * Created by Igor Odokienko.
 */
public class PropertiesCache {

    protected final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final Properties configProp = new Properties();
    private static final PropertiesCache INSTANCE = new PropertiesCache();

    private PropertiesCache() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("test.properties");
        LOG.info("Read all properties from file: SUCCESS");
        try {
            configProp.load(in);
        } catch (IOException e) {
            LOG.info("Can't read all properties from file. Exception: " + e);
        }
    }

    public static String getProperty(String key) {
        return INSTANCE.configProp.getProperty(key);
    }
}
