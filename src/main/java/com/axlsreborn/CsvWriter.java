package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;

import org.apache.commons.lang.StringEscapeUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter {

    public static String toCsvString(SteamAchievement steamAchievement, char csvDelimiter) {
        String getAchievementNameEscaped = StringEscapeUtils.escapeCsv(steamAchievement.getAchievementName());
        String getAchievementPercentEscaped = StringEscapeUtils.escapeCsv(steamAchievement.getAchievementDescription());
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer
                .append("\"")
                .append(getAchievementNameEscaped)
                .append("\"")
                .append(csvDelimiter)
                .append("\"")
                .append(steamAchievement.getAchievementPercent())
                .append("\"")
                .append(csvDelimiter)
                .append("\"")
                .append(getAchievementPercentEscaped)
                .append("\"")
                .append(csvDelimiter)
                .append("\"")
                .append(steamAchievement.getAchievementIconUrl())
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