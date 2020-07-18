package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter {

    public static StringBuffer csvSteamAchievement(SteamAchievement steamAchievement,
            char csvDelimiter) {
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

    public static void writeFile(List<SteamAchievement> steamAchievementsList,
            String csvFilePath,
            char csvDelimiter) {
        try {
            PrintWriter printWriter = new PrintWriter(csvFilePath);

            for (SteamAchievement steamAchievement : steamAchievementsList) {
                java.lang.String steamAchievementStr =
                        CsvWriter.csvSteamAchievement(steamAchievement, csvDelimiter).toString();
                printWriter.write(steamAchievementStr);
            }
            printWriter.close();
            System.out.println("File writing complete.");

        } catch (IOException e) {
            System.err.println("Error while reading properties file");
            System.err.println(e.getLocalizedMessage());
        }
    }
}