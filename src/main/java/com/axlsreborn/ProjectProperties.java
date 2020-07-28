package com.axlsreborn;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    public static final String KEY_STEAM_WEB_API_KEY = "steam.web_api_key";
    public static final String KEY_STEAM_APP_ID = "steam.app_id";
    public static final String KEY_CSV_DELIMITER = "csv.delimiter";
    public static final String KEY_CSV_FILE_PATH = "csv.file_path";
    public static final String KEY_CSV_FILE_OVERWRITE = "csv.file_overwrite";
    public static final String KEY_DEBUG_FLAG = "debug.flag";

    private final String apiKey;
    private final int appId;
    private final char csvDelimiter;
    private final String csvFilePath;
    private final boolean csvFileOverwriteFlag;
    private final boolean csvFileErrorFlag;
    private final boolean debugFlag;

    public ProjectProperties(String propertiesPath) throws ProjectPropertiesException {
        String apiKey;
        int appId;
        char csvDelimiter = ' ';
        String csvFilePath;
        boolean csvFileErrorFlag = false;
        boolean csvFileOverwriteFlag = false;
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
            System.err.printf("App ID: [%s]\n", KEY_STEAM_APP_ID);
            throw new ProjectPropertiesException(e.getLocalizedMessage());
        }

        apiKey = properties.getProperty(KEY_STEAM_WEB_API_KEY);
        String csvDelimiterStr = properties.getProperty(KEY_CSV_DELIMITER);
        csvFilePath = properties.getProperty(KEY_CSV_FILE_PATH);
        String csvFileOverwriteStr = properties.getProperty(KEY_CSV_FILE_OVERWRITE);
        String debugFlagStr = properties.getProperty(KEY_DEBUG_FLAG);

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

        if (debugFlagStr == null || debugFlagStr.isEmpty()) {
            System.err.printf("debugFlagStr = [%s]\n", KEY_DEBUG_FLAG);
            throw new ProjectPropertiesException("Debug flag is not defined in properties file");
        } else if (debugFlagStr.toUpperCase().equals("TRUE")) {
            debugFlag = true;
        } else if (debugFlagStr.toUpperCase().equals("FALSE")) {
            debugFlag = false;
        } else {
            throw new ProjectPropertiesException("Set debug flag to true or false");
        }

        if (csvFileOverwriteStr == null || csvFileOverwriteStr.isEmpty()) {
            System.out.printf("csvFileOverwriteStr = [%s]\n", KEY_CSV_FILE_OVERWRITE);
            throw new ProjectPropertiesException("CSV file overwrite is not defined in properties file");
        } else if (csvFileOverwriteStr.toUpperCase().equals("TRUE")) {
            csvFileOverwriteFlag = true;
        } else if (csvFileOverwriteStr.toUpperCase().equals("FALSE")) {
            csvFileOverwriteFlag = false;
        } else {
            throw new ProjectPropertiesException("Set CSV File Overwrite to true or false");
        }

        try {
            File filePathOrig = new File(csvFilePath);
            if (!csvFileOverwriteFlag && filePathOrig.isFile()) {
                System.err.printf("csv.file_path = [%s]\n", KEY_CSV_FILE_PATH);
                throw new ProjectPropertiesException("File already exists\n" +
                        "Change file name or enable file overwriting in properties file");
            }
        } catch (SecurityException e) {
            throw new ProjectPropertiesException(e.getLocalizedMessage());
        }

        try {
            String fileExtension = FilenameUtils.getExtension(csvFilePath);
            if (!fileExtension.equals("csv")) {
                System.err.printf("csv.file_path = [%s]\n", KEY_CSV_FILE_PATH);
                throw new ProjectPropertiesException("File extension is not .csv\n" +
                        "Fix CSV file path in properties file");
            }
        } catch (SecurityException e) {
            throw new ProjectPropertiesException(e.getLocalizedMessage());
        }

        try {
            File fileFullPath = new File(FilenameUtils.getFullPath(csvFilePath));
            if (!fileFullPath.isDirectory()) {
                System.err.printf("csv.file_path = [%s]\n", KEY_CSV_FILE_PATH);
                throw new ProjectPropertiesException("File directory does not exist\n" +
                        "Fix CSV file path in properties file");
            }
        } catch (SecurityException e) {
            throw new ProjectPropertiesException(e.getLocalizedMessage());
        }

        if (debugFlag) {
            System.out.println("Main.main(): apiKey = [" + apiKey + "]");
            System.out.println("Main.main(): appId = [" + appId + "]");
            System.out.println("Main.main(): csvDelimiter = [" + csvDelimiterStr + "]");
            System.out.println("Main.main(): csvFilePath = [" + csvFilePath + "]");
            System.out.println("Main.main(): csvFileOverwriteFlag = [" + csvFileOverwriteFlag + "]");
            System.out.println("Main.main(): csvFileErrorFlag = [" + csvFileErrorFlag + "]");
            System.out.println("Main.main(): debugFlag = [" + debugFlag + "]");
        }

        this.apiKey = apiKey;
        this.appId = appId;
        this.csvDelimiter = csvDelimiter;
        this.csvFilePath = csvFilePath;
        this.csvFileOverwriteFlag = csvFileOverwriteFlag;
        this.csvFileErrorFlag = csvFileErrorFlag;
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

    public boolean getCsvFileErrorFlag() {
        return csvFileErrorFlag;
    }

    public boolean getCsvFileOverwriteFlag() {
        return csvFileOverwriteFlag;
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