package com.axlsreborn;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    public static final String KEY_STEAM_WEB_API_KEY = "steam.web.api_key";
    public static final String ID_STEAM_APP          = "steam.app_id";
    public static final String CSV_DELIMITER         = "csv.delimiter";
    public static final String FILE_PATH_ACHIEVEMENTS = "file.path_achievements";

    public static String[] readFile(String[] args) {
        String[] projectProperties = new String[4];

        if (args.length != 1) {
            System.err.println("You must specify the path to the project properties file");
            System.exit(1);
        }

        // Steam Web API Key and App ID File Input
        String propertiesPath = args[0];
        System.out.println("Main.main(): propertiesPath = [" + propertiesPath + "]");
        try {
            FileReader fileReader = new FileReader(propertiesPath);
            Properties properties = new Properties();
            properties.load(fileReader);

            String apiKey = properties.getProperty(KEY_STEAM_WEB_API_KEY);
            String appID = properties.getProperty(ID_STEAM_APP);
            String csvDelimiter = properties.getProperty(CSV_DELIMITER);
            String filePathAchievements = properties.getProperty(FILE_PATH_ACHIEVEMENTS);

            System.out.println("Main.main(): apiKey = [" + apiKey + "]");
            System.out.println("Main.main(): appID = [" + appID + "]");
            System.out.println("Main.main(): csvDelimiter = [" + csvDelimiter + "]");
            System.out.println("Main.main(): filePathAchievements = [" + filePathAchievements + "]");

            if (apiKey == null) {
                System.err.println("API Key is not defined in properties file");
                System.err.printf("Expected key: [%s]\n", KEY_STEAM_WEB_API_KEY);
            }
            if (csvDelimiter.length() == 0) {
                System.err.println("Please add a delimiter to the properties file");
                System.err.printf("csvDelimiter = [%s]\n", CSV_DELIMITER);
            }
            if (filePathAchievements == null || filePathAchievements.isEmpty()) {
                System.err.println("Please add a file path to save the Steam Achievements");
                System.err.printf("filePathAchievements = [%s]\n", FILE_PATH_ACHIEVEMENTS);
            }
            projectProperties[0] = apiKey;
            projectProperties[1] = appID;
            projectProperties[2] = csvDelimiter;
            projectProperties[3] = filePathAchievements;

        } catch (IOException e) {
            System.err.println("Error while reading properties file");
            System.err.println(e.getLocalizedMessage());
        }
        return projectProperties;
    }
}
