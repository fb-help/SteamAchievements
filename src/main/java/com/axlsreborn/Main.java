package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;
import com.axlsreborn.service.SteamService;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ProjectPropertiesArgs projectPropertiesArgs = ProjectProperties.getProperties(args);
        String apiKey = projectPropertiesArgs.getApiKey();
        int appId = projectPropertiesArgs.getAppId();
        char csvDelimiter = projectPropertiesArgs.getCsvDelimiter();
        String csvFilePath = projectPropertiesArgs.getCsvFilePath();

        if (args.length != 1) {
            System.err.println("You must specify the path to the project properties file");
            System.exit(1);
        }

        System.out.println("Start");
        List<SteamAchievement> steamAchievementsList = Main.getSteamAchievements(apiKey, appId);
        CsvWriter.writeFile(steamAchievementsList, csvFilePath, csvDelimiter);
        System.out.println("Program Complete");
    }

    public static List<SteamAchievement> getSteamAchievements(String apiKey, int appId) {
        List<SteamAchievement> steamAchievementsList = new ArrayList<>();

        try {
            SteamService service = new SteamService(apiKey, appId);
            steamAchievementsList = service.getAchievementList();
        } catch (SteamApiException e) {
            System.err.println("Error during Steam Web API call");
            System.err.println(e.getLocalizedMessage());
        }
        return steamAchievementsList;
    }
}