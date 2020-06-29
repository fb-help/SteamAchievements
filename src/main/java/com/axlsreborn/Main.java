package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;
import com.axlsreborn.service.SteamService;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {
    //test
    public static final String KEY_STEAM_WEB_API_KEY = "steam.web.api_key";

    public static void main(String[] args) {
        System.out.println("Start");

        if (args.length != 1) {
            System.err.println("You must specify the path to the project properties file");
            System.exit(1);
        }

        String propertiesPath = args[0];
        System.out.println("Main.main(): propertiesPath = [" + propertiesPath + "]");
        try {
            FileReader fileReader = new FileReader(propertiesPath);
            Properties properties = new Properties();
            properties.load(fileReader);
            String apiKey = properties.getProperty(KEY_STEAM_WEB_API_KEY);
            System.out.println("Main.main(): apiKey = [" + apiKey + "]");
            if (apiKey == null) {
                System.err.println("API Key is not defined in properties file");
                System.err.printf("Expected key: [%s]\n", KEY_STEAM_WEB_API_KEY);
            }

            SteamService service = new SteamService(apiKey);
            List<SteamAchievement> achievementList = service.getAchievementList();
            System.out.println("Main.main(): achievementList = [" + achievementList + "]");

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
