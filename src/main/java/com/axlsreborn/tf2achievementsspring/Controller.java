package com.axlsreborn.tf2achievementsspring;

import com.axlsreborn.tf2achievementsspring.model.SteamAchievement;
import com.axlsreborn.tf2achievementsspring.service.SteamService;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/tf2")
    public List<SteamAchievement> getAchievements() throws SteamApiException {
        SteamService steamService = new SteamService("4BCFAFD214028581069051D38F35D78C", 440);
        return steamService.getAchievementList();
    }

    @GetMapping("/{appId}")
    public List<SteamAchievement> getAppId(@PathVariable int appId) throws SteamApiException {
        SteamService steamService = new SteamService("4BCFAFD214028581069051D38F35D78C", appId);
        return steamService.getAchievementList();
    }
}
