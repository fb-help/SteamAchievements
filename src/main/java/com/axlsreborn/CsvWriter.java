package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter {

    public static StringBuffer csvSteamAchievement(SteamAchievement steamAchievement, char csvDelimiter) {
        StringBuffer stringBuffer = new StringBuffer();

        return stringBuffer
                .append("\"")
                .append(steamAchievement.getAchievementName())
                .append("\"")
                .append(csvDelimiter)
                .append("\"")
                .append(steamAchievement.getAchievementPercent())
                .append("\"")
                .append(csvDelimiter)
                .append("\"")
                .append(steamAchievement.getAchievementDescription())
                .append("\"")
                .append(csvDelimiter)
                .append("\"")
                .append(steamAchievement.getAchievementIconUrl())
                .append("\"")
                .append("\n");
    }

    public static void writeFile(List<SteamAchievement> steamAchievementsList, String csvFilePath, char csvDelimiter)
            throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(csvFilePath);

        for (SteamAchievement steamAchievement : steamAchievementsList) {
            java.lang.String steamAchievementStr =
                    CsvWriter.csvSteamAchievement(steamAchievement, csvDelimiter).toString();
            printWriter.write(steamAchievementStr);
        }
        printWriter.close();
    }
}