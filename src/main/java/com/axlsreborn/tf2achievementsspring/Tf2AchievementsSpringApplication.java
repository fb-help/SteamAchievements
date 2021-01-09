package com.axlsreborn.tf2achievementsspring;

import com.axlsreborn.tf2achievementsspring.model.SteamAchievement;
import com.axlsreborn.tf2achievementsspring.service.SteamService;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootApplication
public class Tf2AchievementsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tf2AchievementsSpringApplication.class, args);

//		if (args.length != 1) {
//			System.err.println("You must specify the path to the project properties file");
//			System.exit(1);
//		}
//		ProjectProperties properties;
//		try {
//			properties = new ProjectProperties(args[0]);
//		} catch (ProjectProperties.ProjectPropertiesException e) {
//			System.err.println("Error while reading properties file");
//			System.err.println(e.getLocalizedMessage());
//			System.exit(2);
//			return;
//		}
//		String apiKey = properties.getApiKey();
//		int appId = properties.getAppId();
//		char csvDelimiter = properties.getCsvDelimiter();
//		String csvFilePath = properties.getCsvFilePath();
//		boolean debugFlag = properties.getDebugFlag();
//
//		System.out.println("Start");
//
//		List<SteamAchievement> steamAchievementsList;
//		try {
//			SteamService service = new SteamService(apiKey, appId);
//			steamAchievementsList = service.getAchievementList();
//		} catch (SteamApiException e) {
//			System.err.println("Error getting Steam Achievements");
//			System.err.println(e.getLocalizedMessage());
//			System.exit(3);
//			return;
//		}
//
//		if (debugFlag) {
//			System.out.println("Steam Achievement List Count = " + steamAchievementsList.size());
//		}
//
//		if (steamAchievementsList.isEmpty()) {
//			System.err.println("Steam Achievement List is empty");
//			System.exit(4);
//			return;
//		}
//
//		try {
//			CsvWriter.writeFile(steamAchievementsList, csvFilePath, csvDelimiter);
//		} catch (FileNotFoundException e) {
//			System.err.println("Error writing Steam Achievements to file");
//			System.exit(5);
//			return;
//		}
//
//		System.out.println("Program Complete");

	}

}
