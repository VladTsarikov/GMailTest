package framework.utils;

import framework.enums.LogType;
import java.io.*;
import java.util.Properties;

public class PropertyReader {

    private Properties properties = new Properties();

    public PropertyReader(final String resourceName) {
        properties = readFromResource(properties, resourceName);
    }

    private Properties readFromResource(final Properties objProperties, final String resourceName) {
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
        if (inStream != null) {
            try {
                objProperties.load(inStream);
                inStream.close();
            } catch (IOException e) {
                Logger.log(LogType.DEBUG,e);
            }
        } else {
            Logger.log(LogType.ERROR,String.format("Resource \"%1$s\" could not be found", resourceName));
        }
        return objProperties;
    }

    public String getProperty(final String key) {
        return properties.getProperty(key);
    }
}