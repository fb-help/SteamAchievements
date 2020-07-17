package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;
import com.axlsreborn.service.SteamService;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Start");
        String[] projectProperties = ProjectProperties.readFile(args);
        List<SteamAchievement> steamAchievementsList = Main.getSteamAchievements(projectProperties);
        CsvWriter.writeFile(steamAchievementsList, projectProperties);
        System.out.println("Program Complete");
    }

    public static List<SteamAchievement> getSteamAchievements(String[] projectProperties) {
        List<SteamAchievement> steamAchievementsList = new ArrayList<>();
        String apiKey = projectProperties[0];
        int appId = Integer.parseInt(projectProperties[1]);

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