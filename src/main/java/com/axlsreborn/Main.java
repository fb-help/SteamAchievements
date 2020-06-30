package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;
import com.axlsreborn.service.SteamService;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {
    public static final String KEY_STEAM_WEB_API_KEY = "steam.web.api_key";
    public static final String ID_STEAM_APP          = "steam.app_id";
    //public static final String PATH_FILE_OUTPUT = "file.path_output";

    public static void main(String[] args) {
        System.out.println("Start");

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
            int appIDInt = Integer.parseInt(appID);
            //String filePathOutput = properties.getProperty(PATH_FILE_OUTPUT);

            System.out.println("Main.main(): apiKey = [" + apiKey + "]");
            System.out.println("Main.main(): appID = [" + appID + "]");
            //System.out.println("Main.main(): filePathOutput = [" + filePathOutput + "]");

            if (apiKey == null) {
                System.err.println("API Key is not defined in properties file");
                System.err.printf("Expected key: [%s]\n", KEY_STEAM_WEB_API_KEY);
            }

            // CSV File Output
            SteamService service = new SteamService(apiKey, appIDInt);
            List<SteamAchievement> achievementList = service.getAchievementList();
            System.out.println("File writing complete.");

            // TODO: Do something with achievements
        } catch (IOException e) {
            System.err.println("Error while reading properties file");
            System.err.println(e.getLocalizedMessage());
        } catch (SteamApiException e) {
            System.err.println("Error during Steam Web API call");
            System.err.println(e.getLocalizedMessage());
        }

        System.out.println("Done");
    }
}
