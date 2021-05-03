package com.axlsreborn.tf2achievementsspring.model;

public class SteamAchievement {

    private final int    id;
    private final String name;
    private final Double percent;
    private final String description;
    private final String iconUrl;

    public SteamAchievement(int id, String name, Double percent, String description, String iconUrl) {
        this.name = name;
        this.percent = percent;
        this.description = description;
        this.iconUrl = iconUrl;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPercent() {
        return percent;
    }
}
