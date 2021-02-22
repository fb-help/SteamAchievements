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
	}

}
