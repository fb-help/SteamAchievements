package com.axlsreborn.tf2achievementsspring.model;

public class SteamAchievement {

    private final String achievementName;
    private final Double achievementPercent;
    private final String achievementDescription;
    private final String achievementIconUrl;
    private final int achievementId;

    public SteamAchievement(String achievementName, int achievementId, Double achievementPercent, String achievementDescription, String achievementIconUrl) {
        this.achievementName = achievementName;
        this.achievementPercent = achievementPercent;
        this.achievementDescription = achievementDescription;
        this.achievementIconUrl = achievementIconUrl;
        this.achievementId = achievementId;
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

    public int getAchievementId() { return achievementId; }
}
