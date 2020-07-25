package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter {
    public static String toCsvString(SteamAchievement steamAchievement, char csvDelimiter) {
        return StringUtils.join(new String[]{
                StringEscapeUtils.escapeCsv(steamAchievement.getAchievementName()),
                steamAchievement.getAchievementPercent().toString(),
                StringEscapeUtils.escapeCsv(steamAchievement.getAchievementDescription()),
                steamAchievement.getAchievementIconUrl() + "\n"
        }, csvDelimiter);
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