package com.axlsreborn;

import com.axlsreborn.model.SteamAchievement;
import com.axlsreborn.service.SteamService;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        System.out.println("Start");
        List<SteamAchievement> steamAchievementsList = CsvWriter.getSteamAchievements(args);
        CsvWriter.writeFIle(steamAchievementsList);
        System.out.println("Program Complete");
    }
}
