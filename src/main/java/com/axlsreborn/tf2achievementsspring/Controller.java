package com.axlsreborn.tf2achievementsspring;

import com.axlsreborn.tf2achievementsspring.model.SteamAchievement;
import com.axlsreborn.tf2achievementsspring.service.SteamService;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    public static final int APP_ID_TF2 = 440;

    @Value("${steam.web_api_key}")
    private String apikey;

    private SteamService steamService;

    @GetMapping("/")
    public List<SteamAchievement> getAchievements() throws SteamApiException {
        if (steamService == null) {
            steamService = new SteamService(apikey);
        }
        return steamService.getAchievementList(APP_ID_TF2);
    }

    @GetMapping("/{appId}")
    public List<SteamAchievement> getAppId(@PathVariable int appId) throws SteamApiException {
        if (steamService == null) {
            steamService = new SteamService(apikey);
        }
        return steamService.getAchievementList(appId);
    }
}
