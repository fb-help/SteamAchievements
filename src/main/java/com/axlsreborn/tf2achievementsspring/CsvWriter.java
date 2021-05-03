package com.axlsreborn.tf2achievementsspring;

import com.axlsreborn.tf2achievementsspring.model.SteamAchievement;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter {

    public static String toCsvString(SteamAchievement steamAchievement, char csvDelimiter) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer
                .append("\"")
                .append(steamAchievement.getId())
                .append("\"")
                .append(csvDelimiter)
                .append("\"")
                .append(steamAchievement.getName())
                .append("\"")
                .append(csvDelimiter)
                .append("\"")
                .append(steamAchievement.getPercent())
                .append("\"")
                .append(csvDelimiter)
                .append("\"")
                .append(steamAchievement.getDescription())
                .append("\"")
                .append(csvDelimiter)
                .append("\"")
                .append(steamAchievement.getIconUrl())
                .append("\"\n");
        return stringBuffer.toString();
    }


    public static void writeFile(List<SteamAchievement> steamAchievementsList, String csvFilePath, char csvDelimiter)
            throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(csvFilePath);

        for (SteamAchievement steamAchievement : steamAchievementsList) {
            String steamAchievementStr = CsvWriter.toCsvString(steamAchievement, csvDelimiter);
            printWriter.write(steamAchievementStr);
        }
        printWriter.close();
    }
}
