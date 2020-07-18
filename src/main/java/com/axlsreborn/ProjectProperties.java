package com.axlsreborn;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    public static final String KEY_STEAM_WEB_API_KEY = "steam.web.api_key";
    public static final String ID_STEAM_APP          = "steam.app_id";
    public static final String CSV_DELIMITER         = "csv.delimiter";
    public static final String CSV_FILE_PATH         = "csv.file.path";
    public static final String DEBUG_FLAG            = "debug.flag";


    public static ProjectPropertiesArgs getProperties(String[] args) {
        String apiKey = null;
        int appId = 0;
        String csvDelimiterStr;
        char csvDelimiter = ' ';
        String csvFilePath = null;
        String debugFlag = null;

        String propertiesPath = args[0];
        System.out.println("Main.main(): propertiesPath = [" + propertiesPath + "]");
        try {
            FileReader fileReader = new FileReader(propertiesPath);
            Properties properties = new Properties();
            properties.load(fileReader);

            apiKey = properties.getProperty(KEY_STEAM_WEB_API_KEY);
            String appIdStr = properties.getProperty(ID_STEAM_APP);
            appId = Integer.parseInt(appIdStr);
            csvDelimiterStr = properties.getProperty(CSV_DELIMITER);
            csvFilePath = properties.getProperty(CSV_FILE_PATH);
            debugFlag = properties.getProperty(DEBUG_FLAG).toUpperCase();

            System.out.println("Main.main(): apiKey = [" + apiKey + "]");
            System.out.println("Main.main(): appId = [" + appId + "]");
            System.out.println("Main.main(): csvDelimiter = [" + csvDelimiterStr + "]");
            System.out.println("Main.main(): csvFilePath = [" + csvFilePath + "]");
            System.out.println("Main.main(): debugFlag = [" + debugFlag + "]");

            if (!debugFlag.equals("TRUE") && !debugFlag.equals("FALSE")) {
                System.err.println("Please set debug flag to true or false");
                System.err.printf("debugFlag = [%s]\n", DEBUG_FLAG);
            }

            if (debugFlag.equals("TRUE")) {
                if (apiKey == null || apiKey.equals("")) {
                    System.err.println("API Key is not defined in properties file");
                    System.err.printf("Expected key: [%s]\n", KEY_STEAM_WEB_API_KEY);
                }
                if (csvDelimiterStr == null || csvDelimiterStr.length() != 1) {
                    System.err.println("Please add a single character delimiter to the properties file");
                    System.err.printf("csvDelimiter = [%s]\n", CSV_DELIMITER);
                } else {
                    csvDelimiter = csvDelimiterStr.charAt(0);
                }
                if (csvFilePath == null || csvFilePath.isEmpty()) {
                    System.err.println("Please add a file path to save the Steam Achievements");
                    System.err.printf("csvFilePath = [%s]\n", CSV_FILE_PATH);
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading properties file");
            System.err.println(e.getLocalizedMessage());
        }
        return new ProjectPropertiesArgs(apiKey, appId, csvDelimiter, csvFilePath, debugFlag);
    }
}