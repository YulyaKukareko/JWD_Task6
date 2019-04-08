package by.epam.javawebtraining.kukareko.task6.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Yulya Kukareko
 * @version 1.0 06 Apr 2019
 */
public class ReadConfigProperties {

    public static final Logger LOGGER_CONFIG_PROPERTIES;

    private static Properties prop;
    private static String configFileName = "config.properties";

    static {
        prop = new Properties();
        LOGGER_CONFIG_PROPERTIES = Logger.getLogger(ReadConfigProperties.class);
    }

    public static String getProp(String propName) {
        if(prop.isEmpty()) {
            try (InputStream input = ReadConfigProperties.class.getClassLoader().getResourceAsStream(configFileName)) {
                prop.load(input);
            } catch (IOException ex) {
                LOGGER_CONFIG_PROPERTIES.error(ex.getMessage());
            }
        }
        return prop.getProperty(propName);
    }
}
