package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter {

    public static String toCsvString(SteamAchievement steamAchievement, char csvDelimiter) {
        return String.format("%s %s %.2f %s \"%s\" %s %s %n",
                steamAchievement.getAchievementName(),
                csvDelimiter,
                steamAchievement.getAchievementPercent(),
                csvDelimiter,
                steamAchievement.getAchievementDescription(),
                csvDelimiter,
                steamAchievement.getAchievementIconUrl());
    }

    public static void writeFile(List<SteamAchievement> steamAchievementsList, String csvFilePath, char csvDelimiter)
            throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(csvFilePath);

        for (SteamAchievement steamAchievement : steamAchievementsList) {
            java.lang.String steamAchievementStr = CsvWriter.toCsvString(steamAchievement, csvDelimiter).toString();
            printWriter.write(steamAchievementStr);
        }
        printWriter.close();
    }
}