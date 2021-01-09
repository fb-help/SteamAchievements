package com.axlsreborn.tf2achievementsspring;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    public static final String KEY_STEAM_WEB_API_KEY = "steam.web_api_key";
    public static final String KEY_STEAM_APP_ID      = "steam.app_id";
    public static final String KEY_CSV_DELIMITER     = "csv.delimiter";
    public static final String KEY_CSV_FILE_PATH     = "csv.file_path";
    public static final String KEY_DEBUG_FLAG        = "debug.flag";

    private final String  apiKey;
    private final int     appId;
    private final char    csvDelimiter;
    private final String  csvFilePath;
    private final boolean debugFlag;

    public ProjectProperties(String propertiesPath) throws ProjectPropertiesException {
        String apiKey;
        int appId;
        char csvDelimiter = ' ';
        String csvFilePath;
        boolean debugFlag = false;

        System.out.println("Main.main(): propertiesPath = [" + propertiesPath + "]");
        FileReader fileReader;
        try {
            fileReader = new FileReader(propertiesPath);
        } catch (FileNotFoundException e) {
            throw new ProjectPropertiesException(e.getLocalizedMessage());
        }

        Properties properties = new Properties();
        try {
            properties.load(fileReader);
        } catch (IOException e) {
            throw new ProjectPropertiesException(e.getLocalizedMessage());
        }

        try {
            String steamAppIdStr = properties.getProperty(KEY_STEAM_APP_ID);
            appId = Integer.parseInt(steamAppIdStr);
        } catch (NumberFormatException e) {
            throw new ProjectPropertiesException(e.getLocalizedMessage());
        }

        apiKey = properties.getProperty(KEY_STEAM_WEB_API_KEY);
        String csvDelimiterStr = properties.getProperty(KEY_CSV_DELIMITER);
        csvFilePath = properties.getProperty(KEY_CSV_FILE_PATH);
        String debugFlagStr = properties.getProperty(KEY_DEBUG_FLAG);

        if (debugFlagStr != null && debugFlagStr.toUpperCase().equals("TRUE")) {
            debugFlag = true;
        }

        if (debugFlag) {
            System.out.println("Main.main(): apiKey = [" + apiKey + "]");
            System.out.println("Main.main(): appId = [" + appId + "]");
            System.out.println("Main.main(): csvDelimiter = [" + csvDelimiterStr + "]");
            System.out.println("Main.main(): csvFilePath = [" + csvFilePath + "]");
            System.out.println("Main.main(): debugFlag = [" + debugFlag + "]");
        }

        if (apiKey == null || apiKey.isEmpty()) {
            System.err.printf("Expected key: [%s]\n", KEY_STEAM_WEB_API_KEY);
            throw new ProjectPropertiesException("API Key is not defined in properties file");
        }
        if (csvDelimiterStr == null || csvDelimiterStr.length() != 1) {
            System.err.printf("csvDelimiter = [%s]\n", KEY_CSV_DELIMITER);
            throw new ProjectPropertiesException("CSV delimiter is not defined in properties file");
        } else {
            csvDelimiter = csvDelimiterStr.charAt(0);
        }
        if (csvFilePath == null || csvFilePath.isEmpty()) {
            System.err.printf("csvFilePath = [%s]\n", KEY_CSV_FILE_PATH);
            throw new ProjectPropertiesException("CSV file path is not defined in properties file");
        }

        this.apiKey = apiKey;
        this.appId = appId;
        this.csvDelimiter = csvDelimiter;
        this.csvFilePath = csvFilePath;
        this.debugFlag = debugFlag;
    }

    public String getApiKey() {
        return apiKey;
    }

    public int getAppId() {
        return appId;
    }

    public char getCsvDelimiter() {
        return csvDelimiter;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public boolean getDebugFlag() {
        return debugFlag;
    }

    public class ProjectPropertiesException extends Exception {
        public ProjectPropertiesException(String message) {
            super(message);
        }

    }
}