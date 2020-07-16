package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;
import com.axlsreborn.service.SteamService;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // git checkout dev
    // will switch to dev branch
    public static void main(String[] args) {
        System.out.println("Start");
        String[] projectProperties = ProjectPropertiesFile.readFile(args);
        List<SteamAchievement> steamAchievementsList = Main.getSteamAchievements(projectProperties);
        CsvWriter.writeFile(steamAchievementsList, projectProperties);
        System.out.println("Program Complete");
    }

    public static List<SteamAchievement> getSteamAchievements(String[] projectProperties) {
        List<SteamAchievement> steamAchievementsList = new ArrayList<>();
        String apiKey = projectProperties[0];
        int appID = Integer.parseInt(projectProperties[1]);

        try {
            SteamService service = new SteamService(apiKey, appID);
            steamAchievementsList = service.getAchievementList();
        } catch (SteamApiException e) {
            System.err.println("Error during Steam Web API call");
            System.err.println(e.getLocalizedMessage());
        }
        return steamAchievementsList;
    }
}