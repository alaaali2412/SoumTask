package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Helper {
    public String getValuesFromPropertiesFile(String propertiesFileName, String value) {
        Properties properties = new Properties();
        FileInputStream file;
        try {
            file = new FileInputStream(System.getProperty("user.dir") + "/PropertiesFiles/" + propertiesFileName + ".properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not read properties from file: [" + propertiesFileName + ".properties" + "].", e);
        }
        return properties.getProperty(value);
    }

    public String updateValueInPropertiesFile(String propertiesFileName, String key, String value) {
        Properties properties = new Properties();
        FileInputStream fileIn;
        FileOutputStream fileOut;
        try {
            fileIn = new FileInputStream(System.getProperty("user.dir") + "/PropertiesFiles/" + propertiesFileName + ".properties");
            properties.load(fileIn);
            fileOut = new FileOutputStream(System.getProperty("user.dir") + "/PropertiesFiles/" + propertiesFileName);
            properties.setProperty(key, value);
            properties.store(fileOut, "updated successfully");
        } catch (IOException e) {

            System.out.println("Exception while taking screenshot" + e.getMessage());
            throw new RuntimeException("Could not read properties from file: [" + propertiesFileName + ".properties" + "].", e);
        }
        return properties.getProperty(value);
    }

}
