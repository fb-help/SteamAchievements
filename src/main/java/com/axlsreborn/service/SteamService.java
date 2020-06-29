package com.axlsreborn.service;

import com.axlsreborn.model.SteamAchievement;
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
import java.util.LinkedHashMap;
import java.util.List;

public class SteamService {

    private final SteamWebApiClient apiClient;
    private final int               appID;


    public SteamService(String apiKey, int appID) {
        apiClient = new SteamWebApiClient.SteamWebApiClientBuilder(apiKey).build();
        this.appID = appID;
    }

    public List<SteamAchievement> getAchievementList() throws SteamApiException {
        List<SteamAchievement> achievementList = new ArrayList<>();
        GetGlobalAchievementPercentagesForAppRequest request =
                SteamWebApiRequestFactory.createGetGlobalAchievementPercentagesForAppRequest(appID);
        GetGlobalAchievementPercentagesForApp getGlobalAchievements =
                apiClient.processRequest(request);

        // GetSchemaForGame Request
        GetSchemaForGameRequest request2 =
                SteamWebApiRequestFactory.createGetSchemaForGameRequest(appID);
        GetSchemaForGame getSchemaForGame = apiClient.processRequest(request2);

        // Gets Percentages from GetGlobalAchievementPercentagesForApp
        Achievementpercentages achievementpercentages =
                getGlobalAchievements.getAchievementpercentages();
        List<com.lukaspradel.steamapi.data.json.achievementpercentages.Achievement>
                globalAchievements = achievementpercentages.getAchievements();

        // Gets Achievement Stats. displayname, description, icongray from getSchemaForGame.
        Game game = getSchemaForGame.getGame();
        AvailableGameStats stats = game.getAvailableGameStats();
        List<Achievement> schemaAchievements = stats.getAchievements();

        // Create Linked Hash Map for iterating through globalAchievements.
        LinkedHashMap<String, String> mapGlobalAchievements = new LinkedHashMap<>();

        // Loops through globalAchievements and stores names and percentages.  This will be used as reference to
        // get further data from schemaAchievements List
        for (com.lukaspradel.steamapi.data.json.achievementpercentages.Achievement globalAchievement : globalAchievements) {
            String achievementName = globalAchievement.getName();
            String achievementPercent = String.valueOf(globalAchievement.getPercent());
            mapGlobalAchievements.put(achievementName, achievementPercent);
        }

        for (Achievement schemaAchievement : schemaAchievements) {
            String name = schemaAchievement.getDisplayName();
            String description = schemaAchievement.getDescription();
            double percent =
                    Double.parseDouble(mapGlobalAchievements.get(schemaAchievement.getName()));
            String imageUrl = schemaAchievement.getIcongray();

            SteamAchievement achievement =
                    new SteamAchievement(name, percent, description, imageUrl);
            achievementList.add(achievement);
        }

        return achievementList;
    }

}


//        // Map for final output.
//        Map<String, List<String>> achievementsFinal = new HashMap<>();
//
//        for (Achievement schemaAchievement : schemaAchievements) {
//
//            String achievementDisplayName = schemaAchievement.getDisplayName();
//
//            // Create and add values to ArrayList.
//            List<String> values = new ArrayList<String>();
//            values.add(schemaAchievement.getDescription());
//            values.add(String.valueOf(mapGlobalAchievements.get(schemaAchievement.getName())));
//            values.add(schemaAchievement.getIcongray());
//
//            achievementsFinal.put(achievementDisplayName, values);
//        }

//        return achievementsFinal;
