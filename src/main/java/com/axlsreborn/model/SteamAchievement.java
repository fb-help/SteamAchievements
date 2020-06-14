package com.axlsreborn.model;

public class SteamAchievement {

    private final String achievementName;
    private final Double achievementPercent;
    private final String achievementDescription;
    private final String achievementIconUrl;

    public SteamAchievement(String achievementName, Double achievementPercent, String achievementDescription, String achievementIconUrl) {
        this.achievementName = achievementName;
        this.achievementPercent = achievementPercent;
        this.achievementDescription = achievementDescription;
        this.achievementIconUrl = achievementIconUrl;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public Double getAchievementPercent() {
        return achievementPercent;
    }

    public String getAchievementDescription() {
        return achievementDescription;
    }

    public String getAchievementIconUrl() {
        return achievementIconUrl;
    }
}
