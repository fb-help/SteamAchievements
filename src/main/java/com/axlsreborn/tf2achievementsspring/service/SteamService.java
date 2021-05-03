package com.axlsreborn.tf2achievementsspring.service;

import com.axlsreborn.tf2achievementsspring.model.SteamAchievement;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.achievementpercentages.Achievementpercentages;
import com.lukaspradel.steamapi.data.json.achievementpercentages.GetGlobalAchievementPercentagesForApp;
import com.lukaspradel.steamapi.data.json.getschemaforgame.Achievement;
import com.lukaspradel.steamapi.data.json.getschemaforgame.AvailableGameStats;
import com.lukaspradel.steamapi.data.json.getschemaforgame.Game;
import com.lukaspradel.steamapi.data.json.getschemaforgame.GetSchemaForGame;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetSchemaForGameRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SteamService {

    private final SteamWebApiClient apiClient;

    public SteamService(String apiKey) {
        apiClient = new SteamWebApiClient.SteamWebApiClientBuilder(apiKey).build();
    }

    public List<SteamAchievement> getAchievementList(int appID) throws SteamApiException {
        List<SteamAchievement> achievementList = new ArrayList<>();
        GetGlobalAchievementPercentagesForAppRequest achievementsRequest =
                SteamWebApiRequestFactory.createGetGlobalAchievementPercentagesForAppRequest(appID);
        GetGlobalAchievementPercentagesForApp getGlobalAchievements = apiClient.processRequest(achievementsRequest);

        // GetSchemaForGame Request
        GetSchemaForGameRequest schemaRequest = SteamWebApiRequestFactory.createGetSchemaForGameRequest(appID);
        GetSchemaForGame getSchemaForGame = apiClient.processRequest(schemaRequest);

        // Gets Percentages from GetGlobalAchievementPercentagesForApp
        Achievementpercentages achievementPercentages = getGlobalAchievements.getAchievementpercentages();
        List<com.lukaspradel.steamapi.data.json.achievementpercentages.Achievement> globalAchievements =
                achievementPercentages.getAchievements();

        // Gets Achievement Stats. displayname, description, icongray from getSchemaForGame.
        Game game = getSchemaForGame.getGame();
        AvailableGameStats stats = game.getAvailableGameStats();
        List<Achievement> schemaAchievements = stats.getAchievements();

        // Create Linked Hash Map for iterating through globalAchievements.
        Map<String, Double> mapGlobalAchievements = new HashMap<>();

        // Loops through globalAchievements and stores names and percentages.  This will be used as reference to
        // get further data from schemaAchievements List
        for (com.lukaspradel.steamapi.data.json.achievementpercentages.Achievement globalAchievement : globalAchievements) {
            String achievementName = globalAchievement.getName();
            Double achievementPercent = globalAchievement.getPercent();
            mapGlobalAchievements.put(achievementName, achievementPercent);
        }

        int id = -1;
        // Create a SteamAchievement
        for (Achievement schemaAchievement : schemaAchievements) {
            String name = schemaAchievement.getDisplayName();
            ++id;
            String description = schemaAchievement.getDescription();
            double percent = mapGlobalAchievements.get(schemaAchievement.getName());
            String imageUrl = schemaAchievement.getIcongray();

            SteamAchievement achievement = new SteamAchievement(id, name, percent, description, imageUrl);
            achievementList.add(achievement);
        }

        return achievementList;
    }
}
