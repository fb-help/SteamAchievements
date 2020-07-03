package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;
import com.axlsreborn.service.SteamService;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CsvWriter {
    public static final String KEY_STEAM_WEB_API_KEY = "steam.web.api_key";
    public static final String ID_STEAM_APP          = "steam.app_id";

    public static List<SteamAchievement> getSteamAchievements(String[] args) {
        List<SteamAchievement> steamAchievementsList = new ArrayList<>();

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

            System.out.println("Main.main(): apiKey = [" + apiKey + "]");
            System.out.println("Main.main(): appID = [" + appID + "]");

            if (apiKey == null) {
                System.err.println("API Key is not defined in properties file");
                System.err.printf("Expected key: [%s]\n", KEY_STEAM_WEB_API_KEY);
            }
            SteamService service = new SteamService(apiKey, appIDInt);
            steamAchievementsList = service.getAchievementList();

        } catch (IOException e) {
            System.err.println("Error while reading properties file");
            System.err.println(e.getLocalizedMessage());
        } catch (SteamApiException e) {
            System.err.println("Error during Steam Web API call");
            System.err.println(e.getLocalizedMessage());
        }
        return steamAchievementsList;
    }

    public static String toCsvString(SteamAchievement steamAchievement) {
        return String.format("%s,%.2f,%s,%s %n %n",
                steamAchievement.getAchievementName(),
                steamAchievement.getAchievementPercent(),
                steamAchievement.getAchievementDescription(),
                steamAchievement.getAchievementIconUrl());
    }

    public static void writeFIle(List<SteamAchievement> steamAchievementsList) {
        try {
            PrintWriter printWriter = new PrintWriter("Steam_Achievements.csv");

            for (SteamAchievement steamAchievement : steamAchievementsList) {
                String steamAchievementStr = CsvWriter.toCsvString(steamAchievement);
                printWriter.write(steamAchievementStr);
            }
            printWriter.close();
            System.out.println("File writing complete.");

        } catch (IOException e) {
            System.err.println("Error while reading properties file");
            System.err.println(e.getLocalizedMessage());
        }
    }
}
