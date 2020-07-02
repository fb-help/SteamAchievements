package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;

public class CsvWriter {
    public static String toCsvString(SteamAchievement steamAchievement) {
        return String.format("%s,%.2f,%s,%s %n %n",
                steamAchievement.getAchievementName(),
                steamAchievement.getAchievementPercent(),
                steamAchievement.getAchievementDescription(),
                steamAchievement.getAchievementIconUrl());
    }
}
