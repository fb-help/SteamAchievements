package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter {

    public static StringBuffer csvSteamAchievement(SteamAchievement steamAchievement,
            String[] projectProperties) {
        String csvDelimiter = projectProperties[2];
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
            String[] projectProperties) {
        try {
            String filePathAchievements = projectProperties[3];
            PrintWriter printWriter = new PrintWriter(filePathAchievements);

            for (SteamAchievement steamAchievement : steamAchievementsList) {
                java.lang.String steamAchievementStr =
                        CsvWriter.csvSteamAchievement(steamAchievement, projectProperties).toString();
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